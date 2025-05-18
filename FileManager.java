package Nikon;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FileManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/nikon?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sjh061223,,";

    public static void fileMenu() {
        System.out.println("1. �������ļ�");
        System.out.println("2. ���ļ�����");
        System.out.print("��ѡ��");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                exportToFile();
                break;
            case 2:
                importFromFile();
                break;
            default:
                System.out.println("��Чѡ��");
        }
    }

    public static void exportToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"));
             Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectSQL = "SELECT * FROM products";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSQL);

            while (resultSet.next()) {
                writer.write(resultSet.getString("name") + "," +
                             resultSet.getString("price") + "," +
                             resultSet.getString("details"));
                writer.newLine();
            }
            System.out.println("�����ѵ�����products.txt��");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"));
             Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String insertSQL = "INSERT INTO products (name, price, details) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(insertSQL);
                    pstmt.setString(1, parts[0]);
                    pstmt.setString(2, parts[1]);
                    pstmt.setString(3, parts[2]);
                    pstmt.executeUpdate();
                }
            }
            System.out.println("�����Ѵ�products.txt���룡");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}