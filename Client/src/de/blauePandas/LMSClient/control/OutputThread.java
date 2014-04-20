package de.blauePandas.LMSClient.control;

import de.blauePandas.LMSClient.Client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 24.02.14
 */
public class OutputThread implements Runnable
{
    private Socket client;

    public OutputThread(Socket _client)
    {
        this.client = _client;
    }

    @Override
    public void run()
    {
        InputStream input = null;
        BufferedReader reader = null;

        try
        {
            input = this.client.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            while(true)
            {
                String output = reader.readLine(); //entfangen von Server

                if(!output.isEmpty())
                {
                    if(output.equalsIgnoreCase("interrupt connection") || output.equalsIgnoreCase("stopped server"))
                    {
                        if(output.equalsIgnoreCase("stopped server"))
                        {
                            System.out.println("   Server is stopped");
                            TextFileWriter.systemLog("Server is stopped");
                        }

                        System.out.println("   Client is stopped.");
                        TextFileWriter.systemLog("Client is stopped");
                        break;
                    }

                    System.out.print("   " + output+"\n>> ");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("\n    Lost connection to server! \n" +
                               "    Client is terminated.");

            TextFileWriter.writeError(e);
        }
        finally
        {
            try
            {
                if(reader != null)
                {
                    reader.close();
                }

                if(input != null)
                {
                    input.close();
                }

                Client.stop();
            }
            catch (Exception e)
            {
                TextFileWriter.writeError(e);
                System.out.println("   An error has Occurred!\n"+
                                   "   for more info visit the Error Log!");
            }
        }
    }
}
