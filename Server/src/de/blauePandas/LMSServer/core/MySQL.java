package de.blauePandas.LMSServer.core;

import java.sql.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 04.03.14
 */


public class MySQL
{
    Vector <Pool> pools   = new Vector<Pool>();
    String sqlDriver      = null;
    String sqlDatabase    = null;


    public MySQL(String _sqlDriver, String _sqlDatabase)
    {
        this.sqlDriver      = _sqlDriver;
        this.sqlDatabase    = _sqlDatabase;
    }


    public Connection getConnection()
    {
        Pool pool;
        Connection connection;

        for (int index = 0; index < this.pools.size(); index++)
        {
            if (!(this.pools.elementAt(index)).inUse)
            {
                pool        = this.pools.elementAt(index);
                pool.inUse  = true;

                return pool.connection;
            }
        }

        try
        {
            Class.forName(this.sqlDriver);

            connection = DriverManager.getConnection(this.sqlDatabase);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        pool = new Pool(connection, true);

        this.pools.addElement(pool);

        return connection;
    }


    public void closeConnection(Connection _connection)
    {
        try
        {
            for(int index = 0; index < this.pools.size(); index++ )
            {
                if(this.pools.equals(_connection))
                {
                    this.pools.elementAt(index).inUse = false;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

