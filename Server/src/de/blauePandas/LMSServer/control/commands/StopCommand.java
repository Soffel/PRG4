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
    public String getCommand()
    {
        return "stop";
    }


    @Override
    public int getRight()
    {
        return 99;
    }


    @Override
    public String execute(String[] _args, int _rights)
    {
        if (_args.length > 0)
        {
            if(this.getRight() <= _rights)
            {
                if (_args[0].equalsIgnoreCase("-Server") || _args[0].equalsIgnoreCase("-S"))
                {
                    ClientThread.StopClient();
                    Server.stopServer();

                    return "stopped server";
                }

                String args = "";

                for (String _arg : _args)
                {
                    args = args + _arg + " ";
                }
                return "command " + this.getName() + " " + args + " no found!";
            }
            return "You don't have the permissions to do this";
        }

        ClientThread.StopClient();

        return "interrupt connection";
    }
}
