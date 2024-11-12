package dslkdoi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public boolean showMenu() {
        String filePath = "C:\\Users\\Admin\\Documents\\data_sinh_vien.dat"; // Đường dẫn chính xác
        DoubleLinkList list = new DoubleLinkList(filePath);
        list.loadFromFile(); // Tải dữ liệu từ file khi khởi động chương trình

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║             QUẢN LÝ SINH VIÊN            ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║ 1. Thêm sinh viên                        ║");
            System.out.println("║ 2. In danh sách sinh viên                ║");
            System.out.println("║ 3. In sinh viên có điểm cao nhất         ║");
            System.out.println("║ 4. In sinh viên có điểm thấp nhất        ║");
            System.out.println("║ 5. Xóa sinh viên                         ║");
            System.out.println("║ 6. Tìm kiếm sinh viên                    ║");
            System.out.println("║ 7. Thống kê sinh viên                    ║");
            System.out.println("║ 9. Chỉnh sửa thông tin sinh viên         ║");
            System.out.println("║ 10. Sắp xếp sinh viên theo điểm          ║");
            System.out.println("║ 11. Tìm kiếm sinh viên theo khoảng điểm  ║");
            System.out.println("║ 0. Thoát chương trình                    ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Chọn một tùy chọn: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc bỏ dòng trống sau khi nhập số
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
                scanner.nextLine(); // Bỏ dòng trống còn lại trong buffer
                continue; // Quay lại vòng lặp để chọn lại
            }

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Nhập mã sinh viên (nhấn Enter để thoát): ");
                        String maSV = scanner.nextLine();

                        if (maSV.isEmpty()) { 
                            System.out.println("Đã kết thúc việc thêm sinh viên.");
                            break;
                        }

                        if (list.isStudentExists(maSV)) { 
                            System.out.println("Mã sinh viên đã tồn tại. Vui lòng nhập mã khác.");
                            continue;
                        }

                        System.out.print("Nhập họ tên sinh viên: ");
                        String hoTen = scanner.nextLine();
                        System.out.print("Nhập điểm sinh viên: ");
                        double diem;
                        try {
                            diem = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Vui lòng nhập một số hợp lệ cho điểm.");
                            scanner.nextLine(); // Bỏ dòng trống còn lại trong buffer
                            continue;
                        }
                        scanner.nextLine(); // Đọc bỏ dòng trống sau khi nhập số

                        list.addStudent(maSV, hoTen, diem); // Thêm sinh viên vào danh sách và tự động lưu
                    }
                    break;

                case 2:
                    list.printList();
                    break;
                case 3:
                    list.printHighestScore();
                    break;
                case 4:
                    list.printLowestScore();
                    break;
                case 5:
                    System.out.print("Nhập mã sinh viên cần xóa: ");
                    String maSVToDelete = scanner.nextLine();
                    list.deleteStudent(maSVToDelete);
                    break;
                case 6:
                    System.out.print("Nhập mã sinh viên cần tìm: ");
                    String maSVToSearch = scanner.nextLine();
                    DoubleLink foundStudent = list.searchStudent(maSVToSearch);
                    if (foundStudent != null) {
                        System.out.println("Thông tin sinh viên tìm thấy:");
                        System.out.printf("%-10s %-20s %-10.2f %-10s\n",
                                foundStudent.getMaSv(), foundStudent.getHoTen(),
                                foundStudent.getDiem(), foundStudent.getKq());
                    } else {
                        System.out.println("Không tìm thấy sinh viên với mã " + maSVToSearch);
                    }
                    break;
                case 7:
                    list.thongKeXepLoai();
                    break;
               
                case 9:
                    System.out.print("Nhập mã sinh viên cần chỉnh sửa: ");
                    String maSvToUpdate = scanner.nextLine();
                    if (list.isStudentExists(maSvToUpdate)) {
                        System.out.print("Nhập tên mới cho sinh viên: ");
                        String newHoTen = scanner.nextLine();
                        System.out.print("Nhập điểm mới cho sinh viên: ");
                        double newDiem;
                        try {
                            newDiem = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Vui lòng nhập một số hợp lệ cho điểm.");
                            scanner.nextLine(); // Bỏ dòng trống còn lại trong buffer
                            continue;
                        }
                        scanner.nextLine(); // Đọc bỏ dòng trống sau khi nhập điểm
                        list.updateStudent(maSvToUpdate, newHoTen, newDiem);
                    } else {
                        System.out.println("Mã sinh viên không tồn tại.");
                    }
                    break;
                case 10:
                    list.sortListByScore();
                    System.out.println("Danh sách sinh viên sau khi sắp xếp theo điểm:");
                    list.printList();
                    break;
                case 11:
                    System.out.print("Nhập điểm tối thiểu: ");
                    double minScore;
                    double maxScore;
                    try {
                        minScore = scanner.nextDouble();
                        System.out.print("Nhập điểm tối đa: ");
                        maxScore = scanner.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Vui lòng nhập một số hợp lệ cho điểm.");
                        scanner.nextLine(); // Bỏ dòng trống còn lại trong buffer
                        continue;
                    }
                    scanner.nextLine(); // Đọc bỏ dòng trống sau khi nhập số
                    list.searchByScoreRange(minScore, maxScore);
                    break;
                case 0:
                    exit = true; // Thoát chương trình
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }

        return false;
    }
}
