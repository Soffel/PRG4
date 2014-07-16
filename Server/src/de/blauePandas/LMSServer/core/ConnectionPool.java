
package de.blauePandas.LMSServer.core;

import de.blauePandas.LMSServer.control.TextFileWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 04.03.14
 */

public class ConnectionPool
{
    ArrayList<Connect> listOfConnections   = new ArrayList<>();
    String sqlDriver      = "com.mysql.jdbc.Driver";
    String sqlDatabase    = "jdbc:mysql://";
    String sqlUser        = "root";         //sql username
    String sqlPsw         = "";             //sql userpasswort


    public ConnectionPool(String _sqlDatabaseUrl)
    {
        this.sqlDatabase    = this.sqlDatabase + _sqlDatabaseUrl;
    }


    public ConnectionPool(String _sqlDatabaseUrl, String _sqlUser, String _sqlPsw)
    {
        this.sqlDatabase    = this.sqlDatabase + _sqlDatabaseUrl;
        this.sqlUser        = _sqlUser;
        this.sqlPsw         = _sqlPsw;
    }


    public Connection getConnection()
    {
        Connection connection;

        for (int index = 0; index < listOfConnections.size(); index++)
        {
            if (!listOfConnections.get(index).inUse)
            {
                listOfConnections.get(index).inUse = true;

                try
                {
                    if(!listOfConnections.get(index).connection.isClosed())
                        return listOfConnections.get(index).connection;
                    else
                        listOfConnections.remove(index);

                }
                catch (SQLException e)
                {
                    TextFileWriter.writeError(e);
                    System.out.println("   An error has Occurred!\n" +
                                       "   for more info visit the Error Log!");
                }
            }
        }

        try
        {
            Class.forName(this.sqlDriver);

            connection = DriverManager.getConnection(this.sqlDatabase,this.sqlUser,this.sqlPsw);
        }
        catch (Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                               "   for more info visit the Error Log!");
            return null;
        }

        Connect connect = new Connect(connection, true);

        this.listOfConnections.add(connect);

        return connection;
    }


    public void closeConnection(Connection _connection)
    {
        try
        {
            for(int index = 0; index < this.listOfConnections.size(); index++ )
            {
                if(this.listOfConnections.equals(_connection))
                {
                    this.listOfConnections.get(index).inUse = false;
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
