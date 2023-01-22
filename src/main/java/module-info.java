module com.avt.quantt {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.formdev.flatlaf;
    requires java.desktop;
    requires javafx.swing;


    opens com.avt.quantt to javafx.fxml;
    exports com.avt.quantt;
}