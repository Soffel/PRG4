package de.blauePandas.LMSServer.control.commands;

import de.blauePandas.LMSServer.Server;
import de.blauePandas.LMSServer.control.ClientThread;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 06.04.14
 */
public class StopCommand implements  ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Stop";
    }


    @Override
    public String getComand()
    {
        return "stop";
    }


    @Override
    public int getRight()
    {
        return 0;
    }


    @Override
    public String execute(String[] _args, int _rights)
    {
        if (_args.length > 0)
        {
            if (_args[0].equalsIgnoreCase("-Server") || _args[0].equalsIgnoreCase("-S"))
            {
                ClientThread.StopClient();
                Server.stopServer();

                return "Server  gestoppt";
            }
            else
            {
                String args = "";

                for(int index = 0; index < _args.length; index++)
                {
                    args = args + _args[index] + " ";
                }
                return "Befehl " + this.getName() + " " + args + "nicht gefunden";
            }
        }

        ClientThread.StopClient();

        return "Verbindung getrennt";
    }
}
