package de.blauePandas.LMSServer.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 04.03.14
 */


public class MySQL implements ConnectInterface
{
    Vector <Pool> pools   = new Vector<Pool>();
    String sqlDriver      = "com.mysql.jdbc.Driver";
    String sqlDatabase    = "jdbc:mysql://";
    String sqlUser        = "root";         //sql username
    String sqlPsw         = "";             //sql userpasswort


    public MySQL(String _sqlDatabaseUrl)
    {

        this.sqlDatabase    = this.sqlDatabase + _sqlDatabaseUrl;
    }


    public MySQL(String _sqlDatabaseUrl, String _sqlUser, String _sqlPsw)
    {
        this.sqlDatabase    = this.sqlDatabase + _sqlDatabaseUrl;
        this.sqlUser        = _sqlUser;
        this.sqlPsw         = _sqlPsw;
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

            connection = DriverManager.getConnection(this.sqlDatabase,this.sqlUser,this.sqlPsw);
        }
        catch (Exception e)
        {
            System.out.println("mysql -- "+ e.toString());
            return null;
        }

        pool = new Pool(connection, true);

        this.pools.addElement(pool);

        return connection;
    }


    public void closeConnection(Connection _connection)//todo exception weiterreichen
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

