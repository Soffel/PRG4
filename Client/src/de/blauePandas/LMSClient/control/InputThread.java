package de.blauePandas.LMSClient.control;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 24.02.14
 */

public class InputThread implements Runnable
{
    private Socket client;



    public InputThread(Socket _client)
    {
        this.client = _client;
    }

    @Override
    public void run()
    {
        try
        {
            OutputStream output = this.client.getOutputStream();
            PrintWriter writer = new PrintWriter(output);
            Scanner eingabe = new Scanner(System.in);

            while(true)
            {
                String input = eingabe.nextLine();

                if(!input.isEmpty())
                {
                    String[] split  = input.split(" ");             //String in wird bei jeden Leerzeichen geteilt und im split gespeichert
                    String cmd      = split[0];                     //Comando extrakation

                    writer.write(input + "\n"); //übergabe an server
                    writer.flush();             //abschicken

                    if(cmd.equalsIgnoreCase("stop"))
                    {
                        System.out.println("Client wird beendet.");

                      break;
                    }
                }
            }
            writer.close();
            client.close();
        }

        catch(Exception e)
        {

            System.out.println("InputThread -- " + e.toString());

        }
    }
}

