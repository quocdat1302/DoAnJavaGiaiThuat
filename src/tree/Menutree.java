	package tree;
	
	import java.util.Scanner;
	
	public class Menutree {
	    public boolean showMenu() {
	        String filename = "C:\\Users\\Admin\\Documents\\data_cay_sinh_vien.dat";  // Đường dẫn tệp mặc định
	        BinaryTree tree = new BinaryTree(filename);
	        Scanner sc = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("\n╔═════════════════════════ MENU CÂY NHỊ PHÂN ═════════════════════╗");
	            System.out.println("║ 1. Thêm sinh viên                                               ║");
	            System.out.println("║ 2. Hiển thị sinh viên                                           ║");
	            System.out.println("║ 3. Cập nhật sinh viên                                           ║");
	            System.out.println("║ 4. Xóa sinh viên                                                ║");
	            System.out.println("║ 5. Xem thống kê đậu/rớt                                         ║");
	            System.out.println("║ 6. Hiển thị sinh viên có điểm >= 5                              ║");
	            System.out.println("║ 0. Quay lại menu chính (và lưu tự động)                         ║");
	            System.out.println("╚═════════════════════════════════════════════════════════════════╝");
	            System.out.print("Chọn chức năng: ");
	            choice = sc.nextInt();
	            sc.nextLine();  // Đọc ký tự xuống dòng

	            switch (choice) {
	                case 1:
	                    tree.create();  // Thêm sinh viên vào cây
	                    break;
	                case 2:
	                    tree.setResultsAndCategories();  // Cập nhật kết quả và xếp loại
	                    tree.displayAllStudents();  // Hiển thị sinh viên
	                    break;
	                case 3:
	                    System.out.print("Nhập mã sinh viên để cập nhật: ");
	                    String maSV = sc.nextLine();
	                    System.out.print("Nhập họ tên mới: ");
	                    String hoTen = sc.nextLine();
	                    System.out.print("Nhập điểm mới: ");
	                    double diem = sc.nextDouble();
	                    sc.nextLine();
	                    tree.updateStudent(tree.root, maSV, hoTen, diem);
	                    break;
	                case 4:
	                    System.out.print("Nhập mã sinh viên để xóa: ");
	                    String maSVDel = sc.nextLine();
	                    tree.root = tree.delete(tree.root, maSVDel);  // Xóa sinh viên
	                    System.out.println("Đã xóa sinh viên.");
	                    break;
	                case 5:
	                    tree.printDetailedStatistics();  // Thống kê số lượng đậu/rớt
	                    break;
	                case 6:
	                    // Cập nhật xếp loại và kết quả trước khi hiển thị
	                    tree.setResultsAndCategories(); 
	                    System.out.println("Danh sách sinh viên có điểm >= 5:");
	                    tree.hienThiSinhVienDiemTren5(tree.root);  // Hiển thị sinh viên có điểm >= 5
	                    break;

	                case 0:
	                    System.out.println("Đang lưu dữ liệu và quay lại menu chính...");
	                    tree.saveToFile(filename);  // Tự động lưu khi thoát
	                    return true; // Quay lại menu chính
	                default:
	                    System.out.println("Chọn chức năng không hợp lệ.");
	                    break;
	            }
	        } while (choice != 0);

	        return false;
	    }
	}
