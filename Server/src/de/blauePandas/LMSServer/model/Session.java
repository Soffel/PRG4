package de.blauePandas.LMSServer.model;


/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: André Wagenknecht
 *       Martin Guzien
 * Date: 28.11.13
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */
public class Session {

    private int     UserID = 0;
    private String  Username = "NOLOG";
    private int     Rights = 0;



    public void setUserID(final int _id)
    {
        this.UserID = _id;
    }

    public void setUsername(final String _username)
    {
        this.Username = _username;
    }

    public void setRights(final int _rights)
    {
        this.Rights = _rights;
    }

    public int getUserID()
    {
        return this.UserID;
    }

    public String getUsername()
    {
        return this.Username;
    }

    public int getRights()
    {
        return this.Rights;
    }


    //Ausgabe welcher User derzeit eingeloggt ist
    public String logedIn()
    {
       return "Momentan ist eingeloggt: " + this.getUsername();
    }
}
