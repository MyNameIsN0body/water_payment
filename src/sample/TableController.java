package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableController implements Initializable {
// TABLE VIEW
    @FXML
    private TableView<ModelTable> allTableView;

    @FXML
    private TableColumn<ModelTable, String> IDtableColumn;

    @FXML
    private TableColumn<ModelTable, String> FIOtableColumn;

    @FXML
    private TableColumn<ModelTable, String> BalancetableColumn;

    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection connection = DBConnect.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("select * from users_water");
            while(resultSet.next()) {
                observableList.add(new ModelTable(resultSet.getString("user_id"),resultSet.getString("fio"),resultSet.getDouble("balance")));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        IDtableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        FIOtableColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        BalancetableColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        allTableView.setItems(observableList);
    }
    //  View Controller

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField middleNameTextField;

    @FXML
    private TextField balanceTextField;

//    @FXML
//    private Button addPersonButton;

    @FXML
    private TextField removePersonTextField;

//    @FXML
//    private Button removePersonButton;

    @FXML
    private void removePersonButtonAction(){
        People people = new People(new DBConnection().openConnection("postgres", "IpMan"));
        people.deletePerson(Integer.parseInt(removePersonTextField.getText()));
        removePersonTextField.clear();
        allTableView.getItems().clear();
        initialize(this.location,this.resources);
    }
    @FXML
    private void addPersonButtonAction() {
        People people = new People(new DBConnection().openConnection("postgres", "IpMan"));
        String name = surnameTextField.getText() + " " + nameTextField.getText() + " " + middleNameTextField.getText();
        double balance = Double.parseDouble(balanceTextField.getText());
        people.insertPerson(name,balance);

        surnameTextField.clear();
        nameTextField.clear();
        middleNameTextField.clear();
        balanceTextField.clear();

        allTableView.getItems().clear();
        initialize(this.location,this.resources);
    }


    public void openNewStage(ActionEvent actionEvent) throws IOException {
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setTitle("График оплаты");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("voda_02.fxml"));
        AnchorPane rootLayout = (AnchorPane) loader.load();

        Scene scene = new Scene(rootLayout);
        newStage.setScene(scene);
        newStage.show();

    }

}
