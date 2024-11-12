package Dequy;

import java.util.Scanner;

public class MainDeQuy {
    public boolean showMenu() {
        menuDeQuy menu = new menuDeQuy();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║      ✨ MENU ĐỆ QUY ✨        	 ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ 1. Bài toán Đệ quy             ║");
            System.out.println("║ 2. Bài toán Khử đệ quy         ║");
            System.out.println("║ 0. Thoát                       ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.print("Mời bạn chọn: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                menu.menuDQ();
            } else if (choice == 2) {
                menu.menuKhuDQ();
            } else if (choice == 0) {
                System.out.println("Quay lại menu chính.");
                return true;
            } else {
                System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        }
    }
}
