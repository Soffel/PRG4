package de.blauePandas.LMSServer.core.dao;

import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 26.06.14
 */

public class ItemDAO implements DAOInterface<Item>
{
    private static final String ID      = "item_id";
    private static final String NAME    = "item_name";
    private static final String WEIGHT  = "item_weight";
    private static final String DATE    = "has_expiration_date";
    private static final String NUMBER  = "item_number";



    @Override
    public boolean insert(Item _t)
    {

        Connection conn = null;
        Item back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();

            String insert = " insert into item values (?, ?, ?, ?, ?)";

            PreparedStatement preStatement = conn.prepareStatement(insert);

            preStatement.setInt     (1, _t.getId());
            preStatement.setString  (2, _t.getName());
            preStatement.setInt     (3, _t.getWeight());
            preStatement.setBoolean (4, _t.getHasDate());
            preStatement.setInt     (5, _t.getNumber());

            preStatement.execute();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            if(conn != null)
                ClientThread.getPool().closeConnection(conn);
        }
        return true;
    }

    @Override
    public Item select(Item _t)
    {
        Connection conn = null;
        Item back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();
            String select = " select * from item where ";

            if(_t.getId() != 0)
            {
                select += ID + " = (?) ";
                andCount++;
            }

            if(!_t.getName().equals("*"))
            {
                if(andCount > 0)
                    select += "and ";

                select += NAME +" = (?) ";
                andCount++;
            }

            if(_t.getHasDate())
            {
                if(andCount > 0)
                    select += "and ";

                select += DATE + " = (?) ";
                andCount++;
            }

            if(_t.getWeight() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += WEIGHT + " = (?) ";
                andCount++;
            }

            if(_t.getNumber() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += NUMBER + " = (?) ";
                andCount++;

            }

            System.out.println(select);
            PreparedStatement preStatement = conn.prepareStatement(select);

            if(_t.getId() != 0)
            {
                preStatement.setInt(stateCount, _t.getId());
                stateCount++;
            }

            if(!_t.getName().equals("*"))
            {
                preStatement.setString(stateCount, _t.getName());
                stateCount++;
            }

            if(_t.getHasDate())
            {
                preStatement.setBoolean(stateCount, _t.getHasDate());
                stateCount++;
            }

            if(_t.getWeight() != 0)
            {
                preStatement.setInt(stateCount, _t.getWeight());
                stateCount++;
            }

            if(_t.getNumber() != 0)
            {
                preStatement.setInt(stateCount, _t.getNumber());

            }

            ResultSet result = preStatement.executeQuery();

            while(result.next())
            {
                back = new Item(result.getInt(ID),
                                result.getString(NAME),
                                result.getInt(WEIGHT),
                                result.getBoolean(DATE),
                                result.getInt(NUMBER));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(conn != null)
                ClientThread.getPool().closeConnection(conn);

        }

        return back;
    }

    @Override
    public boolean update(Item _t)
    {
        return false;
    }

    @Override
    public boolean delete(Item _t)
    {
        return false;
    }

    @Override
    public boolean exist(Item _t) {
        return false;
    }
}
