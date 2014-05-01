package de.blauePandas.LMSServer.control;

import de.blauePandas.LMSServer.control.commands.ConsoleCommandInterface;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 19.03.14
 */
public class ConsoleControl
{
    private ArrayList<ConsoleCommandInterface> listOfCommands = new ArrayList <ConsoleCommandInterface>();

    public void addCmd(final ConsoleCommandInterface _cmd)
    {
        if (_cmd != null && !_cmd.getCommand().isEmpty() )
        {
            this.listOfCommands.add(_cmd);
        }
    }


    public void removeCmd(final String _cmd)
    {
        if (!_cmd.isEmpty())
        {
            for (int index = 1; index < this.listOfCommands.size(); index++)
            {
                if (this.listOfCommands.get(index).getCommand().equalsIgnoreCase(_cmd))
                {
                    this.listOfCommands.remove(index);
                }
            }
        }
    }


    public boolean existCmd(final String _cmd)
    {
        if (!_cmd.isEmpty())
        {
            for (ConsoleCommandInterface ccmd : this.listOfCommands)
            {
                if (ccmd.getCommand().equalsIgnoreCase(_cmd))
                {
                    return true;
                }
            }
        }
        return false;
    }


    public ConsoleCommandInterface getCmd(final String _cmd)
    {
        if (existCmd(_cmd))
        {
            for (ConsoleCommandInterface ccmd : this.listOfCommands)
            {
                if (ccmd.getCommand().equalsIgnoreCase(_cmd))
                {
                    return ccmd;
                }
            }
        }
        return null;
    }


    public String performCmd(final String _cmd, final String[] _args, final int _rights)
    {
        if (existCmd(_cmd) && this.getCmd(_cmd)!= null )
        {
            ConsoleCommandInterface  cmdDo = this.getCmd(_cmd);
            return cmdDo.execute(_args, _rights);
        }
        return "Command '" + _cmd + "' not found";
    }
}
