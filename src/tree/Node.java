package tree;


import java.io.Serializable;

public class Node implements Serializable {
    private String maSV;
    private String hoTen;
    private double diem;
    public String xl; // Xếp loại
    public String kq; // Kết quả
    public Node left, right;

    public Node(String maSV, String hoTen, double diem) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diem = diem;
        this.xl = "";
        this.kq = "";
        this.left = this.right = null;
    }
   
    public String getHoTen() {
        return hoTen;
    }

    public double getDiem() {
        return diem;
    }

    public String getKq() {
        return kq;
    }

    public String getXl() {
        return xl;
    }

    // Setter methods
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setDiem(double diem) {
        this.diem = diem;
        this.kq = tinhKq(diem); // Cập nhật kết quả
        this.xl = tinhXl(diem); // Cập nhật xếp loại
    }
    public void setKq(String kq) {
        this.kq = kq;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    // Tính kết quả
    private String tinhKq(double diem) {
        return diem >= 5 ? "Đậu" : "Rớt";
    }

    // Getter và Setter cho maSV
    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

	// Tính xếp loại
    private String tinhXl(double diem) {
        if (diem < 5) return "Kém";
        else if (diem < 7) return "Trung Bình";
        else if (diem < 8) return "Khá";
        else return "Giỏi";
    }
}
