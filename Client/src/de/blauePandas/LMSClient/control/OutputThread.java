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
        try
        {
            InputStream input = this.client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            while(true)
            {
                String output = reader.readLine(); //entfangen von Server

                if(!output.isEmpty())
                {
                    if(output.equalsIgnoreCase("exit"))
                    {
                        break;
                    }
                    System.out.println(output);
                }
            }
            reader.close();
            client.close();
        }
        catch(Exception e)
        {
            System.out.println("OutputThred -- " + e.toString());
        }
    }
}
