package sample.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerExit {


    @FXML
    public Button cancel;

    @FXML
    public void cancel(){
        cancel.getScene().getWindow().hide();
    }
    @FXML
    public void exit(){
        Platform.exit();
    }
}
