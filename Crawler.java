package Nikon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Crawler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/nikon?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sjh061223,,";

    public static void startCrawling() {
        String url = "https://www.nikon.com.cn/sc_CN/products/catalogue.page?lang="; // ʾ��URL
        try {
            // ��ȡ��ҳ����
            Document doc = Jsoup.connect(url).get();
            Elements products = doc.select(".product-item"); // ����ʵ��HTML�ṹ����ѡ����

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String insertSQL = "INSERT INTO products (name, price, details) VALUES (?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(insertSQL);

                for (Element product : products) {
                    String name = product.select(".product-name").text();
                    String price = product.select(".product-price").text();
                    String details = product.select(".product-details").text();

                    System.out.println("��ȡ����Ʒ��");
                    System.out.println("����: " + name);
                    System.out.println("�۸�: " + price);
                    System.out.println("����: " + details);
                    System.out.println("----------------------");

                    // �����ݲ������ݿ�
                    pstmt.setString(1, name);
                    pstmt.setString(2, price);
                    pstmt.setString(3, details);
                    pstmt.addBatch(); // �������
                }

                pstmt.executeBatch(); // ִ����������
                System.out.println("���в�Ʒ�ѳɹ��������ݿ⣡");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("�������ݿ�ʱ����");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("������ȡʧ�ܣ�");
        }
    }
}