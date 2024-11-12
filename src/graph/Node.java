package graph;

// Lớp Node để lưu trữ đỉnh và trọng số của cạnh
public class Node implements Comparable<Node> {
    public int vertex;  // Đỉnh
    public int weight;  // Trọng số của cạnh

    // Constructor để khởi tạo đỉnh và trọng số
    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    // Phương thức so sánh (cho hàng đợi ưu tiên)
    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight;
    }
}
