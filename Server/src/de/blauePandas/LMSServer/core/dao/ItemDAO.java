package de.blauePandas.LMSServer.core.dao;

import de.blauePandas.LMSServer.model.Item;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 26.06.14
 */

public class ItemDAO implements DAOInterface<Item>
{
    @Override
    public boolean insert(Item _t)
    {

        return false;
    }

    @Override
    public Item select(Item _t)
    {
   /*     Connection conn = null;

        int andCount = 0;
        int stateCount = 1;

        try
        {
          //  conn = ClientThread.pool.getConnection();
            String select = " select * from item where ";

            if(_t.getId() != 0)
            {
                select += " item_id = (?) ";
                andCount++;
            }

            if(!_t.getName().equals("*"))
            {
                if(andCount > 0)
                    select += " and ";

                select += " item_name = (?) ";
                andCount++;
            }

            if(_t.getHasDate())
            {
                if(andCount > 0)
                    select += " and ";

                select += " has_expiration_date = (?) ";
                andCount++;
            }

            if(_t.getWeight() != 0)
            {
                if(andCount > 0)
                    select += " and ";

                select += " item_weight = (?) ";
                andCount++;
            }

            PreparedStatement preStatement = conn.prepareStatement(select);

            if(_t.getId() != 0)
                preStatement.setInt(stateCount, _t.getId());

            ResultSet result = preStatement.executeQuery();

            while(result.next())
            {
                System.out.println("Loan Type: " + result.getString("loan_type"));
            }
        }
        catch(Exception e)
        {
            // Fehler ausgabe
        }
        finally
        {
           // if(conn != null)
        //        ClientThread.pool.closeConnection(conn);

        }*/
        return null;
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
}
