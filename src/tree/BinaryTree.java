package tree;

import java.util.*;
import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class BinaryTree implements Serializable {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    // Hàm khởi tạo từ tệp
    public BinaryTree(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            BinaryTree loadedTree = loadFromFile(filename);
            if (loadedTree != null) {
                this.root = loadedTree.root;
                System.out.println("Đã tải dữ liệu từ tệp.");
            } else {
                System.out.println("Không thể tải dữ liệu từ tệp, khởi tạo cây mới.");
            }
        } else {
            System.out.println("Tệp không tồn tại, khởi tạo cây mới.");
        }
    }

 // Lưu cây vào tệp
    public void saveToFile(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Đã lưu cây vào tệp: " + filename);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu tệp: " + e.getMessage());
        }
    }

    // Tải cây từ tệp
    public BinaryTree loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Tệp " + filename + " không tồn tại.");
            return null;
        }
        
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            System.out.println("Đã tải dữ liệu từ tệp: " + filename);
            return (BinaryTree) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi tải tệp: " + e.getMessage());
            return null;
        }
    }


    // Hàm chèn sinh viên vào cây
    public Node insertNode(Node root, String maSV, String ht, double diem) {
        if (root == null) {
            root = new Node(maSV, ht, diem);
            return root;
        }
        if (root.getMaSV().compareTo(maSV) > 0) {
            root.left = insertNode(root.left, maSV, ht, diem);
        } else if (root.getMaSV().compareTo(maSV) < 0) {
            root.right = insertNode(root.right, maSV, ht, diem);
        }
        return root;
    }

    // Nhập dữ liệu sinh viên
    public void create() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nhập mã sinh viên (hoặc bỏ qua để kết thúc): ");
            String maSV = sc.nextLine();
            if (maSV.isEmpty()) {
                break; // Kết thúc nếu không nhập mã
            }
            System.out.print("Nhập họ tên: ");
            String hoTen = sc.nextLine();
            System.out.print("Nhập điểm: ");
            double diem = sc.nextDouble();
            sc.nextLine(); // Đọc dòng mới

            root = insertNode(root, maSV, hoTen, diem);
        }
    }

 // Hàm in danh sách sinh viên với viền đẹp
    public void printStudents(Node root) {
        if (root == null) {
            System.out.println("Danh sách sinh viên trống.");
            return;
        }
        
        // Đầu bảng
        System.out.println("╔══════════════╦══════════════════════════╦════════╦════════════╦════════════╗");
        System.out.println("║     Mã SV    ║         Họ Tên           ║  Điểm  ║  Xếp Loại  ║  Kết Quả   ║");
        System.out.println("╠══════════════╬══════════════════════════╬════════╬════════════╬════════════╣");

        // Nội dung bảng
        printStudentDetails(root);

        // Cuối bảng
        System.out.println("╚══════════════╩══════════════════════════╩════════╩════════════╩════════════╝");
    }

    // Hiển thị tất cả sinh viên
 // Hàm đệ quy để in chi tiết từng sinh viên với viền đẹp
    private void printStudentDetails(Node root) {
        if (root != null) {
            printStudentDetails(root.left);

            System.out.printf("║ %-12s ║ %-24s ║ %-6.2f ║ %-10s ║ %-10s ║\n", 
                root.getMaSV(), 
                root.getHoTen(), 
                root.getDiem(), 
                root.getXl(), 
                root.getKq());

            printStudentDetails(root.right);
        }
    }
 // Hàm hiển thị danh sách sinh viên và các sinh viên có điểm >= 5
    public void displayAllStudentsWithCondition() {
        System.out.println("========== DANH SÁCH TẤT CẢ SINH VIÊN ==========");
        printStudents(root);

        System.out.println("\n========== DANH SÁCH SINH VIÊN CÓ ĐIỂM >= 5 ==========");
        hienThiSinhVienDiemTren5(root);
    }

    // Hàm hiển thị sinh viên có điểm >= 5 (dùng lại bảng với viền đẹp)
    public void hienThiSinhVienDiemTren5(Node root) {
        if (root != null) {
            hienThiSinhVienDiemTren5(root.left);

            if (root.getDiem() >= 5) {
                System.out.printf("║ %-12s ║ %-24s ║ %-6.2f ║ %-10s ║ %-10s ║\n", 
                    root.getMaSV(), 
                    root.getHoTen(), 
                    root.getDiem(), 
                    root.getXl(), 
                    root.getKq());
            }

            hienThiSinhVienDiemTren5(root.right);
        }
    }
    public void displayAllStudents() {
        printStudents(root);
    }

    // Cập nhật kết quả và xếp loại
    public void setResultsAndCategories() {
        setResults(root);
        setCategories(root);
    }

    // Cập nhật kết quả dựa trên điểm
    private void setResults(Node root) {
        if (root != null) {
            root.setKq(root.getDiem() < 5 ? "Rớt" : "Đậu");
            setResults(root.left);
            setResults(root.right);
        }
    }

    // Cập nhật xếp loại dựa trên điểm
    private void setCategories(Node root) {
        if (root != null) {
            if (root.getDiem() < 5) {
                root.setXl("Kém");
            } else if (root.getDiem() < 7) {
                root.setXl("Trung Bình");
            } else if (root.getDiem() < 8) {
                root.setXl("Khá");
            } else {
                root.setXl("Giỏi");
            }
            setCategories(root.left);
            setCategories(root.right);
        }
    }

 // Cập nhật thông tin sinh viên
    public void updateStudent(Node root, String maSV, String hoTen, double diem) {
        Node studentNode = search(root, maSV);
        if (studentNode != null) {
            studentNode.setHoTen(hoTen);
            studentNode.setDiem(diem);
            System.out.println("Thông tin sinh viên đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sinh viên với mã: " + maSV);
        }
    }

    // Cập nhật kết quả dựa trên điểm
    public void updateResults(Node root) {
        if (root != null) {
            root.setKq(root.getDiem() < 5 ? "Rớt" : "Đậu");
            updateResults(root.left);
            updateResults(root.right);
        }
    }

    // Cập nhật xếp loại dựa trên điểm
    public void updateCategories(Node root) {
        if (root != null) {
            if (root.getDiem() < 5) {
                root.setXl("Kém");
            } else if (root.getDiem() < 7) {
                root.setXl("Trung Bình");
            } else if (root.getDiem() < 8) {
                root.setXl("Khá");
            } else {
                root.setXl("Giỏi");
            }
            updateCategories(root.left);
            updateCategories(root.right);
        }
    }

    // Cập nhật kết quả cho tất cả sinh viên
    public void setResults() {
        updateResults(root);
    }

    // Cập nhật xếp loại cho tất cả sinh viên
    public void setCategories() {
        updateCategories(root);
    }

    // Tìm giá trị nhỏ nhất trong cây
    public Node minValueNode(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // Xóa sinh viên khỏi cây
    public Node delete(Node root, String maSV) {
        if (root == null) {
            return root; // Cây rỗng, không làm gì
        }

        // Tìm kiếm vị trí của nút cần xóa
        if (root.getMaSV().compareTo(maSV) > 0) {
            root.left = delete(root.left, maSV); // Tiếp tục tìm trong cây con trái
        } else if (root.getMaSV().compareTo(maSV) < 0) {
            root.right = delete(root.right, maSV); // Tiếp tục tìm trong cây con phải
        } else {
            // Nút cần xóa được tìm thấy
            if (root.left == null && root.right == null) {
                return null; // Xóa nút và trả về null
            } else if (root.left == null) {
                return root.right; // Thay thế nút bằng con bên phải
            } else if (root.right == null) {
                return root.left; // Thay thế nút bằng con bên trái
            }

            // Trường hợp 3: Nút có hai con
            Node minNode = minValueNode(root.right); // Tìm giá trị nhỏ nhất
            root.setMaSV(minNode.getMaSV()); // Thay thế mã sinh viên
            root.setHoTen(minNode.getHoTen()); // Thay thế họ tên
            root.setDiem(minNode.getDiem()); // Thay thế điểm
            root.right = delete(root.right, minNode.getMaSV()); // Xóa nút đã tìm
        }

        return root; // Trả về cây đã sửa
    }

    // Tìm kiếm sinh viên trong cây
    public Node search(Node root, String maSV) {
        if (root == null || root.getMaSV().equals(maSV)) {
            return root; // Nút cần tìm hoặc cây rỗng
        }
        if (root.getMaSV().compareTo(maSV) > 0) {
            return search(root.left, maSV); // Tìm bên trái
        } else {
            return search(root.right, maSV); // Tìm bên phải
        }
    }

    // Đếm số lượng sinh viên đậu và rớt
    public void countResults(Node root, int[] count) {
        if (root != null) {
            if (root.getKq().equals("Đậu")) {
                count[0]++; // Đếm sinh viên đậu
            } else {
                count[1]++; // Đếm sinh viên rớt
            }
            countResults(root.left, count);
            countResults(root.right, count);
        }
    }

    
    private void printStudentStatistics(Node node) {
        if (node == null) {
            return;
        }

        // In thông tin sinh viên của nút hiện tại
        String result = node.getKq(); // Lấy kết quả từ phương thức getKq()
        System.out.printf("%-20s %-10.2f %-10s%n", node.getHoTen(), node.getDiem(), result);

        // Đệ quy cho các nút con
        printStudentStatistics(node.left);
        printStudentStatistics(node.right);
    }

 // Duyệt cây và hiển thị các sinh viên có điểm >= 5
 // In thống kê số sinh viên đậu và rớt
    public void printDetailedStatistics() {
        setResultsAndCategories(); // Cập nhật kết quả và xếp loại trước khi in thống kê
        System.out.println("========== THỐNG KÊ SINH VIÊN ==========");
        System.out.printf("%-20s %-10s %-10s%n", "Tên sinh viên", "Điểm", "Kết quả");
        System.out.println("------------------------------------------");

        printStudentStatistics(root); // Gọi phương thức đệ quy để in thông tin sinh viên

        System.out.println("==========================================");
    }


}