
# Quantt Native

Librería para Java, utiliza un espacio entre el ensamblaje nativo del sistema operativo y el marco JVM para cambiar la decoración de la barra de título


## ¿Cómo funciona?
Utiliza el marco de composición Java Swing para acceder a la decoración nativa del S.O, luego renderiza en su interior una Scene de la suit JavaFX para aumentar la customización y continuar diseñando la App a través de este framework.
## QuickStart

Para empezar a utilizar QuanttNative, descargue el archivo .jar y añádalo como librería dentro de su proyecto de JavaFX.

Una vez añadido, utilice la clase QuanttNative para generar una ventana nativa del sistema con la personalización que desee.

```java
public class QuanttDemo extends Application {

    @Override
    public void start(Stage stage) {
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        
        //Code to run QuanttNative
        QuanttNative qNative = new QuanttNative(
                new QuanttNative.QParams()
                        .add(QuanttNative.ParamKey.TITLE_STRING, "qNativeDemo")
                        .add(QuanttNative.ParamKey.SIZE_DIMENSION, new Dimension(720, 900))
                        .add(QuanttNative.ParamKey.TITLEBAR_BACKGROUND_COLOR, new Color(0x463088))
                        .add(QuanttNative.ParamKey.TITLEBAR_FOREGROUND_COLOR, new Color(0xC4C1DA))
        );
        
        //Set your javaFX view here
        qNative.loadScene("hello-view.fxml");
        
        //Start the view component
        qNative.start();
        
        //////////////////////////////////////////////////////////////////////////////////////////////
    }

    public static void main(String[] args) {
        launch();
    }
}
```
