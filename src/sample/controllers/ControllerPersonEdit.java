package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import sample.DataBase;
import sample.model.Person;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerPersonEdit {
    Connection connection = null;

    public ControllerPersonEdit() {
        connection = DataBase.connection();
    }

    String table = ControllerMenu.changeTable;
    ObservableList<Person> listCourse = FXCollections.observableArrayList();
    ObservableList<Person> check = FXCollections.observableArrayList();
    ObservableList<String> list = FXCollections.observableArrayList("java", "python","php");

    String date1 = "";
    String date2 = "";
    String date3 = "";
    String date4 = "";
    String fullName = "";

    int id;
    boolean cout = true;

    @FXML
    private ComboBox<String> comboBoxCourse;
    @FXML
    public TableView<Person> tableView;
    @FXML
    private Label labelCourse;
    @FXML
    public TableColumn<Person, String> columnFullName;
    @FXML
    public TableColumn<Person, String> columnDate1;
    @FXML
    public TableColumn<Person, String> columnDate2;
    @FXML
    public TableColumn<Person, String> columnDate3;
    @FXML
    public TableColumn<Person, String> columnDate4;
    @FXML
    public Label highlight;
    @FXML
    public Button exit;
    @FXML
    public Button save;

    private String courseName;

    public void actionComboBoxCourse() throws IOException {

        if (listCourse != null) {
            for (int i = 0; i < listCourse.size(); i++) {
                listCourse.remove(i--);
            }
        }

        labelCourse.setText(comboBoxCourse.getValue());
        courseName = comboBoxCourse.getValue();
        try {
            String sqlSelect = "SELECT * FROM journal WHERE course = ?";
            PreparedStatement statement = connection.prepareStatement(sqlSelect);
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listCourse.add(new Person(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        comboBoxCourse.setItems(list);
        columnFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        columnDate1.setCellValueFactory(new PropertyValueFactory<>("date1"));
        columnDate1.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        columnDate1.setOnEditCommit((TableColumn.CellEditEvent<Person,String> event) -> {
            TablePosition<Person, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            Person course = event.getTableView().getItems().get(row);
            course.setDate1(newFullName);
        });

        columnDate2.setCellValueFactory(new PropertyValueFactory<>("date2"));
        columnDate2.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        columnDate2.setOnEditCommit((TableColumn.CellEditEvent<Person,String> event) -> {
            TablePosition<Person, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            Person course = event.getTableView().getItems().get(row);
            course.setDate2(newFullName);
        });
        columnDate3.setCellValueFactory(new PropertyValueFactory<>("date3"));
        columnDate3.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        columnDate3.setOnEditCommit((TableColumn.CellEditEvent<Person,String> event) -> {
            TablePosition<Person, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            Person course = event.getTableView().getItems().get(row);
            course.setDate3(newFullName);
        });
        columnDate4.setCellValueFactory(new PropertyValueFactory<>("date4"));
        columnDate4.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        columnDate4.setOnEditCommit((TableColumn.CellEditEvent<Person,String> event) -> {
            TablePosition<Person, String> pos = event.getTablePosition();
            String newFullName = event.getNewValue();
            int row = pos.getRow();
            Person course = event.getTableView().getItems().get(row);
            course.setDate4(newFullName);
        });
        tableView.setEditable(true);
        tableView.setItems(listCourse);
        getInformation(null);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                getInformation(newValue));
    }



    public void save(ActionEvent event) throws IOException {
        try {
            Person person = tableView.getSelectionModel().getSelectedItem();
            String sqlUpdate = "UPDATE journal SET date1 = ?, date2 = ?, date3 = ?, date4 = ? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sqlUpdate);
            statement.setString(1, person.getDate1());
            statement.setString(2, person.getDate2());
            statement.setString(3, person.getDate3());
            statement.setString(4, person.getDate4());
            statement.setInt(5, person.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void exit() {
        if (date1.isEmpty() && date2.isEmpty() && date3.isEmpty() && date4.isEmpty() || cout) {
            exit.getScene().getWindow().hide();
        } else {
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

    public void getInformation(Person person) {
        if (person != null) {
            System.out.println(person.getId() + person.getFullName() + person.getDate1() + person.getDate2() + person.getDate3() + person.getDate4());
            date1 = person.getDate1();
            date2 = person.getDate2();
            date3 = person.getDate3();
            date4 = person.getDate4();
            fullName = person.getFullName();
            id = person.getId();
            highlight.setText("");
            cout = false;
        }
    }
}


