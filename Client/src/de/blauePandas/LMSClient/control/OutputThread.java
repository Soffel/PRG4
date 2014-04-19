package de.blauePandas.LMSClient.control;

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
                    if(output.equalsIgnoreCase("Verbindung getrennt") || output.equalsIgnoreCase("Server gestoppt"))
                    {
                        if(output.equalsIgnoreCase("Server gestoppt"))
                        {
                            System.out.println("   der Server wird Beendet.");
                        }

                        System.out.println("   Client wird beendet.");
                        TextFileWriter.systemLog("Client beendet");
                        break;
                    }

                    System.out.print("   " + output+"\n>> ");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Verbindung zum Server verloren!\n   Client wird beendet");

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

                client.close();
            }
            catch (Exception e)
            {
                TextFileWriter.writeError(e);
                System.out.println("   Es ist ein Fehler Aufgetreten!\n"+
                                   "   für weitere Infos siehe Errorlog!");
            }
        }
    }
}
