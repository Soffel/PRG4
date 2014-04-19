package de.blauePandas.LMSClient.control;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 19.04.14
 */
public class TextFileWriter
{
    private static java.io.FileWriter writer;
    private static File file;

    private static String ERROR_FILE    = "ClientError.txt";
    private static String LOG_FILE      = "ClientSystemlog.txt";

    private static String StringBuild(Exception _error)
    {
        StringBuilder stringBuilder         = new StringBuilder();
        Date currentTime                    = new Date();
        SimpleDateFormat formatter          = new SimpleDateFormat ("yyyy.MM.dd 'at' HH:mm:ss ");
        StackTraceElement[] errorStackTrace = _error.getStackTrace();

        stringBuilder = stringBuilder.append("################################################################################\r\n# ")
                                     .append(formatter.format(currentTime))
                                     .append("\r\n# \r\n# Exception type: ")
                                     .append(_error.toString()).append("\r\n#\r\n# ");

        for (StackTraceElement aSte : errorStackTrace)
        {
            stringBuilder = stringBuilder.append(aSte).append("\r\n# ");
        }

        stringBuilder = stringBuilder.append("\r\n################################################################################\r\n");

        return stringBuilder.toString();
    }

    public static void writeError(Exception _error)
    {
        file = new File(ERROR_FILE);

        try
        {
            writer = new java.io.FileWriter(file ,true);

            writer.write(StringBuild(_error));
            writer.write(System.getProperty("line.separator"));

            writer.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void systemLog(String _log)
    {
        file = new File(LOG_FILE);

        Date currentTime            = new Date();
        SimpleDateFormat formatter  = new SimpleDateFormat ("yyyy.MM.dd 'at' HH:mm:ss ");

        try
        {
            writer = new java.io.FileWriter(file ,true);

            writer.write(formatter.format(currentTime) + " >> " + _log);

            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
