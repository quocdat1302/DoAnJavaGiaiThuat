package graph;

import java.util.*;

public class DoThi {
    private int soDinh;
    private LinkedList<Nut>[] danhSachKe;

    private static class Nut implements Comparable<Nut> {
        int dinh;
        int trongSo;

        Nut(int dinh, int trongSo) {
            this.dinh = dinh;
            this.trongSo = trongSo;
        }

        @Override
        public int compareTo(Nut nutKhac) {
            return Integer.compare(this.trongSo, nutKhac.trongSo);
        }
    }

    public DoThi(int soDinh) {
        this.soDinh = soDinh;
        danhSachKe = new LinkedList[soDinh];
        for (int i = 0; i < soDinh; i++) {
            danhSachKe[i] = new LinkedList<>();
        }
    }

    public void themCanh(int u, int v, int trongSo) {
        danhSachKe[u].add(new Nut(v, trongSo));
        danhSachKe[v].add(new Nut(u, trongSo));
    }

    public void dijkstra(int nguon) {
        int[] khoangCach = new int[soDinh];
        Arrays.fill(khoangCach, Integer.MAX_VALUE);
        khoangCach[nguon] = 0;

        PriorityQueue<Nut> hangDoiUuTien = new PriorityQueue<>();
        hangDoiUuTien.add(new Nut(nguon, 0));

        System.out.println("Bắt đầu từ đỉnh " + nguon + ", tìm đường đi ngắn nhất:");
        System.out.println("--------------------------------------------------");

        while (!hangDoiUuTien.isEmpty()) {
            Nut nutHienTai = hangDoiUuTien.poll();
            int dinhHienTai = nutHienTai.dinh;

            System.out.println("Đang xử lý đỉnh: " + dinhHienTai + " (khoảng cách hiện tại: " + khoangCach[dinhHienTai] + ")");

            for (Nut hangXom : danhSachKe[dinhHienTai]) {
                int dinhKe = hangXom.dinh;
                int trongSoCanh = hangXom.trongSo;

                if (khoangCach[dinhHienTai] + trongSoCanh < khoangCach[dinhKe]) {
                    khoangCach[dinhKe] = khoangCach[dinhHienTai] + trongSoCanh;
                    hangDoiUuTien.add(new Nut(dinhKe, khoangCach[dinhKe]));

                    System.out.println("   --> Cập nhật đỉnh " + dinhKe + " với khoảng cách mới: " + khoangCach[dinhKe]);
                }
            }

            try {
                Thread.sleep(500); // Tạm dừng 0.5 giây
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("--------------------------------------------------");
        }

        hienThiKhoangCach(nguon, khoangCach);
    }

    private void hienThiKhoangCach(int nguon, int[] khoangCach) {
        System.out.println("\nKết quả khoảng cách từ đỉnh " + nguon + " đến các đỉnh khác:");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < soDinh; i++) {
            String ketQua = khoangCach[i] == Integer.MAX_VALUE ? "∞" : String.valueOf(khoangCach[i]);
            System.out.println("Đỉnh " + i + ": " + ketQua);
        }
    }

    public static void main(String[] args) {
        DoThi doThi = new DoThi(4);
        doThi.themCanh(0, 1, 2);
        doThi.themCanh(0, 2, 5);
        doThi.themCanh(1, 2, 1);
        doThi.themCanh(1, 3, 4);
        doThi.themCanh(2, 3, 3);

        doThi.dijkstra(0);
    }
}
