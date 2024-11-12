package dslkdoi;
import java.io.*;

public class DoubleLinkList {
    private DoubleLink head;
    private String filePath;
    private boolean showData = false;

    public DoubleLinkList(String filePath) {
        head = null;
        this.filePath = filePath;
        loadFromFile(); // Tự động tải dữ liệu từ file khi khởi động
    }

    // Hàm tự động tải dữ liệu từ file
    public void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Chưa có dữ liệu. Bắt đầu với danh sách rỗng.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String maSv = parts[0];
                    String hoTen = parts[1];
                    double diem = Double.parseDouble(parts[2]);
                    addStudent(maSv, hoTen, diem, false); // Không tự lưu khi đang tải dữ liệu
                }
            }
            System.out.println("Đã tải dữ liệu từ file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    // Hàm lưu dữ liệu vào file
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            DoubleLink current = head;
            while (current != null) {
                bw.write(current.getMaSv() + "," + current.getHoTen() + "," + current.getDiem());
                bw.newLine();
                current = current.next;
            }
            System.out.println("Đã lưu dữ liệu vào file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
    public void addStudent(String maSv, String hoTen, double diem) {
        addStudent(maSv, hoTen, diem, true);
    }

    // Phương thức thêm sinh viên
    private void addStudent(String maSv, String hoTen, double diem, boolean saveToFile) {
        DoubleLink newStudent = new DoubleLink(maSv, hoTen, diem);
        if (head == null) {
            head = newStudent;
        } else {
            DoubleLink current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newStudent;
            newStudent.prev = current;
        }
        System.out.println("Đã thêm sinh viên: " + hoTen);

        // Tự động lưu nếu thêm trực tiếp
        if (saveToFile) {
            saveToFile();
        }
    }

    public void setShowData(boolean showData) {
        this.showData = showData;
    }

  
    // Các phương thức khác cũng điều chỉnh theo cách tương tự...

    public boolean isStudentExists(String maSv) {
        DoubleLink current = head;
        while (current != null) {
            if (current.getMaSv().equals(maSv)) {
                return true; // Mã sinh viên đã tồn tại
            }
            current = current.next;
        }
        return false; // Mã sinh viên chưa tồn tại
    }

    // Các phương thức khác như in danh sách, tìm kiếm, xóa sinh viên...


    // In danh sách sinh viên
 // In danh sách sinh viên
    public void printList() {
        if (head == null) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }
        System.out.println("╔════════════╦════════════════════════════╦══════════╦══════════╗");
        System.out.println("║    Mã SV   ║         Họ Tên             ║   Điểm   ║ Kết Quả  ║");
        System.out.println("╠════════════╬════════════════════════════╬══════════╬══════════╣");

        DoubleLink current = head;
        while (current != null) {
            System.out.printf("║ %-10s ║ %-24s ║ %-8.2f ║ %-8s ║\n",
                    current.getMaSv(), current.getHoTen(),
                    current.getDiem(), current.getKq());
            current = current.next;
        }

        System.out.println("╚════════════╩════════════════════════════╩══════════╩══════════╝");
    }

    // In sinh viên có điểm cao nhất
    public void printHighestScore() {
        if (head == null) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }
        DoubleLink highest = head;
        DoubleLink current = head;
        while (current != null) {
            if (current.getDiem() > highest.getDiem()) {
                highest = current;
            }
            current = current.next;
        }
        System.out.println("Sinh viên có điểm cao nhất:");
        System.out.printf("%-10s %-20s %-10.2f %-10s\n",
                highest.getMaSv(), highest.getHoTen(),
                highest.getDiem(), highest.getKq());
    }

    // In sinh viên có điểm thấp nhất
    public void printLowestScore() {
        if (head == null) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }
        DoubleLink lowest = head;
        DoubleLink current = head;
        while (current != null) {
            if (current.getDiem() < lowest.getDiem()) {
                lowest = current;
            }
            current = current.next;
        }
        System.out.println("Sinh viên có điểm thấp nhất:");
        System.out.printf("%-10s %-20s %-10.2f %-10s\n",
                lowest.getMaSv(), lowest.getHoTen(),
                lowest.getDiem(), lowest.getKq());
    }

    public void deleteStudent(String maSv) {
        if (head == null) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }
        if (head.getMaSv().equals(maSv)) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            System.out.println("Đã xóa sinh viên có mã: " + maSv);
            saveToFile(); // Tự động lưu sau khi xóa
            return;
        }
        DoubleLink current = head;
        while (current != null) {
            if (current.getMaSv().equals(maSv)) {
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                if (current.prev != null) {
                    current.prev.next = current.next;
                }
                System.out.println("Đã xóa sinh viên có mã: " + maSv);
                saveToFile(); // Tự động lưu sau khi xóa
                return;
            }
            current = current.next;
        }
        System.out.println("Không tìm thấy sinh viên với mã: " + maSv);
    }

    // Cập nhật thông tin sinh viên và tự động lưu
    public void updateStudent(String maSv, String newHoTen, double newDiem) {
        DoubleLink student = searchStudent(maSv);
        if (student != null) {
            student.setHoTen(newHoTen);
            student.setDiem(newDiem);
            saveToFile(); // Tự động lưu sau khi cập nhật
            System.out.println("Cập nhật thông tin sinh viên thành công.");
        } else {
            System.out.println("Không tìm thấy sinh viên với mã: " + maSv);
        }
    }
  
    // Cập nhật thông tin sinh viên 
    // Cập nhật thông tin sinh viên và tự động lưu


    // Tìm kiếm sinh viên theo mã
    public DoubleLink searchStudent(String maSv) {
        DoubleLink current = head;
        while (current != null) {
            if (current.getMaSv().equals(maSv)) {
                return current; // Trả về sinh viên tìm thấy
            }
            current = current.next;
        }
        return null; // Không tìm thấy
    }

    // Thống kê sinh viên đậu, rớt và in bảng chi tiết
    public void thongKeXepLoai() {
        if (head == null) {
            System.out.println("Danh sách sinh viên rỗng.");
            return;
        }

        int countPass = 0;
        int countFail = 0;
        DoubleLink current = head;

        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   THỐNG KÊ XẾP LOẠI SINH VIÊN                    ║");
        System.out.println("╠════════════════════╦════════════╦═══════════════════════════════╣");
        System.out.println("║       Họ Tên       ║    Điểm    ║          Xếp Loại            ║");
        System.out.println("╠════════════════════╬════════════╬═══════════════════════════════╣");

        // Duyệt qua danh sách và in từng sinh viên theo bảng
        while (current != null) {
            String hoTen = current.getHoTen();
            double diem = current.getDiem();
            String xepLoai = current.getXl();
            
            System.out.printf("║ %-18s ║ %-10.2f ║ %-25s ║\n", hoTen, diem, xepLoai);

            if (current.getKq().equals("Đậu")) {
                countPass++;
            } else {
                countFail++;
            }
            
            current = current.next;
        }

        System.out.println("╚════════════════════╩════════════╩═══════════════════════════════╝");
        System.out.println("Số sinh viên đậu: " + countPass);
        System.out.println("Số sinh viên rớt: " + countFail);
    }

    public void searchByScoreRange(double minScore, double maxScore) {
        boolean found = false;
        DoubleLink current = head;
        System.out.println("Sinh viên có điểm trong khoảng " + minScore + " - " + maxScore + ":");
        System.out.printf("%-10s %-20s %-10s %-10s\n", "Mã SV", "Họ Tên", "Điểm", "Kết Quả");

        while (current != null) {
            if (current.getDiem() >= minScore && current.getDiem() <= maxScore) {
                found = true;
                System.out.printf("%-10s %-20s %-10.2f %-10s\n",
                        current.getMaSv(), current.getHoTen(),
                        current.getDiem(), current.getKq());
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Không có sinh viên nào trong khoảng điểm này.");
        }
    }
    

    public void sortListByScore() {
        if (head == null || head.next == null) {
            return;
        }
        for (DoubleLink i = head; i.next != null; i = i.next) {
            for (DoubleLink j = i.next; j != null; j = j.next) {
                if (i.getDiem() < j.getDiem()) {  // Đổi chỗ nếu điểm của i < j
                    // Hoán đổi các thuộc tính
                    String tempMaSv = i.getMaSv();
                    String tempHoTen = i.getHoTen();
                    double tempDiem = i.getDiem();

                    i.setMaSv(j.getMaSv());
                    i.setHoTen(j.getHoTen());
                    i.setDiem(j.getDiem());

                    j.setMaSv(tempMaSv);
                    j.setHoTen(tempHoTen);
                    j.setDiem(tempDiem);
                }
            }
        }
        System.out.println("Đã sắp xếp danh sách sinh viên theo điểm.");
    }

}