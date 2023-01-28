module com.avt.quantt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.formdev.flatlaf;
    requires javafx.swing;


    opens com.avt.quantt to javafx.fxml;
    exports com.avt.quantt;
}