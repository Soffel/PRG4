package de.blauePandas.LMSClient;

import de.blauePandas.LMSClient.control.InputThread;
import de.blauePandas.LMSClient.control.OutputThread;
import de.blauePandas.LMSClient.control.TextFileWriter;

import java.net.Socket;


/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 23.02.14
 */
public class Client
{
    public static void main(String[] _args)
    {
        try
        {
            Socket client = new Socket("localhost", 12345 );

            System.out.println("   Client gestartet");
            TextFileWriter.systemLog("Client gestartet");

            InputThread input   = new InputThread(client);
            OutputThread output = new OutputThread(client);

            new Thread(input).start();
            new Thread(output).start();
        }
        catch(Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   Es ist ein Fehler Aufgetreten!\n" +
                               "   für weitere Infos siehe Errorlog!");
        }
    }
}
