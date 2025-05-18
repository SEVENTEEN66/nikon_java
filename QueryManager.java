package Nikon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class QueryManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/nikon?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sjh061223,,";

    public static void queryProduct() {
        String querySQL = "SELECT * FROM products";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querySQL)) {

            System.out.println("���в�Ʒ��");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("����: " + resultSet.getString("name"));
                System.out.println("�۸�: " + resultSet.getString("price"));
                System.out.println("����: " + resultSet.getString("details"));
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("��ѯ��Ʒʱ����");
        }
    }
}
