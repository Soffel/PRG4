package de.blauePandas.LMSServer.control.commands;

import de.blauePandas.LMSServer.control.ClientThread;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 20.04.14
 */
public class LogoutCommand implements ConsoleCommandInterface
{
    @Override
    public String getName()
    {
        return "Logout";
    }

    @Override
    public String getCommand()
    {
        return "logout";
    }

    @Override
    public int getRight()
    {
        return 0;
    }

    @Override
    public String execute(String[] _args, int _rights)
    {
        ClientThread.ConsoleControl.addCmd(new LoginCommand());

        ClientThread.ConsoleControl.removeCmd("delete");
        ClientThread.ConsoleControl.removeCmd("new");
        ClientThread.ConsoleControl.removeCmd("stop");
        ClientThread.ConsoleControl.removeCmd("update");
        ClientThread.ConsoleControl.removeCmd("info");

        return "You have been successfully logged out";
    }
}
