package de.blauePandas.LMSServer;

import de.blauePandas.LMSServer.core.dao.ItemDAO;
import de.blauePandas.LMSServer.model.Item;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 16.07.2014
 */
public class Test
{



    public static void main(String[] _args)
    {

        Item test = new Item();

        test.setName("test");

        ItemDAO dao = new ItemDAO();

        dao.select(test);
    }


}
