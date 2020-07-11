package sample;

public class ModelTable {
    String id;
    String fio;
    double balance;

    public void setUser_id(String id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ModelTable(String id, String fio, double balance) {
        this.id = id;
        this.fio = fio;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public double getBalance() {
        return balance;
    }
}
