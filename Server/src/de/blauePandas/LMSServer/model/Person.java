package de.blauePandas.LMSServer.model;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 27.02.14
 */
public class Person
{
    private int id;
    private int rights;
    private String name;
    private String loginName;
    private String loginPsw;


    /**
     * getter
     */
    public int getId()
    {
        return this.id;
    }

    public int getRights() { return this.rights; }

    public String getName() { return this.name; }

    public String getloginName () { return this.loginName; }

    public String getloginPsw() { return this.loginPsw; }


    /**
     * Setter
     */

    public void setId(int _id) { this.id = _id; }

    public void setRights(int _rights) { this.rights = _rights; }

    public void setName(String _name) { this.name = _name; }

    public void setLoginName(String _loginName)
    {
        this.loginName = _loginName;
    }

    public void setLoginPsw(String _loginPsw)
    {
        this.loginPsw = _loginPsw;
    }
}

