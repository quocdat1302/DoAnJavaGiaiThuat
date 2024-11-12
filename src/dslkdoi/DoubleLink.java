package dslkdoi;

class DoubleLink {
    private String maSv;
    private String hoTen;
    private double diem;
    private String kq; // Kết quả: "Đậu" hoặc "Rớt"
    private String xl; // Xếp loại: "Kém", "Trung Bình", "Khá", "Giỏi"
    DoubleLink next;
    DoubleLink prev;

    public DoubleLink(String maSv, String hoTen, double diem) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.diem = diem;
        this.kq = tinhKq(diem); // Tính kết quả đậu/rớt
        this.xl = tinhXl(diem); // Tính xếp loại
        this.next = null;
        this.prev = null;
    }

    // Tính kết quả đậu/rớt
    private String tinhKq(double diem) {
        return diem >= 5 ? "Đậu" : "Rớt";
    }

    // Tính xếp loại
    private String tinhXl(double diem) {
        if (diem < 0) {
            return "Kém";
        } else if (diem >= 5 && diem < 7) {
            return "Trung Bình";
        } else if (diem >= 7 && diem < 8) {
            return "Khá";
        } else {
            return "Giỏi";
        }
    }

    // Getter cho các thuộc tính
    public String getMaSv() {
        return maSv;
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

    // Setter cho các thuộc tính cần thiết trong quá trình hoán đổi
    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setDiem(double diem) {
        this.diem = diem;
        this.kq = tinhKq(diem); // Cập nhật lại kết quả khi thay đổi điểm
        this.xl = tinhXl(diem); // Cập nhật lại xếp loại khi thay đổi điểm
    }
}
