package Main;

import Dequy.MainDeQuy;
import dslkdoi.Main;
import tree.Menutree;
import graph.DoThiMenu; // Import lớp Dothimenu
import java.util.Scanner;

public class menu {
    public static void main(String[] args) {
        int luaChon;
        Scanner sc = new Scanner(System.in);
        MainDeQuy deQuy = new MainDeQuy();
        dslkdoi.Main list = new dslkdoi.Main();
        tree.Menutree treeMenu = new tree.Menutree();
        DoThiMenu doThiMenu = new DoThiMenu(); // Khởi tạo lớp Dothimenu

        // Viền menua
        String vienTrenDuoi = "╔════════════════════════════════════════════╗";
        String vienGiua = "╠════════════════════════════════════════════╣";
        String vienDong = "║                                            ║";

        do {
            System.out.println("\n" + vienTrenDuoi);
            System.out.println("║                 MENU CHÍNH                 ║");
            System.out.println(vienGiua);
            System.out.println(vienDong);
            System.out.println("║  1. Các bài toán đệ quy (khử đệ quy)       ║");
            System.out.println("║  2. Danh sách liên kết đôi - MENU          ║");
            System.out.println("║  3. Quản lý sinh viên bằng BinaryTree      ║");
            System.out.println("║  4. Giải bài toán đồ thị                   ║");  // Thêm lựa chọn mới
            System.out.println("║  0. Thoát                                  ║");
            System.out.println(vienDong);
            System.out.println(vienTrenDuoi);
            System.out.print("Mời bạn chọn: ");
            
            luaChon = sc.nextInt();

            switch (luaChon) {
                case 1:
                    if (deQuy.showMenu()) {
                        System.out.println("\nQuay lại menu chính.");
                    }
                    break;
                case 2:
                    if (list.showMenu()) {
                        System.out.println("\nQuay lại menu chính.");
                    }
                    break;
                case 3:
                    if (treeMenu.showMenu()) {
                        System.out.println("\nQuay lại menu chính.");
                    }
                    break;
                case 4:
                    // Gọi menu đồ thị từ lớp Dothimenu
                    if (doThiMenu.showMenu()) {
                        System.out.println("\nQuay lại menu chính.");
                    }
                    break;
                case 0:
                    System.out.println("\nThoát chương trình...");
                    break;
                default:
                    System.out.println("\nLựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (luaChon != 0);

        sc.close(); // Đóng Scanner
    }
}
