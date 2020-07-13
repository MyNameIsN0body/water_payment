package sample;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ModelTable {
//    String id;
//    String fio;
//    double balance;
//
//
//    public void setUser_id(String id) {
//        this.id = id;
//    }
//
//    public void setFio(String fio) {
//        this.fio = fio;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
//
//    public ModelTable(String id, String fio, double balance) {
//        this.id = id;
//        this.fio = fio;
//        this.balance = balance;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getFio() {
//        return fio;
//    }
//
//    public double getBalance() {
//        return balance;
//    }

    private SimpleIntegerProperty id;
    private SimpleStringProperty fio;
    private SimpleStringProperty balance;


    public void setUser_id(Integer id) {
        this.id.set(id);
    }

    public void setFio(String fio) {
        this.fio.set(fio);
    }

    public void setBalance(double balance) {
        this.balance.set(String.valueOf(balance));
    }

    public ModelTable(Integer id, String fio, double balance) {
        this.id = new SimpleIntegerProperty(id);
        this.fio = new SimpleStringProperty(fio);
        this.balance = new SimpleStringProperty(String.valueOf(balance));

    }

    public Integer getId() {
        return id.getValue();
    }

    public String getFio() {
        return fio.getValue();
    }

    public String getBalance() {
        return balance.getValue();
    }
    public Observable getStringPropertyId() {
        return id;
    }
    public Observable getStringPropertyFIO() {
        return fio;
    }
    public Observable getStringPropertyBalance() {
        return balance;
    }

//    @Override
//    public String toString() {
//        return getId();
//    }
}
