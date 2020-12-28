package sample.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DataBase;
import sample.model.Person;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerNewCourses {
    Connection connection;
    public ControllerNewCourses(){
        connection = DataBase.connection();
    }
    String table = ControllerMenu.changeTable;


    @FXML
    public void export(){
        try {
            List<Person> list = new ArrayList<>();
            String sql = "select * from " + table;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Person person = new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                list.add(person);
            }
            File csv = new File("table.csv");
            csv.createNewFile();
            PrintWriter fw = new PrintWriter(csv, "windows-1251");
            for (Person person: list) {
                fw.write(person.toString());
            }
            fw.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void exit (){
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/Exit.fxml"));
            primaryStage.setTitle("Ошибка!");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




