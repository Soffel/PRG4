package de.blauePandas.LMSServer.model;

import de.blauePandas.LMSServer.Server;
import de.blauePandas.LMSServer.core.MySQL;
import de.blauePandas.LMSServer.functions.IsNumberFunction;

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
    private String personName   = "NoLogin";
    private String userName     = "NoLogin";
    private String userPsw      = "";
    private int    userRigths   = 0;


    /**
     * Funktion zum einloggen eine in der Datenbank angelegten Person
     *      -kontrolle ob User existiert / das Passwort korrekt ist
     *
     * @param _userName Loginname
     * @param _userPsw  Loginpsw
     * @return          erfolgs / misserfolgsmeldung (String)
     */
    public String login( final String _userName, final String _userPsw)
    {
        String[] args = {_userName,_userPsw};
        ResultSet result;

        try
        {
            result = Server.mySQL.doThisAndResult("select",args);
        }
        catch (Exception e)
        {
            return "Error: "+ e.toString();
        }

        // todo result auswerten & in Person eintragen (Session ersatz)

        return (result != null)? "jetzt als "+_userName+" eingeloggt.":
                                 "login nicht erfolgreich!";
    }


    /**
     * Funktion zum rücksetzen der Personendaten (ausloggen)
     *
     * @return erfolgsmeldung (String)
     */
    public String logout()
    {
        this.personName = "NoLogin";
        this.userName = "NoLogin";
        this.userPsw = "";
        this.userRigths = 0;

        return "Logout erfolgreich.";
    }


    /**
     * Funktion zum anlegen neuer Personen / Mitarbeiter in die Datenbank
     *
     * @param _args übergabe der Personendaten, einfacherhalber als Stringarray
     * @return erfolgsmeldung / misserfolgsmeldung (String)
     */
    public String newPerson(String[] _args)
    {
       try
       {
            Server.mySQL.doThis("INSERT INTO user(user_name,user_login_name,user_login_psw,user_rights) VALUES (?,?,?,?)", _args);
       }
       catch(Exception e)
       {
            System.out.println("Person -newPerson- "+ e.toString());
            return "Error: "+ e.toString();
       }

        return _args[0]+" erfolreich anglegt.";
    }


    /**
     * Funktion zum löschen der ind er Datenbank angelegtern Personen
     *
     * @param _args übergabe der Personendaten, einfacherhalber als Stringarray
     * @return erfolgsmeldung / misserfolgsmeldung (String)
     */
    public String deletePerson (String[] _args)
    {
        try
        {
            Server.mySQL.doThis("delete", _args); //todo deleteanweisung komplett
            //todo Exception erstellen
        }
        catch(Exception e)
        {
            System.out.println("Person -deletePerson- "+ e.toString());
            return "Error: "+ e.toString();
        }

        return _args[0]+" erfolreich gelöscht.";
    }

    /**
     * Funktion zum verändern der bereits angelegten Userdaten
     *
     *@param _args übergabe der Personendaten, einfacherhalber als Stringarray
     * @return erfolgsmeldung / misserfolgsmeldung (String)
     */
    public String updatePerson(String[] _args)
    {
        try
        {
            Server.mySQL.doThis("update", _args); //todo updateanweisung komplett
            //todo Exception erstellen
        }
        catch(Exception e)
        {
            System.out.println("Person -updatePerson- "+ e.toString());
            return "Error: "+ e.toString();
        }

        return _args[0]+" erfolreich geändert.";
    }

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
