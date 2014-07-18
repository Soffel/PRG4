package de.blauePandas.LMSServer.core.dao;

import de.blauePandas.LMSServer.control.ClientThread;
import de.blauePandas.LMSServer.model.Item;
import de.blauePandas.LMSServer.model.Person;

import javax.lang.model.element.Name;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 26.06.14
 */

public class PersonDAO implements DAOInterface<Person>
{
    private static final String ID          = "user_id";
    private static final String RIGHTS      = "user_rights";
    private static final String NAME        = "user_name";
    private static final String LOGINNAME   = "user_loginname";
    private static final String LOGINPSW    = "user_loginpsw";

    @Override
    public boolean insert(Person _t)
    {

        Connection conn = null;
        Person back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();

            String insert = " insert into person values (?, ?, ?, ?, ?)";

            PreparedStatement preStatement = conn.prepareStatement(insert);

            preStatement.setInt     (1, _t.getId());
            preStatement.setInt     (2, _t.getRights());
            preStatement.setString  (3, _t.getName());
            preStatement.setString  (4, _t.getloginName());
            preStatement.setString  (5, _t.getloginPsw());

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
    public Person select(Person _t)
    {
        Connection conn = null;
        Person back = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
            conn = ClientThread.getPool().getConnection();
            String select = " select * from person where ";

            if(_t.getId() != 0)
            {
                select += ID + " = (?) ";
                andCount++;
            }

            if(_t.getRights() != 0)
            {
                if(andCount > 0)
                    select += "and ";

                select += RIGHTS +" = (?) ";
                andCount++;
            }

            if(_t.getName().equals("*"))
            {
                if(andCount > 0)
                    select += "and ";

                select += NAME + " = (?) ";
                andCount++;
            }

            if(_t.getloginName().equals("*"))
            {
                if(andCount > 0)
                    select += "and ";

                select += LOGINNAME + " = (?) ";
                andCount++;
            }

            if(_t.getloginPsw().equals("*"))
            {
                if(andCount > 0)
                    select += "and ";

                select += LOGINPSW + " = (?) ";
                andCount++;

            }

            System.out.println(select);
            PreparedStatement preStatement = conn.prepareStatement(select);

            if(_t.getId() != 0)
            {
                preStatement.setInt(stateCount, _t.getId());
                stateCount++;
            }

            if(_t.getRights() != 0)
            {
                preStatement.setInt(stateCount, _t.getRights());
                stateCount++;
            }

            if(_t.getName().equals("*"))
            {
                preStatement.setString(stateCount, _t.getName());
                stateCount++;
            }

            if(_t.getloginName().equals("*"))
            {
                preStatement.setString(stateCount, _t.getloginName());
                stateCount++;
            }

            if(_t.getloginPsw().equals("*"))
            {
                preStatement.setString(stateCount, _t.getloginPsw());
                stateCount++;
            }

            ResultSet result = preStatement.executeQuery();

            while(result.next())
            {
                back = new Person(result.getInt(ID),
                                  result.getInt(RIGHTS),
                                  result.getString(NAME),
                                  result.getString(LOGINNAME),
                                  result.getString(LOGINPSW));
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
    public boolean update(Person _t)
    {
        return false;
    }

    @Override
    public boolean delete(Person _t)
    {
        return false;
    }

    @Override
    public boolean exist(Person _t) {
        return false;
    }
}
