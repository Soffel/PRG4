package de.blauePandas.LMSServer.control.commands;

import de.blauePandas.LMSServer.core.dao.ItemDAO;
import de.blauePandas.LMSServer.model.Item;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 20.04.14
 */
public class NewCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "New";
    }

    @Override
    public String getCommand()
    {
        return "new";
    }

    @Override
    public int getRight()
    {
        return 0;
    }

    @Override
    public String execute(String[] _args, int _rights)
    {

    Item test = new Item(11,"testitem",100,true,10);
        ItemDAO dao = new ItemDAO();

        dao.insert(test);

        return "You don't have the permissions to do this";
    }
}
