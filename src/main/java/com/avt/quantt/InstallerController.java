package com.avt.quantt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class InstallerController {


    @FXML
    private VBox errorPopUp;

    @FXML
    private Label errorText;

    @FXML
    private Button openButton, createButton;

    @FXML
    private TextField pathField, nameField, groupField;

    @FXML
    protected void initialize() {
        errorPopUp.setVisible(false);
        openButton.setOnAction(event -> pathField.setText(selectPath()));
        createButton.setOnAction(event -> createProject());
    }

    private void createProject() {

        //Check if Maven is installed
        if (!isMavenInstalled()) {
            errorPopUp.setVisible(true);
            errorText.setText("Make sure that Maven is installed in this device.");
            return;
        }

        String path = pathField.getText();
        String name = nameField.getText();
        String groupId = groupField.getText();

        Process process = null;
        try {
            process = new ProcessBuilder("mvn", "archetype:generate", "-DgroupId=" + groupId, "-DartifactId=" + name, "-DarchetypeArtifactId=maven-archetype-quickstart", "-DinteractiveMode=false").directory(new File(path)).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(line);
        }
    }


    public boolean isMavenInstalled() {
        try {
            File project = new File(this.getClass().getPackage().getName().replace(".", "/"));
            String path = project.getAbsolutePath();

            path += "/7/bin/";
            String command = path+"mvn.cmd -version";

            System.out.println(command);

            Process process = Runtime.getRuntime().exec(command);
            //Process process = Runtime.getRuntime().exec("mvn -version");
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("Apache Maven")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private String selectPath() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a folder:");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }

        return null;
    }


}
