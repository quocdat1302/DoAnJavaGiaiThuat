package graph;

import java.util.*;

public class DoThiMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int V_MAX = 100; // Số đỉnh tối đa
    private int V; // Số đỉnh hiện tại
    private int[][] graph = new int[V_MAX][V_MAX]; // Ma trận kề

    public DoThiMenu() {
        V = 0;
        for (int i = 0; i < V_MAX; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }
    }

    public void taoDoThiMoi() {
        System.out.print("Nhập số đỉnh: ");
        V = scanner.nextInt();
        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0; // Đỉnh đến chính nó có khoảng cách bằng 0
        }
        System.out.println("Đã tạo đồ thị với " + V + " đỉnh.");
    }

    public void themDinh() {
        V++;
        System.out.println("Đã thêm đỉnh mới. Số đỉnh hiện tại là: " + V);
    }

    public void themCanh() {
        System.out.print("Nhập đỉnh đầu: ");
        int u = scanner.nextInt();
        System.out.print("Nhập đỉnh cuối: ");
        int v = scanner.nextInt();
        System.out.print("Nhập trọng số: ");
        int w = scanner.nextInt();

        if (u < V && v < V) {
            graph[u][v] = w;
            graph[v][u] = w; // Nếu đồ thị vô hướng
            System.out.println("Đã thêm cạnh từ " + u + " đến " + v + " với trọng số " + w);
        } else {
            System.out.println("Đỉnh không hợp lệ.");
        }
    }



    private int timDinhGanNhat(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void hienThiKhoangCach(int[] dist, int source) {
        System.out.println("Khoảng cách từ đỉnh nguồn " + source + " đến các đỉnh khác:");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Đỉnh " + i + ": không thể tiếp cận");
            } else {
                System.out.println("Đỉnh " + i + ": " + dist[i]);
            }
        }
    }


public void tinhDuongDiNganNhat(int source) {
    // Kiểm tra nếu chưa tạo đồ thị
    if (V == 0) {
        System.out.println("Vui lòng tạo đồ thị trước.");
        return;
    }

    // Kiểm tra nếu đỉnh nguồn không hợp lệ
    if (source < 0 || source >= V) {
        System.out.println("Đỉnh nguồn không hợp lệ. Vui lòng nhập giá trị từ 0 đến " + (V - 1));
        return;
    }

    int[] dist = new int[V];
    boolean[] visited = new boolean[V];

    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;

    for (int i = 0; i < V - 1; i++) {
        int u = timDinhGanNhat(dist, visited);
        if (u == -1) break; // Nếu không tìm được đỉnh gần nhất, thoát vòng lặp
        visited[u] = true;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && graph[u][v] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {
                dist[v] = dist[u] + graph[u][v];
            }
        }
    }

    hienThiKhoangCach(dist, source);
}

public boolean showMenu() {
    int choice;
    do {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║         MENU ĐỒ THỊ            ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║ 1. Tạo đồ thị mới              ║");
        System.out.println("║ 2. Thêm đỉnh                   ║");
        System.out.println("║ 3. Thêm cạnh                   ║");
        System.out.println("║ 4. Tính đường đi ngắn nhất     ║");
        System.out.println("║ 5. Hiển thị khoảng cách        ║");
        System.out.println("║ 6. Thoát                       ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.print("Mời bạn chọn (1-6): ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                taoDoThiMoi();
                break;
            case 2:
                themDinh();
                break;
            case 3:
                themCanh();
                break;
            case 4:
                if (V == 0) {
                    System.out.println("Vui lòng tạo đồ thị trước.");
                } else {
                    System.out.print("Nhập đỉnh nguồn: ");
                    int source = scanner.nextInt();
                    tinhDuongDiNganNhat(source);
                }
                break;
            case 5:
                if (V == 0) {
                    System.out.println("Vui lòng tạo đồ thị trước.");
                } else {
                    System.out.print("Nhập đỉnh nguồn: ");
                    int source = scanner.nextInt();
                    tinhDuongDiNganNhat(source);
                }
                break;
            case 6:
                System.out.println("Thoát menu đồ thị...");
                return true;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    } while (choice != 6);
    return false;
}
}