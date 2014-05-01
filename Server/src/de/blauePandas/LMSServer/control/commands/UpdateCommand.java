package de.blauePandas.LMSServer.control.commands;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 20.04.14
 */
public class UpdateCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Update";
    }

    @Override
    public String getCommand()
    {
        return "update";
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
            //todo wait for update item,person,shelf&yard-functions
        }
        return "You don't have the permissions to do this";
    }
}
