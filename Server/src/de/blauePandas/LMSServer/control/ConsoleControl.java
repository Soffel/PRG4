package de.blauePandas.LMSServer.control;

import de.blauePandas.LMSServer.control.commands.ConsoleCommandInterface;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 19.03.14
 */
public class ConsoleControl
{
    private ArrayList<ConsoleCommandInterface> listOfCommands = new ArrayList <ConsoleCommandInterface>();

    private static final String MSG_INFO =   "  [-INFO-]  ";
    private static final String MSG_WARN =   "  [-WARN-]  ";
    private static final String MSG_ERROR =  "  [-ERROR-] ";


    public void addCmd(final ConsoleCommandInterface _cmd)//neues Commando hinzufügen
    {
        if(_cmd != null)//prüfen das comando nicht null ist
        {
            if(!_cmd.getComand().isEmpty())//prüfen ob im cmd leer ist
            {
                this.listOfCommands.add(_cmd);  //cmd hinzufügen
            }
        }
    }


    public  void removeCmd(final String _cmd)// Comando löschen
    {
        if(!_cmd.isEmpty())
        {
            for(int index = 1; index < this.listOfCommands.size(); index++)//das zu löschende comando in arraylist suchen
            {
                if(this.listOfCommands.get(index).getComand().equalsIgnoreCase(_cmd))//wen gefunden
                {
                    this.listOfCommands.remove(index);    //dan löschen
                }
            }
        }
    }


    public boolean existCmd(final String _cmd)  //prüfen ob befehl existiert
    {
        if(!_cmd.isEmpty())//wen _Cmd nicht leer
        {
            for(ConsoleCommandInterface ccmd : this.listOfCommands)//Array durchlaufen & befehl raushohlen
            {
                if(ccmd.getComand().equalsIgnoreCase(_cmd))//überprüfen ob cmds gleich sind
                {
                    return true; // ja es existiert
                }
            }
            //Konsole.ConsoleOut.writeError("Befehl '" + _cmd + "' nicht gefunden"); todo
        }
        return false; //nein es existiert nicht
    }


    public ConsoleCommandInterface getCmd(final String _cmd)
    {
        if(existCmd(_cmd))
        {
            for(ConsoleCommandInterface ccmd : this.listOfCommands)//Array mit Comandos durchlaufen
            {

                if(ccmd.getComand().equalsIgnoreCase(_cmd))//wen Befehl gefunden
                {
                    return ccmd;          //gespeicherten befehl ausgeben ( kann ma auch mit eingegebenen machen, aber ist eig egal)
                }
            }
        }
        return null;     // null wen nicht gefunden
    }


    public String performCmd(final String _cmd, final String[] _args, final int _rights)// eigegebenes Comando ausführen
    {
        if(existCmd(_cmd)) //prüfen ob existiert(siehe weiter oben)
        {
            if(this.getCmd(_cmd)!= null)
            {
                ConsoleCommandInterface  cmdDo = this.getCmd(_cmd);
                return cmdDo.execute(_args, _rights);  //befehl ausführen
            }
        }
        return MSG_ERROR+" Command '" + _cmd + "' not found";
    }





}
