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

    private static ArrayList<String> output = new ArrayList<>();    //speichert Serverrückgaben
    private static ArrayList<String> input  = new ArrayList<>();    //speichert gesendete Eingaben

    private static int KEY = 1;

    private static PrintWriter writer  = null;
    private static BufferedReader reader = null;

    @FXML
    private TextArea outputarea;

    @FXML
    private TextField inputarea;

    @FXML
    private Button buttonsend;


    /***********************************************************************************************************************
     * GUI
     */
    @Override
    public void start(Stage stage)
    {
        try
        {
            stage.setTitle("blaue Pandas - Lagermanagementsystem");//setzt FensterTitel
            stage.getIcons().add(new Image("file:images/2.png"));//setzt Panda-Icon

             /*
                laden des FXML-Files & setzen des Controllers
             */

            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LMS.fxml"));
            loader.setController(this);
            AnchorPane LMS = loader.load();


            output.add("Client started successfully\r\n");  //add meldung das Client(GUI) erfolgreich gestartet wurde
            outputarea.setText(output.get(0));//erste Azsgabe das Cliebt erfolgreich gestartet wurde


            writer = new PrintWriter(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            /*
                Senden - Button wurde gedrückt - Event
             */

            buttonsend.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {

                    String inputStream = inputarea.getText(); //hohlen des Inputs aus dem Eingabefeld
                    inputarea.setText("");

                    if(!inputStream.isEmpty())// kontrolle ob etwas im Eingabefeld stand
                    {
                        input.add(inputStream); // hinzufügen in die input-history
                        TextFileWriter.systemLog(inputStream);  //hinzufügen in den Systemlog

                        writer.write(inputStream + "\n"); //übergabe an server
                        writer.flush();//abschicken



                        KEY = 1;//rücksetzen des KEY-wertes

                         /*
                            mit der folgenden Lösung bin ich selbst nicht ganz zufrieden da auch eine Nachricht von Server kommen könnte
                            obwohl kein Befehl gesendet wurde,
                            leider scheint die Threadlösung (siehe Client) bei GUI's nicht zu funktionieren
                         */

                        try
                        {
                            String out = reader.readLine(); // Auslesen der Serverrückgabe

                            if (!out.isEmpty())
                            {
                                output.add(out + "\r\n"); // hinzufügen in output-history

                                String print = "";

                                 /*
                                    Ausgabe der History im Output-Feld (max die letzten 17 Einträge)
                                 */

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

                                outputarea.setText(print);//Ausgabe in output-Feld
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

             /*
                Abfangen von Key-Events (Enter zum Absenden & Pfeiltasten up & Down zum navigieren in input-history)
             */

            inputarea.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent keyEvent)
                {
                     /*
                       gleche funktion wie beim drücken des Senden-Buttons, nur duch drücken der Enter-taste
                    */
                    if(keyEvent.getCode() == KeyCode.valueOf("ENTER"))
                    {
                        String inputStream = inputarea.getText();
                        inputarea.setText("");

                        if(!inputStream.isEmpty())
                        {
                            input.add(inputStream);
                            TextFileWriter.systemLog(inputStream);

                            writer.write(inputStream + "\n"); //übergabe an server
                            writer.flush();//abschicken

                            KEY = 1;//rücksetzen des KEY-wertes

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

                     /*
                        navigieren in der input-history mittels Pfeiltasten (Up & Down)
                     */
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
            stage.show(); //das eigendliche "starten" der GUI

        }
        catch (Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }

    }


    /***********************************************************************************************************************
     * Main
     *  verbindet GUI mit Sever & startet diese
     *  Server muss zum starten der GUI aktiv sein !
     */

    public static void main(String[] _args)
    {
        try
        {
            client = new Socket("localhost", 12345 ); //verbinden zum Server

            launch(_args);//starten der GUI
        }
        catch(Exception e)
        {
            TextFileWriter.writeError(e);
            System.out.println("   An error has Occurred!\n" +
                    "   for more info visit the Error Log!");
        }
    }
}