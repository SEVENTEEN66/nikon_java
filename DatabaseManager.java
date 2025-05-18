package Nikon;

import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/nikon?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sjh061223,,";

    static {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS products (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                                    "name VARCHAR(255) NOT NULL," +
                                    "price VARCHAR(50)," +
                                    "details TEXT)";
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("�������Ʒ���ƣ�");
        String name = scanner.nextLine();
        System.out.print("�������Ʒ�۸�");
        String price = scanner.nextLine();
        System.out.print("�������Ʒ���飺");
        String details = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertSQL = "INSERT INTO products (name, price, details) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, name);
            pstmt.setString(2, price);
            pstmt.setString(3, details);
            pstmt.executeUpdate();
            System.out.println("��Ʒ��ӳɹ���");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������Ҫ�޸ĵĲ�Ʒ���ƣ�");
        String name = scanner.nextLine();
        System.out.print("�������µļ۸�");
        String price = scanner.nextLine();
        System.out.print("�������µ����飺");
        String details = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateSQL = "UPDATE products SET price = ?, details = ? WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(updateSQL);
            pstmt.setString(1, price);
            pstmt.setString(2, details);
            pstmt.setString(3, name);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("��Ʒ�޸ĳɹ���");
            } else {
                System.out.println("δ�ҵ�ָ����Ʒ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������Ҫɾ���Ĳ�Ʒ���ƣ�");
        String name = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String deleteSQL = "DELETE FROM products WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(deleteSQL);
            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("��Ʒɾ���ɹ���");
            } else {
                System.out.println("δ�ҵ�ָ����Ʒ��");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getAllProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectSQL = "SELECT * FROM products";
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(selectSQL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}