![QNBRAND](https://github.com/JoseLuisSaizLopez/QuanttNative/blob/master/qnative-logo.png)

# Quantt Native

Librería para Java, utiliza un espacio entre el ensamblaje nativo del sistema operativo y el marco JVM para cambiar la decoración de la barra de título


## ¿Cómo funciona?
Utiliza el marco de composición Java Swing para acceder a la decoración nativa del S.O, luego renderiza en su interior una Scene de la suit JavaFX para aumentar la customización y continuar diseñando la App a través de este framework.

<br><br>

## QuickStart

Para empezar a utilizar QuanttNative, descargue el archivo .jar y añádalo como librería dentro de su proyecto de JavaFX.
[Download last release](https://github.com/JoseLuisSaizLopez/QuanttNative/releases/tag/V1.1)

<br>

### Maven

Si usted está utilizando Maven, dentro del archivo pom.xml deberá establecer la ruta del archivo dentro de su proyecto:
```xml
    <repositories>
        <repository>
            <id>quantt-repo</id> <!--example id-->
            <url>file://${project.basedir}/lib</url> <!--example path-->
        </repository>
    </repositories>
```

Y también añada la dependencia de esta forma:
```xml
        <dependency>
            <groupId>com.avt</groupId>
            <artifactId>quantt</artifactId>
            <version>1.1-Alphabet</version>
        </dependency>
```

<br>

#### Module system

Si aparte de Maven, usted trabaja con un sistema modular de dependencias, deberá añadir esta línea dentro de su archivo module-info.java

```java
requires com.avt.quantt;
```

<br>

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
<br><br>

## Guía de estilos

Esta es una pequeña introducción para configurar la estética de la ventana

### TitleBar

- Background Color
```java 
    //new QuantNative()...
    .add(QuanttNative.ParamKey.TITLEBAR_BACKGROUND_COLOR, new Color(0x463088))
    //..
```

- Foreground Color
```java 
    //new QuantNative()...
      .add(QuanttNative.ParamKey.TITLEBAR_FOREGROUND_COLOR, new Color(0xC4C1DA))
    //..
```

- Title Text
```java 
    //new QuantNative()...
      .add(QuanttNative.ParamKey.TITLE_STRING, "yourtitle")
    //..
```

- Image icon
```java 
    //new QuantNative()...
      .add(ParamKey.ICON_IMAGE, yourIMAGEicon);
    //..
```

- Frame initial size
```java 
    //new QuantNative()...
    .add(QuanttNative.ParamKey.SIZE_DIMENSION, new Dimension(width, height))
    //..
```

<br><br>

## Crear un menu de acceso rápido

Si desea incluir un menu de acceso rápido dentro de su aplicación, pruebe a seguir este ejemplo durante la creación de su aplicación:

```java
        //Load QNative
        QuanttNative qNative = new QuanttNative(
                //Menu composer
                new QuanttNative.QTitleMenu()
                        .addMenu("File", new QuanttNative.Option[]{
                                new QuanttNative.Option("Create new project"),
                                new QuanttNative.Option("Open project"),
                                new QuanttNative.Option("Close project", false),            //Disabled
                                new QuanttNative.Option("Save", false),                     //Disabled
                                new QuanttNative.Option("Save as...", false),               //Disabled
                                new QuanttNative.Option("Exit")
                        })
                        .addMenu("Edit", new QuanttNative.Option[]{
                                new QuanttNative.Option("Undo last", false),                //Disabled
                                new QuanttNative.Option("Redo last", false),                //Disabled
                                new QuanttNative.Option("Change color...", false),          //Disabled
                                new QuanttNative.Option("Edit configurations...", false)    //Disabled
                        })
                        .addMenu("Help", new QuanttNative.Option[]{
                                new QuanttNative.Option("Open web support", true)
                        })
        );

        qNative.getTitleMenu().addMenuOptionListener((key, option) -> {
            switch (option) {
                case "Exit": System.exit(0); break;
                default: System.out.println(option); break;
            }
        });
```

![menu-composer](https://github.com/JoseLuisSaizLopez/QuanttNative/blob/master/menu-composer.png)


<br><br>

## Demo

Este es un ejemplo de un marco creado con Quantt Native en Windows 11
![demo](https://github.com/JoseLuisSaizLopez/QuanttNative/blob/master/qnative-demo.png)
