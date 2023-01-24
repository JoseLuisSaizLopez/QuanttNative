package com.avt.quantt;

import com.formdev.flatlaf.FlatDarkLaf;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class QuanttNative {



    /*
    ==========================================
    =  Properties                            =
    ==========================================
    */

    private JFrame rootPane;
    private Scene scene;
    private Dimension size = new Dimension(600, 320);
    private QParams params;



    /*
    ==========================================
    =  Constructors                          =
    ==========================================
    */

    public QuanttNative() {
        setDefaultUIStyle();
        setCustomUIStyle();
        rootPane = new JFrame();
    }

    public QuanttNative(String title) {
        setDefaultUIStyle();
        setCustomUIStyle();
        params.params.put(ParamKey.TITLE_STRING, title);
    }

    public QuanttNative(QParams params) {
        setDefaultUIStyle();
        System.out.println(params.getImage(ParamKey.ICON_IMAGE));
        setCustomUIStyle();
        this.params.params.putAll(params.params);
    }



    /*
    ==========================================
    =  DTO                                   =
    ==========================================
    */

    public void loadScene(URL fxmlURL) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            scene = new Scene(fxmlLoader.load(), size.width, size.height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setSize(int width, int height) {
        if (rootPane!=null) {
            rootPane.setSize(width, height);
        }
        params.params.put(ParamKey.SIZE_DIMENSION, new Dimension(width, height));
    }

    public void setTitle(String title) {
        if (rootPane!=null) {
            rootPane.setTitle(title);
        }
        params.params.put(ParamKey.TITLE_STRING, title);
    }

    public QuanttNative setTitleBarBackground(Color color) {
        params.params.put(ParamKey.TITLEBAR_BACKGROUND_COLOR, color);
        return this;
    }

    public QuanttNative setTitleBarForeground(Color color) {
        params.params.put(ParamKey.TITLEBAR_FOREGROUND_COLOR, color);
        return this;
    }



    /*
    ==========================================
    =  Functions                             =
    ==========================================
    */

    private void setDefaultUIStyle() {
        try {
            URL url = this.getClass().getResource("quantt-logo-32px.png");
            Image icon = ImageIO.read(url);
            params = new QParams()
                    .add(ParamKey.SIZE_DIMENSION, new Dimension(300,400))
                    .add(ParamKey.TITLE_STRING, "Quantt Application")
                    .add(ParamKey.TITLEBAR_BACKGROUND_COLOR, new Color(0xFF2F3962))
                    .add(ParamKey.TITLEBAR_FOREGROUND_COLOR, new Color(0xD1D7DC))
                    .add(ParamKey.ICON_IMAGE, icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCustomUIStyle() {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
            UIManager.put("TitlePane.showIcon", true);
            UIManager.put("TitlePane.iconSize", new Dimension(20,20));
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    public void start() {
        //Create rootPane
        rootPane = new JFrame(params.getString(ParamKey.TITLE_STRING));

        //Create viewport
        JFXPanel jfxPanel = new JFXPanel();
        rootPane.add(jfxPanel);
        rootPane.getRootPane().putClientProperty("JRootPane.titleBarBackground", params.getColor(ParamKey.TITLEBAR_BACKGROUND_COLOR));
        rootPane.getRootPane().putClientProperty("JRootPane.titleBarForeground", params.getColor(ParamKey.TITLEBAR_FOREGROUND_COLOR));
        rootPane.getRootPane().putClientProperty("TitlePane.showIcon", Boolean.TRUE);
        rootPane.setIconImage(params.getImage(ParamKey.ICON_IMAGE));
        rootPane.setVisible(true);
        rootPane.setSize(params.getDimension(ParamKey.SIZE_DIMENSION));

        //Add scene
        Platform.runLater(() -> jfxPanel.setScene(scene));
    }



    /*
    ==========================================
    =  Params                                =
    ==========================================
    */
    public static class QParams {
        private Map<ParamKey,Object> params = new HashMap<>();

        public QParams add(ParamKey key, Object value) {
            params.put(key, value);
            return this;
        }

        private String getString(ParamKey key) {
            Object value = params.get(key);
            if (value instanceof String) {
                return (String) value;
            } else {
                return "";
            }
        }

        private Dimension getDimension(ParamKey key) {
            Object value = params.get(key);
            if (value instanceof Dimension) {
                return (Dimension) value;
            } else {
                return new Dimension(0,0);
            }
        }

        private Color getColor(ParamKey key) {
            Object value = params.get(key);
            if (value instanceof Color) {
                return (Color) value;
            } else {
                return Color.BLACK;
            }
        }

        private Image getImage(ParamKey key) {
            Object value = params.get(key);
            if (value instanceof Image) {
                return (Image) value;
            } else {
                return null;
            }
        }

    }

    //Supported keys
    public enum ParamKey {
        TITLEBAR_BACKGROUND_COLOR,
        TITLEBAR_FOREGROUND_COLOR,
        TITLE_STRING,
        SIZE_DIMENSION,
        ICON_IMAGE
    }

}
