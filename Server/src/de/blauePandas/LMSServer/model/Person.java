package de.blauePandas.LMSServer.model;

import de.blauePandas.LMSServer.Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 27.02.14
 */
public class Person
{

    public void login()
    {
//zum testen werden einfach alle User ausgegeben

        try {
            Connection connection = Server.mySQL.getConnection();
            Statement statement =  connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user"); //auslesen
            while(resultSet.next())
            {
                System.out.println(resultSet.getString("user_name"));
            }
            resultSet.close();
            statement.close();
            Server.mySQL.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
