package Nikon;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("===== ��ӭ��������ϵͳ =====");
            System.out.println("���ߣ���***");
            System.out.println("1. ������ȡ");
            System.out.println("2. ��������");
            System.out.println("3. �����޸�");
            System.out.println("4. ����ɾ��");
            System.out.println("5. ���ݲ�ѯ");
            System.out.println("6. �ļ�����/����");
            System.out.println("7. �˳�");
            System.out.print("��ѡ��1-7����");

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
                    System.out.println("�˳�ϵͳ��");
                    return;
                default:
                    System.out.println("��Чѡ��");
            }
        }
    }
}