package de.blauePandas.LMSClient;

import de.blauePandas.LMSClient.control.TextFileWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * Team: blaue Pandas
 * User: Andre Wagenknecht
 * Date: 15.07.14
 */


public class GUI extends Application
{
    private static Socket client = null;

    private static ArrayList<String> output = new ArrayList<>();
    private static ArrayList<String> input  = new ArrayList<>();

    private static int KEY = 1;

    private static PrintWriter writer  = null;
    private static BufferedReader reader = null;

    @FXML
    private TextArea outputarea;

    @FXML
    private TextField inputarea;

    @FXML
    private Button buttonsend;


    @Override
    public void start(Stage stage)
    {
        try
        {
            output.add("Client started successfully\r\n");

            stage.setTitle("blaue Pandas - Lagermanagementsystem");
            stage.getIcons().add(new Image("file:images/2.png"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LMS.fxml"));
            loader.setController(this);
            AnchorPane LMS = loader.load();



            outputarea.setText(output.get(0));

            writer = new PrintWriter(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            buttonsend.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    String inputStream = inputarea.getText();

                    if(!inputStream.isEmpty())
                    {
                        input.add(inputStream);
                        TextFileWriter.systemLog(inputStream);

                        writer.write(inputStream + "\n"); //übergabe an server
                        writer.flush();//abschicken

                        try
                        {
                            String out = reader.readLine();

                            if (!out.isEmpty())
                            {
                                output.add(out + "\r\n");

                                String print = "";

                                if (output.size() >= 17)
                                    for (int index = output.size() - 17; index < output.size(); index++)
                                    {
                                        print += output.get(index);
                                    }
                                else
                                    for (String anOutput : output)
                                    {
                                        print += anOutput;
                                    }

                                outputarea.setText(print);
                            }
                        }
                        catch (IOException e)
                        {
                            TextFileWriter.writeError(e);
                            System.out.println("   An error has Occurred!\n" +
                                               "   for more info visit the Error Log!");
                        }
                    }
                }
            });

            inputarea.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent keyEvent)
                {
                   if(keyEvent.getCode() == KeyCode.valueOf("ENTER"))
                   {
                       String inputStream = inputarea.getText();

                       if(!inputStream.isEmpty())
                       {
                           input.add(inputStream);
                           TextFileWriter.systemLog(inputStream);

                           writer.write(inputStream + "\n"); //übergabe an server
                           writer.flush();//abschicken

                           try
                           {
                               String out = reader.readLine();

                               if (!out.isEmpty())
                               {
                                   output.add(out + "\r\n");

                                   String print = "";

                                   if (output.size() >= 17)
                                       for (int index = output.size() - 17; index < output.size(); index++)
                                       {
                                           print += output.get(index);
                                       }
                                   else
                                       for (String anOutput : output)
                                       {
                                           print += anOutput;
                                       }

                                   outputarea.setText(print);
                               }
                           }
                           catch (IOException e)
                           {
                               TextFileWriter.writeError(e);
                               System.out.println("   An error has Occurred!\n" +
                                                  "   for more info visit the Error Log!");
                           }
                       }
                   }

                   if(keyEvent.getCode() == KeyCode.valueOf("UP"))
                   {
                        if(KEY < input.size())
                            KEY++;

                        if(input.size() > 1)
                            inputarea.setText(input.get(input.size()-KEY));
                   }


                   if(keyEvent.getCode() == KeyCode.valueOf("DOWN"))
                   {
                        if(KEY > 1)
                            KEY--;

                        if(input.size() > 1)
                            inputarea.setText(input.get(input.size()-KEY));
                   }

                    /* TESTAUSGABE

                    System.out.println(keyEvent.getCode());
                    System.out.println("character='" + keyEvent.getCharacter() + "'");

                    */
                }
            });

            stage.setScene(new Scene(LMS));
            stage.show();

        }
        catch (Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                               "   for more info visit the Error Log!");
        }

    }


    public static void main(String[] _args)
    {
        try
        {
            client = new Socket("localhost", 12345 );

            launch(_args);
        }
        catch(Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                               "   for more info visit the Error Log!");
        }
    }
}