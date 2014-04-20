package de.blauePandas.LMSServer;

import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.control.TextFileWriter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 23.02.14
 *
 * Server Main
 *  - erstellt Server & wartet auf Clienten die sich mit ihn verbinden wollen
 *  - weist jeden verbundenen Client einen eigenen ClientThread hinzu
 *
 */
public class Server
{

    private static ServerSocket server = null;
    private static ExecutorService executorService = null;

   // public static ConnectionPool mySQL  = ConnectionPool.getInstance(); // erstellen eines "ConnectionPools"


    public static void stopServer()
    {
        try
        {
            //todo Connectionpool Verbindungen schliessen
            server.close();

        }
        catch(Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("");
        }
    }


    public static void main(String[] _args)
    {
        try
        {
            server          = new ServerSocket(12345);
            executorService = Executors.newFixedThreadPool(3); // max 3 Threads gleichzeitig (zum leichteren testen später dan erhöhen ! //todo max verbindungen erhöhen)

            System.out.println("Server started successfully");
            TextFileWriter.systemLog("Server started successfully");

            while (true)
            {
                try
                {
                    Socket client = server.accept();
                    executorService.execute(new ClientThread(client));
                }
                catch (IOException e)
                {
                    if(!server.isClosed()) //todo schönere lösung finden
                    {
                        TextFileWriter.writeError(e);
                        System.out.println("   An error has Occurred!\n" +
                                           "   for more info visit the Error Log!");
                    }
                }

                if(server.isClosed())
                {
                    System.out.println("Server is stopped!");
                    TextFileWriter.systemLog("Server is stopped");

                    break;
                }

            }
        }
        catch (IOException e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }
        finally
        {
            if (executorService != null)
            {
                executorService.shutdown();
            }

            try
            {
                if (server != null)
                {
                    server.close();
                }
            }
            catch (IOException e)
            {
                TextFileWriter.writeError(e);
                System.out.println("   An error has Occurred!\n" +
                        "   for more info visit the Error Log!");
            }

            if (executorService != null)
            {
                try
                {
                    if (!executorService.awaitTermination(100, TimeUnit.MICROSECONDS))
                    {
                        System.out.println("Still waiting after 100ms: calling System.exit(0)...");
                        TextFileWriter.systemLog("Still waiting after 100ms: calling System.exit(0)...");
                        System.exit(0);
                    }
                }
                catch (InterruptedException e)
                {
                    TextFileWriter.writeError(e);
                    System.out.println("   An error has Occurred!\n" +
                                       "   for more info visit the Error Log!");
                }
            }

        }
    }
}


