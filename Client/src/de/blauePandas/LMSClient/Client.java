package de.blauePandas.LMSClient;

import de.blauePandas.LMSClient.control.InputThread;
import de.blauePandas.LMSClient.control.OutputThread;
import de.blauePandas.LMSClient.control.TextFileWriter;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 23.02.14
 */

public class Client
{
    public static void stopClient()
    {
        System.exit(0);
    }

    public static void main(String[] _args)
    {
        try
        {
            Socket client = new Socket("localhost", 12345 ); //verbindung zum server herstellen

            System.out.print("   Client started successfully \n>> ");
            TextFileWriter.systemLog("Client started successfully");

            // starten der in & output-threads
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            executorService.execute(new InputThread(client));
            executorService.execute(new OutputThread(client));

        }
        catch(Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                               "   for more info visit the Error Log!");
        }
    }
}