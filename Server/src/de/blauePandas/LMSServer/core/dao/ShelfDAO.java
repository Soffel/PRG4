package de.blauePandas.LMSServer.core.dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.model.Item;
import de.blauePandas.LMSServer.model.Person;
import de.blauePandas.LMSServer.model.Shelf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 26.06.14
 */

public class ShelfDAO implements DAOInterface<Shelf>
{
    private static final String ID          = "shelf_id";
    private static final String NAME        = "shelf_name";
    private static final String FREEYARD    = "shelf_nfree_yard";
    private static final String MAXLOAD     = "shelf_max_load";
    private static final String FREELOAD    = "shelf_free_load";

    @Override
    public boolean insert(Shelf _t)
    {

        Connection conn = null;
        Shelf back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();

            String insert = " insert into shelf values (?, ?, ?, ?, ?)";

            PreparedStatement preStatement = conn.prepareStatement(insert);

            preStatement.setInt     (1, _t.getId());
            preStatement.setString  (2, _t.getName());
            preStatement.setInt     (3, _t.getFreeYard());
            preStatement.setInt     (4, _t.getMaxLoad());
            preStatement.setInt     (5, _t.getFreeLoad());

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
    public Shelf select(Shelf _t)

    {
        Connection conn = null;
        Shelf back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();
            String select = " select * from shelf where ";

            if(_t.getId() != 0)
            {
                select += ID + " = (?) ";
                andCount++;
            }

            if(_t.getName().equals("*"))
            {
                if(andCount > 0)
                    select += "and ";

                select += NAME +" = (?) ";
                andCount++;
            }

            if(_t.getFreeYard() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += FREEYARD + " = (?) ";
                andCount++;
            }

            if(_t.getMaxLoad() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += MAXLOAD + " = (?) ";
                andCount++;

            }

            if(_t.getFreeLoad() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += FREELOAD + " = (?) ";
                andCount++;

            }

            System.out.println(select);
            PreparedStatement preStatement = conn.prepareStatement(select);

            if(_t.getId() != 0)
            {
                preStatement.setInt(stateCount, _t.getId());
                stateCount++;
            }

            if(_t.getName().equals("*"))
            {
                preStatement.setString(stateCount, _t.getName());
                stateCount++;
            }

            if(_t.getFreeYard() != 0)
            {
                preStatement.setInt(stateCount, _t.getFreeYard());
                stateCount++;
            }

            if(_t.getMaxLoad() != 0)
            {
                preStatement.setInt(stateCount, _t.getMaxLoad());

            }

            if(_t.getFreeLoad() != 0)
            {
                preStatement.setInt(stateCount, _t.getFreeLoad());

            }

            ResultSet result = preStatement.executeQuery();

            while(result.next())
            {
                back = new Shelf(result.getInt(ID),
                                 result.getString(NAME),
                                 result.getInt(FREEYARD),
                                 result.getInt(MAXLOAD),
                                 result.getInt(FREELOAD));
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
    public boolean update(Shelf _t)
    {
        return false;
    }

    @Override
    public boolean delete(Shelf _t)
    {
        return false;
    }
}
