package graph;

import java.util.*;

public class Graph {
    private int numVertices;
    private LinkedList<Node>[] adjList;

    private static class Node implements Comparable<Node> {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v, int weight) {
        adjList[u].add(new Node(v, weight));
        adjList[v].add(new Node(u, weight));
    }

    public void dijkstra(int source) {
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(source, 0));

        System.out.println("Bắt đầu từ đỉnh " + source + ", tìm đường đi ngắn nhất:");
        System.out.println("--------------------------------------------------");

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int currentVertex = currentNode.vertex;

            // Hiển thị đỉnh đang được xử lý
            System.out.println("Đang xử lý đỉnh: " + currentVertex + " (khoảng cách hiện tại: " + distances[currentVertex] + ")");

            for (Node neighbor : adjList[currentVertex]) {
                int adjacentVertex = neighbor.vertex;
                int edgeWeight = neighbor.weight;

                if (distances[currentVertex] + edgeWeight < distances[adjacentVertex]) {
                    distances[adjacentVertex] = distances[currentVertex] + edgeWeight;
                    priorityQueue.add(new Node(adjacentVertex, distances[adjacentVertex]));

                    // Hiển thị cập nhật khoảng cách
                    System.out.println("   --> Cập nhật đỉnh " + adjacentVertex + " với khoảng cách mới: " + distances[adjacentVertex]);
                }
            }

            // Tạo hiệu ứng tạm dừng để quan sát từng bước
            try {
                Thread.sleep(500); // Tạm dừng 0.5 giây
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("--------------------------------------------------");
        }

        displayDistances(source, distances);
    }

    private void displayDistances(int source, int[] distances) {
        System.out.println("\nKết quả khoảng cách từ đỉnh " + source + " đến các đỉnh khác:");
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < numVertices; i++) {
            String result = distances[i] == Integer.MAX_VALUE ? "∞" : String.valueOf(distances[i]);
            System.out.println("Đỉnh " + i + ": " + result);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 1, 4);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 3, 9);

        graph.dijkstra(0);
    }
}
