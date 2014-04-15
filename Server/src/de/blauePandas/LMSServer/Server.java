package de.blauePandas.LMSServer;

import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.core.ConnectionPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    /**
     * SQL-exception while adding connection to pool:
     * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost/PRG4
     */


    private static ServerSocket server = null;
    private static ExecutorService executorService = null;


    public static ConnectionPool mySQL  = ConnectionPool.getInstance(); // erstellen eines "ConnectionPools"


    public static void stopServer()
    {
        try
        {
            executorService.shutdown();
            server.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void main(String[] _args)
    {
        try
        {
            server          = new ServerSocket(12345);
            executorService = Executors.newFixedThreadPool(3); // max 3 Threads gleichzeitig (zum leichteren testen später dan erhöhen ! //todo max verbindungen erhöhen)

            System.out.println("Server gestartet!");

            while (true)
            {
                try
                {
                    Socket client = server.accept();
                    executorService.execute(new ClientThread(client));
                }
                catch (IOException e)
                {
                    System.out.println("Server -inWhileschleife- " + e.toString());
                }
            }
        }
        catch (IOException e)
        {
            System.out.print("Server -- " + e.toString());
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
                e.printStackTrace();
            }
        }
    }
}


