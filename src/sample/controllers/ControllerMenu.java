package sample.controllers;

import sample.DataBase;
import java.sql.Connection;



public class ControllerMenu {
    Connection connection;

    public ControllerMenu() {
        connection = DataBase.connection();
    }

    public static String changeTable = "";

}

