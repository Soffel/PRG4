package de.blauePandas.LMSServer;

import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.core.MySQL;
import de.blauePandas.LMSServer.model.Person;

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
    public static MySQL mySQL  = new MySQL("localhost/prgjava","root",""); // erstellen eines "ConnectionPools"


    public static void main(String[] _args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // max 3 Threads gleichzeitig (zum leichteren testen später dan erhöhen ! //todo max verbindungen erhöhen)

        try
        {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Server gestartet!");

            Person person = new Person();

            person.login();


            while(true)
            {
                try
                {
                    Socket client = server.accept();
                    executorService.execute(new ClientThread(client));
                }
                catch(IOException e)
                {
                    System.out.println("Server -inWhileschleife- " + e.toString());
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
