package de.blauePandas.LMSClient.control;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 24.02.14
 */

public class InputThread implements Runnable
{
    private Socket client = null;


    public InputThread(Socket _client)
    {
        this.client = _client;
    }


    @Override
    public void run()
    {
        OutputStream output = null;
        PrintWriter writer  = null;
        Scanner eingabe     = null;

        try
        {
            output  = this.client.getOutputStream();
            writer  = new PrintWriter(output);
            eingabe = new Scanner(System.in);

            while (true)
            {
                String input = null;

                if (eingabe.hasNextLine())
                {
                    input = eingabe.nextLine();
                }

                if (input != null)
                {
                    TextFileWriter.systemLog(input);
                    writer.write(input + "\n"); //übergabe an server
                    writer.flush();             //abschicken
                }
            }
        }
        catch (Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   Es ist ein Fehler Aufgetreten!\n" +
                               "   für weitere Infos siehe Errorlog!");
        }
        finally
        {
            try
            {
                if (output != null)
                {
                    output.close();
                }

                if (eingabe != null)
                {
                    eingabe.close();
                }

                if (writer != null)
                {
                    writer.close();
                }

                if (client != null)
                {
                    client.close();
                }
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

