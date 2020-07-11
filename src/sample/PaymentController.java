package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private TextField IDUpdatePeopleTextField;



    @FXML
    private TextField balanceUpdatePeopleTextField;

    @FXML
    private Button balanceUpdatePeopleButton;
    @FXML
    protected void editPressedAction(ActionEvent event) {
        People people = new People(new DBConnection().openConnection("postgres", "IpMan"));
        people.updatePeople(Integer.parseInt(IDUpdatePeopleTextField.getText()), Double.parseDouble(balanceUpdatePeopleTextField.getText()));

        IDUpdatePeopleTextField.clear();
        balanceUpdatePeopleTextField.clear();
        Stage stage = (Stage) balanceUpdatePeopleButton.getScene().getWindow();
        stage.close();


    }

    @FXML
    private ListView<String> payment_list;

    private ObservableList<String> items = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payment_list.setItems(items);
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnect.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_water WHERE balance =(SELECT MIN(balance) FROM users_water);");

            while(resultSet.next()) {
                String ListViewRow = "ID: " + resultSet.getString(1) + " \t"+resultSet.getString(2) + " \t" + resultSet.getString(3) + " руб";
                items.add(ListViewRow);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
