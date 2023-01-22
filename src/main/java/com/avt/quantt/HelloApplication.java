package com.avt.quantt;

import com.formdev.flatlaf.FlatDarkLaf;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
            //UIManager.put("TitlePane.unifiedBackground", false);
            //UIManager.put("TitlePane.Background", Color.RED);
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        JFrame frame = new JFrame("Penelope");
        JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel);
        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(23,180,252));
        frame.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.RED);
        frame.setVisible(true);

        printUIManagerKeys();

        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                jfxPanel.setScene(scene);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void printUIManagerKeys()
    {
        UIDefaults defaults = UIManager.getDefaults();
        Enumeration<Object> keysEnumeration = defaults.keys();
        ArrayList<Object> keysList = Collections.list(keysEnumeration);
        for (Object key : keysList)
        {
            if (key.toString().toLowerCase().contains("jrootpane")) {
                System.out.println(key);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}