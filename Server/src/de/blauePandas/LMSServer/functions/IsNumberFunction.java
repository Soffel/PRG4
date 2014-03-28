package de.blauePandas.LMSServer.functions;

/**
 * Created by andre on 28.03.14.
 */
public class IsNumberFunction
{
    public static boolean isNumber(final String _args ){

        if (_args == null || _args.length() == 0 )
            return false;

        for (int index = 0; index < _args.length(); index++ ){

            if (!Character.isDigit(_args.charAt(index)))
                return false;
        }
        return true;
    }
}
