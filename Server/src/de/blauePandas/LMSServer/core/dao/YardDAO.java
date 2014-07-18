package de.blauePandas.LMSServer.core.dao;

import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.model.Item;
import de.blauePandas.LMSServer.model.Yard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 26.06.14
 */

public class YardDAO implements DAOInterface<Yard>
{

    private int id;
    private int itemId;
    private int shelfId;
    private int numberOfItems;
    private int posHori;
    private int posVerti;
    private boolean occupied;

    @Override
    public boolean insert(Yard _t)

    {

        Connection conn = null;
        Item back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();

            String insert = " insert into yard values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preStatement = conn.prepareStatement(insert);

            preStatement.setInt     (1, _t.getId());
            preStatement.setInt     (2, _t.getItemId());
            preStatement.setInt     (3, _t.getShelfId());
            preStatement.setInt     (4, _t.getNumberOfItems());
            preStatement.setInt     (5, _t.getPosHori());
            preStatement.setInt     (6, _t.getPosVerti());
            preStatement.setBoolean (7, _t.getoccupied());

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
        return false;
    }
    @Override
    public Yard select(Yard _t)

    {
        Connection conn = null;
        Item back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();
            String select = " select * from yard where ";

            if(_t.getId() != 0)
            {
                select += ID + " = (?) ";
                andCount++;
            }

            if(_t.getItemId() != 0)
            {
                select += ITEMID + " = (?) ";
                andCount++;
            }

            if(_t.getShelfId() != 0)
            {
                select += SHELFID + " = (?) ";
                andCount++;
            }

            if(_t.getNumberOfItems() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += NUMBEROFITEMS + " = (?) ";
                andCount++;
            }

            if(_t.getPosHori() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += POSHORI + " = (?) ";
                andCount++;
            }

            if(_t.getPosVerti() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += POSVERTI + " = (?) ";
                andCount++;
            }

            if(_t.getoccupied())
            {
                if(andCount > 0)
                    select += "and ";

                select += OCCUPIED + " = (?) ";
                andCount++;
            }

            System.out.println(select);
            PreparedStatement preStatement = conn.prepareStatement(select);

            if(_t.getId() != 0)
            {
                preStatement.setInt(stateCount, _t.getId());
                stateCount++;
            }

            if(_t.getItemId() != 0)
            {
                preStatement.setInt(stateCount, _t.getItemId());
                stateCount++;
            }

            if(_t.getShelfId() != 0)
            {
                preStatement.setInt(stateCount, _t.getShelfId());
                stateCount++;
            }

            if(_t.getNumberOfItems() != 0)
            {
                preStatement.setInt(stateCount, _t.getNumberOfItems());
                stateCount++;
            }

            if(_t.getPosHori() != 0)
            {
                preStatement.setInt(stateCount, _t.getPosHori());

            }

            if(_t.getPosVerti() != 0)
            {
                preStatement.setInt(stateCount, _t.getPosVerti());

            }

            if(_t.getoccupied())
            {
                preStatement.setBoolean(stateCount, _t.getoccupied());
                stateCount++;
            }

            ResultSet result = preStatement.executeQuery();

            while(result.next())
            {
                back = new Item(result.getInt(ID),
                                result.getInt(ITEMID),
                                result.getInt(SHELFID),
                                result.getInt(NUMBEROFITEMS),
                                result.getInt(POSHORI),
                                result.getString(POSVERTI),
                                result.getBoolean(OCCUPIED));
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

        return null;
    }

    @Override
    public boolean update(Yard _t)
    {
        return false;
    }

    @Override
    public boolean delete(Yard _t)
    {
        return false;
    }
}
