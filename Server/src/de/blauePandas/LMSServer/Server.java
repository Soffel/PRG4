package de.blauePandas.LMSServer;

import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.core.MySQL;

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
 */
public class Server
{
    static MySQL mySQL  = new MySQL("org.gjt.mm.mysql.Driver", "jdbc:mysql://localhost/prgjava"); // erstellen eines "ConnectionPools"

    public static void main(String[] _args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // max 3 Threads gleichzeitig (zum leichteren testen später dan erhöhen ! //todo max verbindungen erhöhen)

        try
        {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Server gestartet!");

            while(true)
            {
                try
                {
                    Socket client = server.accept();
                    executorService.execute(new ClientThread(client));
                }
                catch(IOException e)
                {
                    System.out.print("4");
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e)
        {
            System.out.print("Server -- " + e.toString());
        }
    }
}


//TODO connectionpool testen
