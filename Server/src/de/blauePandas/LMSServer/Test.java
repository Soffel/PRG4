package de.blauePandas.LMSServer;

import de.blauePandas.LMSServer.core.MySQL;
import de.blauePandas.LMSServer.model.Person;

/**
 * Created by andre on 24.03.14.
 */
public class Test
{
    public static void main(String[] args)
    {
        Person person = new Person();
        MySQL mysql = new MySQL("localhost/prgjava","root","");

        String[] insert = {"Andre Wagenknecht","ANWA","passwort", "4"};

        System.out.println(person.newPerson(insert));



    }
}
