package de.blauePandas.LMSServer.control.commands;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 19.03.14
 */
public class EchoCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Echo";
    }


    @Override
    public String getComand()
    {
        return "echo";
    }


    @Override
    public int getRight()
    {
        return 0;
    }


    @Override
    public String execute(String[] _args, int _rights)
    {
        //Darf jeder deswegenkeine Rechtekontrolle
        if (_args.length > 0)
        {
            StringBuilder stringBuilder = new StringBuilder(); // erstellt aus den getrennten eingaben wieder einen eingelnen string

            for (String msg : _args)
            {
                stringBuilder.append(msg).append(" "); // fügt string zusammen
            }
            return stringBuilder.toString();
        }
        return this.getName() + " benötigt ein Argument!";
    }
}
