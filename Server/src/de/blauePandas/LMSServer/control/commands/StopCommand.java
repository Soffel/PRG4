package de.blauePandas.LMSServer.control.commands;

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
        if(_args.length > 0)
        {
            if(_args[0].equalsIgnoreCase("-Server") || _args[0].equalsIgnoreCase("-S"))
            {
                return "Server  gestoppt";
            }
        }
        else
        {
            ClientThread.StopClient();
        }
        return "Server nicht gestoppt";
    }
}
