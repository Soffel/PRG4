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
                    if(output.equalsIgnoreCase("stop"))
                    {
                        break;
                    }

                    System.out.print(output+"\n>> ");
                }
            }
        }
        catch(Exception e)
        {
            //System.out.println("OutputThred -- " + e.toString());
            System.out.println("Client wurde beendet");
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
                System.out.println("OutputThred -finally- " + e.toString());
            }
        }
    }
}
