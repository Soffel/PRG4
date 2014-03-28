package de.blauePandas.LMSServer.control;

import de.blauePandas.LMSServer.control.commands.*;
import de.blauePandas.LMSServer.model.*;

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
    private  Socket client;


    /* übergabe des Clienten */
    public ClientThread(Socket _client)
    {
        this.client = _client;
    }

    @Override
    public void run()
    {

        ConsoleControl ConsoleControl = new ConsoleControl();

        ConsoleControl.addCmd(new EchoCommand());

        try
        {

            PrintWriter writer = new PrintWriter(client.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            writer.write("<<"+Thread.currentThread().getName() + ">>  Verbindung zum Server ist aufgebaut\n");
            writer.flush();
            System.out.println("<<" + Thread.currentThread().getName() + ">> Verbindung aufgebaut");

            while(true)
            {
                String input = reader.readLine();


                if(!input.isEmpty())
                {


                    String[] split  = input.split(" ");             //Sting in wird bei jeden Leerzeichen geteilt und im split gespeichert
                    String cmd      = split[0];                     //Comando extrakation

                    String[] args   = new String[split.length-1];   // restlichen eingaben
                    System.arraycopy(split, 1, args, 0, split.length - 1);

                    if(cmd.equalsIgnoreCase("stop"))
                    {
                        System.out.print("<<" + Thread.currentThread().getName() + ">> Verbindung getrennt");
                        break;
                    }



                    String msg = ConsoleControl.performCmd(cmd, args,1);//todo rights

                    writer.write(msg+"\n");
                    writer.flush();

                    System.out.println("<<" + Thread.currentThread().getName() + ">> "+ input);
                }
            }
            writer.close();
            reader.close();
            client.close();
        }

        catch(Exception e)
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
    }
}
