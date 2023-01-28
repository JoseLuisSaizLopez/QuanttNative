package com.avt.quantt;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.ui.FlatPopupMenuBorder;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private QTitleMenu titleMenu;
    private QCoordinate coordinate;
    private Map<Node, TitlePropertiesComposer> titleComposers = new HashMap<>();



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
        params.params.put(ParamKey.TITLE_STRING, title);
        setCustomUIStyle();
    }

    public QuanttNative(QParams params) {
        setDefaultUIStyle();
        this.params.params.putAll(params.params);
        setCustomUIStyle();
    }

    public QuanttNative(QParams params, QTitleMenu menu) {
        setDefaultUIStyle();
        this.params.params.putAll(params.params);
        this.titleMenu = menu;
        setCustomUIStyle();
    }

    public QuanttNative(QTitleMenu menu) {
        setDefaultUIStyle();
        this.titleMenu = menu;
        setCustomUIStyle();
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

    public Scene getScene() {
        return this.scene;
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

    public QTitleMenu getTitleMenu() {
        return this.titleMenu;
    }

    public QParams getParams() {
        return this.params;
    }

    public void setX(int x) {
        this.coordinate.setX(x);
    }

    public void setY(int y) {
        this.coordinate.setY(y);
    }

    public void setPosition(int x, int y) {
        this.coordinate.setXY(x, y);
    }



    /*
    ==========================================
    =  Functions                             =
    ==========================================
    */

    public void exit(int status) {
        rootPane.setVisible(false);
        System.exit(status);
    }

    private void setDefaultUIStyle() {
        try {
            URL url = this.getClass().getResource("quantt-logo-32px.png");
            Image icon = ImageIO.read(url);
            params = new QParams()
                    .add(ParamKey.SIZE_DIMENSION, new Dimension(300,400))
                    .add(ParamKey.TITLE_STRING, "Quantt Application")
                    .add(ParamKey.TITLEBAR_BACKGROUND_COLOR, new Color(0xFF2F3962))
                    .add(ParamKey.TITLEBAR_FOREGROUND_COLOR, new Color(0xD1D7DC))
                    .add(ParamKey.ICON_IMAGE, icon)
                    .add(ParamKey.CENTER_ON_SCREEN_BOOLEAN, true)
                    .add(ParamKey.RESIZABLE_BOOLEAN, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCustomUIStyle() {
        try {

            //Title bar
            UIManager.setLookAndFeel( new FlatDarkLaf() );
            UIManager.put("TitlePane.showIcon", true);
            UIManager.put("TitlePane.iconSize", new Dimension(20,20));
            UIManager.put("TitlePane.menuBarEmbedded", true);

            //Foreground color
            Color foregroundColor = createForegroundColor((Color) params.params.get(ParamKey.TITLEBAR_BACKGROUND_COLOR));

            //PopUp menu
            UIManager.put("PopupMenu.borderInsets", new Insets(0,0,0,0));
            UIManager.put("PopupMenu.borderColor", new Color(0x5CFFFFFF, true)); //transparent
            UIManager.put("PopupMenu.border", new FlatPopupMenuBorder());

            //Menu bar
            UIManager.put("MenuBar.foreground", params.params.get(ParamKey.TITLEBAR_FOREGROUND_COLOR)); //transparent
            UIManager.put("MenuBar.hoverBackground", new Color(0x0000000, true)); //transparent
            UIManager.put("MenuBar.selectionBackground", getSimilarColor((Color) params.params.get(ParamKey.TITLEBAR_BACKGROUND_COLOR), 0.1f, -0.3f));
            UIManager.put("MenuBar.selectionForeground", params.params.get(ParamKey.TITLEBAR_FOREGROUND_COLOR));

            //Menu item
            UIManager.put("MenuItem.opaque", true);
            UIManager.put("MenuItem.foreground",  getSimilarColor((Color) params.params.get(ParamKey.TITLEBAR_FOREGROUND_COLOR), 0.05f, 0f));
            UIManager.put("MenuItem.background", getSimilarColor((Color) params.params.get(ParamKey.TITLEBAR_BACKGROUND_COLOR), 0.05f, -0.3f));
            UIManager.put("MenuItem.selectionBackground", getSimilarColor((Color) params.params.get(ParamKey.TITLEBAR_BACKGROUND_COLOR), 0.1f, -0.1f));
            UIManager.put("MenuItem.selectionForeground", params.params.get(ParamKey.TITLEBAR_FOREGROUND_COLOR));
            UIManager.put("MenuItem.disabledForeground", getSimilarColor((Color) params.params.get(ParamKey.TITLEBAR_FOREGROUND_COLOR), 0.2f, 0.1f, 0.35f));



        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    public void start() {
        //Create rootPane
        rootPane = new JFrame(params.getString(ParamKey.TITLE_STRING));

        //Create embed menu bar
        if (titleMenu!=null && !titleMenu.menus.isEmpty()) {
            JMenuBar menuBar = new JMenuBar();
            for (Map.Entry<String,Option[]> menu: titleMenu.menus.entrySet()) {
                menuBar.add(titleMenu.createMenu(menu));
            }
            //Add event listener
            implementMenuOptionListener(menuBar);
            rootPane.setJMenuBar(menuBar);
        }

        //Set resizable
        rootPane.setResizable(Boolean.TRUE.equals(params.getBoolean(ParamKey.RESIZABLE_BOOLEAN)));


        //Create viewport
        JFXPanel jfxPanel = new JFXPanel();
        rootPane.add(jfxPanel);
        rootPane.getRootPane().putClientProperty("JRootPane.titleBarBackground", params.getColor(ParamKey.TITLEBAR_BACKGROUND_COLOR));
        rootPane.getRootPane().putClientProperty("JRootPane.titleBarForeground", params.getColor(ParamKey.TITLEBAR_FOREGROUND_COLOR));
        rootPane.getRootPane().putClientProperty("TitlePane.showIcon", Boolean.TRUE);
        rootPane.setIconImage(params.getImage(ParamKey.ICON_IMAGE));
        rootPane.setVisible(true);
        rootPane.setSize(params.getDimension(ParamKey.SIZE_DIMENSION));

        //Set center on screen
        if (Boolean.TRUE.equals(params.getBoolean(ParamKey.CENTER_ON_SCREEN_BOOLEAN))) {
            rootPane.setLocationRelativeTo(null);
        }

        //Set position by coordinates
        coordinate = params.getQCoordinate(ParamKey.POSITION_QCOORDINATE);
        if (coordinate!=null) {
            rootPane.setLocation(coordinate.x, coordinate.y);
        } else {
            coordinate = new QCoordinate(rootPane.getX(), rootPane.getY());
        }
        rootPane.addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                coordinate.x = e.getComponent().getX();
                coordinate.y = e.getComponent().getY();
            }
        });
        coordinate.setOnChangedListener((x, y) -> rootPane.setLocation(x, y));


        //Add scene
        Platform.runLater(() -> jfxPanel.setScene(scene));
    }

    public void addTitleProperties(Node node) {
        titleComposers.put(node, new TitlePropertiesComposer(node, rootPane));
    }

    public void removeTitleProperties(Node node) {
        node.setOnMousePressed(null);
        node.setOnMouseDragged(null);
        titleComposers.remove(node);
    }


    /*
    ==========================================
    =  Title properties                      =
    ==========================================
    */
    public static class TitlePropertiesComposer {

        private int x, y;

        public TitlePropertiesComposer(Node node, JFrame rootPane) {
            //For moving
            node.setOnMousePressed(event -> {
                x = (int) event.getSceneX();
                y = (int) event.getSceneY();
            });
            node.setOnMouseDragged(event -> {
                int x2 = (int) event.getScreenX();
                int y2 = (int) event.getScreenY();

                double width = node.getBoundsInLocal().getWidth();
                double height = node.getBoundsInLocal().getHeight();

                rootPane.setLocation((int) (x2 - x), (int) (y2 - y - height));
            });
        }

    }


    /*
    ==========================================
    =  Color composers                       =
    ==========================================
    */
    private boolean isColorLight(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        double brightness = (0.299*r + 0.587*g + 0.114*b) / 255;

        return brightness >= 0.5;
    }

    private Color createForegroundColor(Color backgroundColor) {
        boolean isBackgroundLight = isColorLight(backgroundColor);
        if (isBackgroundLight) {
            return new Color(0x1A1A1A);
        } else {
            return new Color(0xF3F3F3);
        }
    }

    private Color getSimilarColor(Color backgroundColor, float thresholdBrightness, float thresholdSaturation) {
        int r = backgroundColor.getRed();
        int g = backgroundColor.getGreen();
        int b = backgroundColor.getBlue();

        double brightness = (0.299*r + 0.587*g + 0.114*b) / 255;

        if (brightness < 0.5) {
            r = (int) (r * (1 + thresholdBrightness));
            g = (int) (g * (1 + thresholdBrightness));
            b = (int) (b * (1 + thresholdBrightness));
        } else {
            r = (int) (r * (1 - thresholdBrightness));
            g = (int) (g * (1 - thresholdBrightness));
            b = (int) (b * (1 - thresholdBrightness));
        }

        Color colorBrightness = new Color(r,g,b);

        float[] hsb = Color.RGBtoHSB(colorBrightness.getRed(), colorBrightness.getGreen(), colorBrightness.getBlue(), null);
        hsb[1] = Math.min(1, Math.max(0, hsb[1] + thresholdSaturation));

        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }

    private Color getSimilarColor(Color backgroundColor, float thresholdBrightness, float thresholdSaturation, float alpha) {
        Color modified = getSimilarColor(backgroundColor, thresholdBrightness, thresholdSaturation);

        int r = backgroundColor.getRed();
        int g = backgroundColor.getGreen();
        int b = backgroundColor.getBlue();

        return new Color(r,g,b, alpha);
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

        private Boolean getBoolean(ParamKey key) {
            Object value = params.get(key);
            if (value instanceof Boolean) {
                return (boolean) value;
            } else {
                return null;
            }
        }

        private QCoordinate getQCoordinate(ParamKey key) {
            Object value = params.get(key);
            if (value instanceof QCoordinate) {
                return (QCoordinate) value;
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
        ICON_IMAGE,
        CENTER_ON_SCREEN_BOOLEAN,
        POSITION_QCOORDINATE,
        RESIZABLE_BOOLEAN
    }


    /*
    ==========================================
    =  Coordinates                           =
    ==========================================
    */
    public static class QCoordinate {

        private int x, y;
        private CoordinateChanged onChangedListener;

        public QCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
            callChanged();
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
            callChanged();
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
            callChanged();
        }

        private void callChanged() {
            if (onChangedListener!=null) {
                onChangedListener.changed(x, y);
            }
        }

        private void setOnChangedListener(CoordinateChanged listener) {
            this.onChangedListener = listener;
        }

        private interface CoordinateChanged {
            void changed(int x, int y);
        }

    }


    /*
    ==========================================
    =  MENU OPTIONS                          =
    ==========================================
    */

    public static class QTitleMenu {

        private LinkedHashMap<String, Option[]> menus = new LinkedHashMap<>();

        private MenuOptionListener menuListener;

        public QTitleMenu() {}

        public QTitleMenu addMenu(String menuTitle, Option[] options) {
            menus.put(menuTitle, options);
            return this;
        }

        private JMenu createMenu(Map.Entry<String, Option[]> menu) {
            JMenu menuPane = new JMenu(menu.getKey());

            if (menu.getValue()!=null && menu.getValue().length>0) {
                for (Option option:menu.getValue()) {
                    menuPane.add(option.getPane());
                    //Set icon
                }
            }
            return menuPane;
        }

        public QTitleMenu addMenuOptionListener(MenuOptionListener listener) {
            this.menuListener = listener;
            return this;
        }

        public Option[] getMenuOptions(String title) {
            return this.menus.get(title);
        }

        public LinkedHashMap<String, Option[]> getMenus() {
            return this.menus;
        }

    }

    public static class Option {

        private String name;
        private boolean enabled = true;
        private JMenuItem optionPane;
        private Icon icon;

        public Option(String name) {
            this.name = name;
        }

        public Option(String name, boolean isEnabled) {
            this.name = name;
            enabled = isEnabled;
        }

        public Option(String name, Image icon, boolean isEnabled) {
            this.name = name;
            enabled = isEnabled;
            this.icon = (Icon) icon;
        }

        public Option(String name, Image icon) {
            this.name = name;
            this.icon = new ImageIcon(icon);
        }

        private JMenuItem getPane() {
            if (optionPane==null) {
                optionPane=new JMenuItem(name);
                optionPane.setEnabled(enabled);
                if (icon!=null) {
                    optionPane.setIcon(icon);
                }
            }
            return optionPane;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
            optionPane.setVisible(enabled);
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    private void implementMenuOptionListener(JMenuBar menuBar) {
        if (titleMenu!=null && !titleMenu.menus.isEmpty()) {
            if (titleMenu.menuListener!=null) {
                for (int i = 0; i < menuBar.getMenuCount(); i++) {
                    JMenu menu = menuBar.getMenu(i);
                    for (int j = 0; j < menu.getItemCount(); j++) {
                        JMenuItem item = menu.getItem(j);
                        item.addActionListener((e)->titleMenu.menuListener.onOptionClicked(menu.getText(), item.getText()));
                    }
                }
            }
        }
    }

    public interface MenuOptionListener {
        void onOptionClicked(String key, String option);
    }

}
