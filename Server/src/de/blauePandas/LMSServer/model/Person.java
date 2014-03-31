package de.blauePandas.LMSServer.model;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 27.02.14
 */
public class Person
{
    private String personName   = "NoLogin";
    private String userName     = "NoLogin";
    private String userPsw      = "";
    private int    userRigths   = 0;


    // benötigt login, logout, neue Person anlege, Personendaten ändern, Person löschen

    /**
     * Get Anweisungen
     */

    public String getUsername()
    {
        return this.userName;
    }

    public String getPersonName()
    {
        return this.personName;
    }

    public String getUserPsw()
    {
        return this.userPsw;
    }

    public int getUserRigths()
    {
        return this.userRigths;
    }

}
