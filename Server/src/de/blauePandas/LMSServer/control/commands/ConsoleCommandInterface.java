package de.blauePandas.LMSServer.control.commands;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: André Wagenknecht
 * Date: 16.11.13
 * Time: 12:45
 *
 * Inteface zum erstellen der cmd-arraylist
 */
public interface ConsoleCommandInterface
{
    public String   getName();                  //Name des Befehls
    public String   getComand();                //Comando um befehl auszuführen
    public int      getRight();                 //Welche Rechte werden benötigt?

    public String execute(final String[] _args, final int _rights);     //das soll gemacht werden wen das comando eingegeben wird

}
