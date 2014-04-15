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
        PrintWriter     writer          = null;
        BufferedReader  reader          = null;
        ConsoleControl  ConsoleControl  = new ConsoleControl();

        ConsoleControl.addCmd(new EchoCommand());
        ConsoleControl.addCmd(new StopCommand()); //todo sobald rechte verwaltung steht entfernen

        try
        {
            writer  = new PrintWriter(client.getOutputStream());
            reader  = new BufferedReader(new InputStreamReader(client.getInputStream()));

            writer.write("<<"+Thread.currentThread().getName() + ">>  Verbindung zum Server ist aufgebaut\n");
            writer.flush();

            System.out.println("<<" + Thread.currentThread().getName() + ">> Verbindung aufgebaut");

            while (true)
            {
                String input = reader.readLine();

                if (!input.isEmpty())
                {
                    String[] split  = input.split(" ");             //Sting in wird bei jeden Leerzeichen geteilt und im split gespeichert
                    String cmd      = split[0];                     //Comando extrakation

                    String[] args   = new String[split.length-1];   // restlichen eingaben
                    System.arraycopy(split, 1, args, 0, split.length - 1);

                    String msg = ConsoleControl.performCmd(cmd, args,1);//todo rights

                    if (clientStop)
                    {
                        System.out.print("<<" + Thread.currentThread().getName() + ">> Verbindung getrennt\n");
                        break;
                    }

                    writer.write("   " + msg + "\n");
                    writer.flush();

                    System.out.println("ss<<" + Thread.currentThread().getName() + ">> "+ input);
                }
            }
        }

        catch (Exception e)
        {
            System.out.print("ClientThred -- " + e.toString() + "\n");

            try
            {
                client.close();
            }
            catch (IOException e1)
            {
                System.out.print("ClientThred -client.close- " + e1.toString() + "\n");
            }
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
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }
        }
    }
}
