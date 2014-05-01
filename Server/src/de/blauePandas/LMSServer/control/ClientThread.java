package de.blauePandas.LMSServer.control;

import de.blauePandas.LMSServer.control.commands.EchoCommand;
import de.blauePandas.LMSServer.control.commands.StopCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 23.02.14
 */
public class ClientThread implements Runnable
{
    private Socket client               = null;
    private static boolean clientStop   = false;
    public static ConsoleControl  ConsoleControl = null;

    /* übergabe des Clienten */
    public ClientThread(Socket _client)
    {
        this.client = _client;
    }


    public static void StopClient()
    {
        clientStop = true;
    }


    @Override
    public void run()
    {
        PrintWriter     writer  = null;
        BufferedReader  reader  = null;

        ConsoleControl  = new ConsoleControl();

        ConsoleControl.addCmd(new EchoCommand());
        //ConsoleControl.addCmd(new LoginCommand());
        ConsoleControl.addCmd(new StopCommand()); //todo sobald rechte verwaltung steht entfernen

        try
        {
            writer  = new PrintWriter(client.getOutputStream());
            reader  = new BufferedReader(new InputStreamReader(client.getInputStream()));

            writer.write("<<"+Thread.currentThread().getName() + ">>  Successfully Serverconnection\n");
            writer.flush();

            System.out.println("<<" + Thread.currentThread().getName() + ">> Successfully Clientconnection");
            TextFileWriter.systemLog( Thread.currentThread().getName() + " Successfully connection");


            while (true)
            {
                String input = reader.readLine();

                if (!input.isEmpty())
                {
                    System.out.println("<<" + Thread.currentThread().getName() + ">> "+ input);
                    TextFileWriter.systemLog( "INPUT: " + Thread.currentThread().getName() + " " + input);

                    String[] split  = input.split(" ");             //Sting in wird bei jeden Leerzeichen geteilt und im split gespeichert
                    String cmd      = split[0];                     //Comando extrakation

                    String[] args   = new String[split.length-1];   // restlichen eingaben
                    System.arraycopy(split, 1, args, 0, split.length - 1);

                    String msg = ConsoleControl.performCmd(cmd, args,100);//todo rights

                    writer.write(msg + "\n");
                    writer.flush();

                    if (clientStop)
                    {
                        System.out.println("<<" + Thread.currentThread().getName() + ">> interrupt connection");
                        TextFileWriter.systemLog( Thread.currentThread().getName() + " interrupt connection");
                        break;
                    }
                }
            }
        }

        catch (Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                               "   for more info visit the Error Log!");
        }
        finally
        {
            if (writer != null)
            {
                writer.close();
            }

            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    TextFileWriter.writeError(e);
                    System.out.println("   An error has Occurred!\n" +
                                       "   for more info visit the Error Log!");
                }
            }

            if (client != null)
            {
                try
                {
                    client.close();
                }
                catch (IOException e)
                {
                    TextFileWriter.writeError(e);
                    System.out.println("   An error has Occurred!\n" +
                                       "   for more info visit the Error Log!");
                }
            }
        }
    }
}
