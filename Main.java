package Nikon;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== 欢迎访问爬虫系统 =====");
            System.out.println("作者：班***");
            System.out.println("1. 数据爬取");
            System.out.println("2. 键盘输入");
            System.out.println("3. 数据修改");
            System.out.println("4. 数据删除");
            System.out.println("5. 数据查询");
            System.out.println("6. 文件导入/导出");
            System.out.println("7. 退出");
            System.out.print("请选择（1-7）：");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Crawler.startCrawling();
                    break;
                case 2:
                    DatabaseManager.addProduct();
                    break;
                case 3:
                    DatabaseManager.modifyProduct();
                    break;
                case 4:
                    DatabaseManager.deleteProduct();
                    break;
                case 5:
                    QueryManager.queryProduct();
                    break;
                case 6:
                    FileManager.fileMenu();
                    break;
                case 7:
                    System.out.println("退出系统。");
                    return;
                default:
                    System.out.println("无效选择。");
            }
        }
    }
}