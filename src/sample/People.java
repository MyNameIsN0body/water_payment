package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class People {
    private Statement statement = null;
    private Connection connection = null;

    private int id;
    private String fio;
    private double balance;

    public People(Connection connection) {
        this.connection = connection;
    }

    public void insertPerson(String fullName, double balance) {
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO users_water (fio,balance) " + "VALUES ('" + fullName + "'," + balance + ");";
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            connection.commit();
            connection.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public ResultSet selectAllPerson() {
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_water;");
            while (resultSet.next()) {
                id = resultSet.getInt("user_id");
                fio = resultSet.getString("fio");
                balance = resultSet.getDouble("balance");
                System.out.println("ID " + id);
                System.out.println("FIO " + fio);
                System.out.println("Balance " + balance);

                System.out.println();
                return resultSet;
            }
            resultSet.close();
            statement.close();
            connection.close();


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
        System.out.println("Selection done successfully");
        return null;

    }

    ///////////>>>>>>>>>>>>>>>>>>>>>>>
    public ResultSet candidatesPaymentPerson() {
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_water WHERE balance =(SELECT MIN(balance) FROM user_water);");
            while (resultSet.next()) {
                id = resultSet.getInt("user_id");
                fio = resultSet.getString("fio");
                balance = resultSet.getDouble("balance");
                System.out.println("ID " + id);
                System.out.println("FIO " + fio);
                System.out.println("Balance " + balance);
                System.out.println();
                return resultSet;
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
        System.out.println("Selection candidates done successfully");
        return null;
    }

    //////////////<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void deletePerson(int id) {
        try {
            statement = connection.createStatement();
            String sql = "DELETE FROM users_water WHERE user_id ="+id+";";
            System.out.println(sql);
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Deletion done successfully with ID=" + id);
    }
    public void updatePeople (int ID, double balance) {
        try
        {
            statement = connection.createStatement();
            String sql = "UPDATE users_water SET  balance = balance + '" + balance + "' WHERE user_id="+ID+";";
            statement.executeUpdate(sql);
            connection.commit();

            statement.close();
            connection.close();
        }
        catch ( Exception e )
        {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println("Updating of ID's=" + ID + " balance=" + balance + " done successfully");
    }

}
