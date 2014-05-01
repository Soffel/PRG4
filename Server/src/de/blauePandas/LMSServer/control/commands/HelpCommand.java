package de.blauePandas.LMSServer.control.commands;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 20.04.14
 */
public class HelpCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Help";
    }

    @Override
    public String getCommand()
    {
        return "help";
    }

    @Override
    public int getRight()
    {
        return 0;
    }

    @Override
    public String execute(String[] _args, int _rights)
    {
        //todo wait for cmd's
        return null;
    }
}
