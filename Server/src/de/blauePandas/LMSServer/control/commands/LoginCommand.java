package de.blauePandas.LMSServer.control.commands;

import de.blauePandas.LMSServer.control.ClientThread;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 20.04.14
 */
public class LoginCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Login";
    }

    @Override
    public String getCommand()
    {
        return "login";
    }

    @Override
    public int getRight()
    {
        return 0;
    }

    @Override
    public String execute(String[] _args, int _rights)
    {
        ClientThread.ConsoleControl.addCmd(new LogoutCommand());

        //todo wait for other cmd's

        ClientThread.ConsoleControl.removeCmd("login");
        return null;
    }
}
