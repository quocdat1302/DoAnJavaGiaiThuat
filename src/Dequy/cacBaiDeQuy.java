package Dequy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cacBaiDeQuy {
    
    // 1. Giai thừa - Đệ quy
    public int giaiThua(int n) {
        if (n == 0) return 1;
        return n * giaiThua(n - 1);
    }

    // 2. Số Fibonacci thứ n - Đệ quy
    public int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 3. Tìm phần tử lớn nhất trong mảng - Đệ quy
    public int timMax(int[] arr, int n) {
        if (n == 1) return arr[0];
        return Math.max(arr[n - 1], timMax(arr, n - 1));
    }

    // 4. Chuyển đổi thập phân sang nhị phân - Đệ quy
    public void chuyenSangNhiPhan(int n) {
        if (n > 0) {
            chuyenSangNhiPhan(n / 2);
            System.out.print(n % 2);
        }
    }

    // 5. Đếm số chữ số của một số - Đệ quy
    public int demChuSo(int n) {
        if (n == 0) return 0;
        return 1 + demChuSo(n / 10);
    }

    // 6. Bài đệ quy quay lui 1: Tìm tất cả các tổ hợp của một mảng số nguyên
    public List<List<Integer>> toHop(int[] arr, int k) {
        List<List<Integer>> ketQua = new ArrayList<>();
        toHopHelper(arr, k, 0, new ArrayList<>(), ketQua);
        return ketQua;
    }

    private void toHopHelper(int[] arr, int k, int start, List<Integer> current, List<List<Integer>> ketQua) {
        if (current.size() == k) {
            ketQua.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            toHopHelper(arr, k, i + 1, current, ketQua);
            current.remove(current.size() - 1);
        }
    }

    // 7. Bài đệ quy quay lui 2: Giải bài toán N-Queen
    public List<int[]> nQueen(int n) {
        List<int[]> ketQua = new ArrayList<>();
        giaiNQueen(new int[n], 0, ketQua);
        return ketQua;
    }

    private void giaiNQueen(int[] board, int row, List<int[]> ketQua) {
        if (row == board.length) {
            ketQua.add(board.clone());
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (kiemTraViTri(board, row, col)) {
                board[row] = col;
                giaiNQueen(board, row + 1, ketQua);
            }
        }
    }

    private boolean kiemTraViTri(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    // 8. Thuật toán sắp xếp 1: Sắp xếp nổi bọt (Bubble Sort) - Đệ quy
    public void sapXepNoiBot(int[] arr, int n) {
        if (n == 1) return;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        sapXepNoiBot(arr, n - 1);
    }

    // 9. Thuật toán sắp xếp 2: Sắp xếp chèn (Insertion Sort) - Đệ quy
    public void sapXepChen(int[] arr, int n) {
        if (n <= 1) return;
        sapXepChen(arr, n - 1);
        int last = arr[n - 1];
        int j = n - 2;
        while (j >= 0 && arr[j] > last) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = last;
    }

    // 10. Thuật toán sắp xếp 3: Sắp xếp trộn (Merge Sort) - Đệ quy
    public void sapXepTron(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sapXepTron(arr, left, mid);
            sapXepTron(arr, mid + 1, right);
            tron(arr, left, mid, right);
        }
    }

    private void tron(int[] arr, int left, int mid, int right) {
        int[] temp = Arrays.copyOfRange(arr, left, right + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            arr[k++] = temp[i - left] <= temp[j - left] ? temp[i++ - left] : temp[j++ - left];
        }
        while (i <= mid) arr[k++] = temp[i++ - left];
    }

    // 11. Thuật toán sắp xếp 4: Sắp xếp nhanh (Quick Sort) - Đệ quy
    public void sapXepNhanh(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = phanDoan(arr, low, high);
            sapXepNhanh(arr, low, pivotIndex - 1);
            sapXepNhanh(arr, pivotIndex + 1, high);
        }
    }

    private int phanDoan(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }


public boolean giaiSudoku(int[][] board) {
    return giaiSudokuHelper(board, 0, 0);
}

private boolean giaiSudokuHelper(int[][] board, int row, int col) {
    if (row == 9) return true;
    if (col == 9) return giaiSudokuHelper(board, row + 1, 0);
    if (board[row][col] != 0) return giaiSudokuHelper(board, row, col + 1);

    for (int num = 1; num <= 9; num++) {
        if (laHopLeSudoku(board, row, col, num)) {
            board[row][col] = num;
            if (giaiSudokuHelper(board, row, col + 1)) return true;
            board[row][col] = 0;
        }
    }
    return false;
}

private boolean laHopLeSudoku(int[][] board, int row, int col, int num) {
    for (int i = 0; i < 9; i++) {
        if (board[row][i] == num || board[i][col] == num || board[row - row % 3 + i / 3][col - col % 3 + i % 3] == num)
            return false;
    }
    return true;
}
}
