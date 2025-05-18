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
        String url = "https://www.nikon.com.cn/sc_CN/products/catalogue.page?lang="; // 示例URL
        try {
            // 爬取网页内容
            Document doc = Jsoup.connect(url).get();
            Elements products = doc.select(".product-item"); // 根据实际HTML结构调整选择器

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String insertSQL = "INSERT INTO products (name, price, details) VALUES (?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(insertSQL);

                for (Element product : products) {
                    String name = product.select(".product-name").text();
                    String price = product.select(".product-price").text();
                    String details = product.select(".product-details").text();

                    System.out.println("爬取到产品：");
                    System.out.println("名称: " + name);
                    System.out.println("价格: " + price);
                    System.out.println("详情: " + details);
                    System.out.println("----------------------");

                    // 将数据插入数据库
                    pstmt.setString(1, name);
                    pstmt.setString(2, price);
                    pstmt.setString(3, details);
                    pstmt.addBatch(); // 批量添加
                }

                pstmt.executeBatch(); // 执行批量插入
                System.out.println("所有产品已成功存入数据库！");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("存入数据库时出错！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数据爬取失败！");
        }
    }
}