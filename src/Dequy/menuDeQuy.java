package Dequy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class menuDeQuy {
    cacBaiDeQuy deQuy = new cacBaiDeQuy();
    baiKhuDeQuy khuDeQuy = new baiKhuDeQuy();

    // Menu for recursive algorithms
    public void menuDQ() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║        MENU BÀI TOÁN ĐỆ QUY            ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1. Giai thừa                          ║");
            System.out.println("║  2. Số Fibonacci                       ║");
            System.out.println("║  3. Tìm phần tử lớn nhất trong mảng    ║");
            System.out.println("║  4. Chuyển đổi thập phân sang nhị phân ║");
            System.out.println("║  5. Tìm tất cả các tổ hợp của mảng     ║");
            System.out.println("║  6. Giải bài toán N-Queen              ║");
            System.out.println("║  7. Sắp xếp nổi bọt                    ║");
            System.out.println("║  8. Sắp xếp chèn                       ║");
            System.out.println("║  9. Sắp xếp trộn                       ║");
            System.out.println("║ 10. Sắp xếp nhanh                      ║");
            System.out.println("║ 11. Giải Sudoku                        ║");
            System.out.println("║  0. Quay lại                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Mời bạn chọn (0-11): ");
            
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nhập n: ");
                    int n = sc.nextInt();
                    System.out.println("Giai thừa của " + n + " là: " + deQuy.giaiThua(n));
                    break;
                case 2:
                    System.out.print("Nhập n: ");
                    n = sc.nextInt();
                    System.out.println("Số Fibonacci thứ " + n + " là: " + deQuy.fibonacci(n));
                    break;
                case 3:
                    System.out.print("Nhập số phần tử mảng: ");
                    n = sc.nextInt();
                    int[] arr = new int[n];
                    System.out.println("Nhập các phần tử mảng: ");
                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
                    System.out.println("Phần tử lớn nhất là: " + deQuy.timMax(arr, n));
                    break;
                case 4:
                    System.out.print("Nhập số thập phân: ");
                    n = sc.nextInt();
                    System.out.print("Số nhị phân: ");
                    deQuy.chuyenSangNhiPhan(n);
                    System.out.println();
                    break;
                case 5:
                    int[] array = {1, 2, 3};
                    List<List<Integer>> toHop = deQuy.toHop(array, 2);
                    System.out.println("Các tổ hợp của mảng là: " + toHop);
                    break;
                case 6:
                    List<int[]> nQueenResult = deQuy.nQueen(4);
                    System.out.println("Các cách giải N-Queen:");
                    for (int[] solution : nQueenResult) {
                        System.out.println(Arrays.toString(solution));
                    }
                    break;
                case 7:
                    System.out.print("Nhập số phần tử mảng: ");
                    n = sc.nextInt();
                    arr = new int[n];
                    System.out.println("Nhập các phần tử mảng: ");
                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
                    deQuy.sapXepNoiBot(arr, n);
                    System.out.println("Mảng sau khi sắp xếp nổi bọt: " + Arrays.toString(arr));
                    break;
                case 8:
                    System.out.print("Nhập số phần tử mảng: ");
                    n = sc.nextInt();
                    arr = new int[n];
                    System.out.println("Nhập các phần tử mảng: ");
                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
                    deQuy.sapXepChen(arr, n);
                    System.out.println("Mảng sau khi sắp xếp chèn: " + Arrays.toString(arr));
                    break;
                case 9:
                    System.out.print("Nhập số phần tử mảng: ");
                    n = sc.nextInt();
                    arr = new int[n];
                    System.out.println("Nhập các phần tử mảng: ");
                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
                    deQuy.sapXepTron(arr, 0, n - 1);
                    System.out.println("Mảng sau khi sắp xếp trộn: " + Arrays.toString(arr));
                    break;
                case 10:
                    System.out.print("Nhập số phần tử mảng: ");
                    n = sc.nextInt();
                    arr = new int[n];
                    System.out.println("Nhập các phần tử mảng: ");
                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
                    deQuy.sapXepNhanh(arr, 0, n - 1);
                    System.out.println("Mảng sau khi sắp xếp nhanh: " + Arrays.toString(arr));
                    break;
                case 11:
                    int[][] board = {
                        {5, 3, 0, 0, 7, 0, 0, 0, 0},
                        {6, 0, 0, 1, 9, 5, 0, 0, 0},
                        {0, 9, 8, 0, 0, 0, 0, 6, 0},
                        {8, 0, 0, 0, 6, 0, 0, 0, 3},
                        {4, 0, 0, 8, 0, 3, 0, 0, 1},
                        {7, 0, 0, 0, 2, 0, 0, 0, 6},
                        {0, 6, 0, 0, 0, 0, 2, 8, 0},
                        {0, 0, 0, 4, 1, 9, 0, 0, 5},
                        {0, 0, 0, 0, 8, 0, 0, 7, 9}
                    };
                    if (deQuy.giaiSudoku(board)) {
                        System.out.println("Sudoku đã giải:");
                        for (int[] row : board) {
                            System.out.println(Arrays.toString(row));
                        }
                    } else {
                        System.out.println("Không có lời giải cho Sudoku này.");
                    }
                    break;
                case 0:
                    System.out.println("\nQuay lại menu chính...");
                    break;
                default:
                    System.out.println("\n❌ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                    break;
            }
        } while (choice != 0);
    }

    // Non-recursive algorithm menu
    public void menuKhuDQ() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║       MENU BÀI TOÁN KHỬ ĐỆ QUY         ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1. Giai thừa                          ║");
            System.out.println("║  2. Số Fibonacci                       ║");
            System.out.println("║  3. Tìm phần tử lớn nhất trong mảng    ║");
            System.out.println("║  4. Chuyển đổi thập phân sang nhị phân ║");
            System.out.println("║  5. Đếm số chữ số của một số           ║");
            System.out.println("║  6. Tìm tất cả các tổ hợp của mảng     ║");
            System.out.println("║  7. Giải bài toán N-Queen              ║");
            System.out.println("║  8. Sắp xếp nổi bọt                    ║");
            System.out.println("║  9. Sắp xếp chèn                       ║");
            System.out.println("║ 10. Sắp xếp trộn                       ║");
            System.out.println("║ 11. Sắp xếp nhanh                      ║");
            System.out.println("║  0. Quay lại                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Mời bạn chọn (0-11): ");
            
            choice = sc.nextInt();
	
	            switch (choice) {
	                case 1:
	                    System.out.print("Nhập n: ");
	                    int n = sc.nextInt();
	                    System.out.println("Giai thừa của " + n + " là: " + khuDeQuy.giaiThua(n));
	                    break;
	                case 2:
	                    System.out.print("Nhập n: ");
	                    n = sc.nextInt();
	                    System.out.println("Số Fibonacci thứ " + n + " là: " + khuDeQuy.fibonacci(n));
	                    break;
	                case 3:
	                    System.out.print("Nhập số phần tử mảng: ");
	                    n = sc.nextInt();
	                    int[] arr = new int[n];
	                    System.out.println("Nhập các phần tử mảng: ");
	                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
	                    System.out.println("Phần tử lớn nhất là: " + khuDeQuy.timMax(arr));
	                    break;
	                case 4:
	                    System.out.print("Nhập số thập phân: ");
	                    n = sc.nextInt();
	                    System.out.println("Số nhị phân là: " + khuDeQuy.chuyenSangNhiPhan(n));
	                    break;
	                case 5:
	                    System.out.print("Nhập số: ");
	                    n = sc.nextInt();
	                    System.out.println("Số chữ số: " + khuDeQuy.demChuSo(n));
	                    break;
	                case 6:
	                    int[] array = {1, 2, 3};
	                    List<List<Integer>> toHop = khuDeQuy.toHop(array, 2);
	                    System.out.println("Các tổ hợp của mảng là: " + toHop);
	                    break;
	                case 7:
	                    List<int[]> nQueenResult = khuDeQuy.nQueen(4);
	                    System.out.println("Các cách giải N-Queen:");
	                    for (int[] solution : nQueenResult) {
	                        System.out.println(Arrays.toString(solution));
	                    }
	                    break;
	                case 8:
	                    System.out.print("Nhập số phần tử mảng: ");
	                    n = sc.nextInt();
	                    arr = new int[n];
	                    System.out.println("Nhập các phần tử mảng: ");
	                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
	                    khuDeQuy.sapXepNoiBot(arr);
	                    System.out.println("Mảng sau khi sắp xếp nổi bọt: " + Arrays.toString(arr));
	                    break;
	                case 9:
	                    System.out.print("Nhập số phần tử mảng: ");
	                    n = sc.nextInt();
	                    arr = new int[n];
	                    System.out.println("Nhập các phần tử mảng: ");
	                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
	                    khuDeQuy.sapXepChen(arr);
	                    System.out.println("Mảng sau khi sắp xếp chèn: " + Arrays.toString(arr));
	                    break;
	                case 10:
	                    System.out.print("Nhập số phần tử mảng: ");
	                    n = sc.nextInt();
	                    arr = new int[n];
	                    System.out.println("Nhập các phần tử mảng: ");
	                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
	                    khuDeQuy.sapXepTron(arr);; // Chỉ truyền một tham số
	                    System.out.println("Mảng sau khi sắp xếp trộn: " + Arrays.toString(arr));
	                    break;
	
	                case 11:
	                    System.out.print("Nhập số phần tử mảng: ");
	                    n = sc.nextInt();
	                    arr = new int[n];
	                    System.out.println("Nhập các phần tử mảng: ");
	                    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
	                    khuDeQuy.sapXepNhanh(arr); // Chỉ truyền một tham số
	                    System.out.println("Mảng sau khi sắp xếp nhanh: " + Arrays.toString(arr));
	                    break;
	
	                case 0:
	                    System.out.println("\nQuay lại menu chính...");
	                    break;
	                default:
	                    System.out.println("\n❌ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
	                    break;
	            }
	        } while (choice != 0);
	    }
	
	}
