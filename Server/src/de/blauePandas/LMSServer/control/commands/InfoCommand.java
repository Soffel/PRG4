package de.blauePandas.LMSServer.control.commands;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 20.04.14
 */
public class InfoCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Info";
    }

    @Override
    public String getCommand()
    {
        return "info";
    }

    @Override
    public int getRight()
    {
        return 0;
    }

    @Override
    public String execute(String[] _args, int _rights)
    {
        if(this.getRight()<= _rights)
        {
            //todo wait for infofunktion Items,Person,Shelf,Yard
        }
        return "You don't have the permissions to do this";
    }
}
