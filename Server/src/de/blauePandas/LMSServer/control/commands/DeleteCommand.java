package de.blauePandas.LMSServer.control.commands;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 20.04.14
 */
public class DeleteCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Delete";
    }

    @Override
    public String getCommand()
    {
        return "delete";
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
            if(_args.length == 3)
            {
                //todo wait for logoutfunction
            }
        }
       return "You don't have the permissions to do this";
    }
}
