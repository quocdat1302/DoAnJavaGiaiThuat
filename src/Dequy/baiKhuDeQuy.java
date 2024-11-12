package Dequy;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class baiKhuDeQuy {

	    // 1. Giai thừa - Khử đệ quy
	    public int giaiThua(int n) {
	        int ketQua = 1;
	        for (int i = 1; i <= n; i++) {
	            ketQua *= i;
	        }
	        return ketQua;
	    }

	    // 3. Số Fibonacci thứ n - Khử đệ quy
	    public int fibonacci(int n) {
	        if (n <= 1) return n;
	        int f0 = 0, f1 = 1, fn = 1;
	        for (int i = 2; i <= n; i++) {
	            fn = f0 + f1;
	            f0 = f1;
	            f1 = fn;
	        }
	        return fn;
	    }

	    // 5. Tìm phần tử lớn nhất trong mảng - Khử đệ quy
	    public int timMax(int[] arr) {
	        int max = arr[0];
	        for (int i = 1; i < arr.length; i++) {
	            if (arr[i] > max) {
	                max = arr[i];
	            }
	        }
	        return max;
	    }

	    // 7. Chuyển đổi thập phân sang nhị phân - Khử đệ quy
	    public String chuyenSangNhiPhan(int n) {
	        if (n == 0) return "0";
	        StringBuilder binary = new StringBuilder();
	        while (n > 0) {
	            binary.insert(0, n % 2);
	            n /= 2;
	        }
	        return binary.toString();
	    }

	    // 8. Đếm số chữ số của một số - Khử đệ quy
	    public int demChuSo(int n) {
	        int count = 0;
	        while (n != 0) {
	            n /= 10;
	            count++;
	        }
	        return count;
	    }

	    // Bài quay lui 1: Tìm tất cả các tổ hợp của một mảng số nguyên - Khử đệ quy
	    public List<List<Integer>> toHop(int[] arr, int k) {
	        List<List<Integer>> ketQua = new ArrayList<>();
	        int n = arr.length;
	        int[] combination = new int[k];
	        int i = 0;
	        while (i >= 0) {
	            combination[i]++;
	            if (combination[i] > n) {
	                i--;
	            } else if (i == k - 1) {
	                List<Integer> currentCombination = new ArrayList<>();
	                for (int j = 0; j < k; j++) {
	                    currentCombination.add(arr[combination[j] - 1]);
	                }
	                ketQua.add(currentCombination);
	            } else {
	                i++;
	                combination[i] = combination[i - 1];
	            }
	        }
	        return ketQua;
	    }

	    // Bài quay lui 2: Giải bài toán N-Queen - Khử đệ quy
	    public List<int[]> nQueen(int n) {
	        List<int[]> ketQua = new ArrayList<>();
	        int[] board = new int[n];
	        int row = 0;
	        
	        while (row >= 0) {
	            board[row]++;
	            while (board[row] <= n && !kiemTraViTri(board, row)) {
	                board[row]++;
	            }
	            
	            if (board[row] <= n) {
	                if (row == n - 1) {
	                    ketQua.add(board.clone());
	                } else {
	                    row++;
	                    board[row] = 0;
	                }
	            } else {
	                row--;
	            }
	        }
	        
	        return ketQua;
	    }

	    private boolean kiemTraViTri(int[] board, int row) {
	        for (int i = 0; i < row; i++) {
	            if (board[i] == board[row] || Math.abs(board[i] - board[row]) == Math.abs(i - row)) {
	                return false;
	            }
	        }
	        return true;
	    }

	    // Sắp xếp nổi bọt (Bubble Sort) - Khử đệ quy
	    public void sapXepNoiBot(int[] arr) {
	        int n = arr.length;
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (arr[j] > arr[j + 1]) {
	                    int temp = arr[j];
	                    arr[j] = arr[j + 1];
	                    arr[j + 1] = temp;
	                }
	            }
	        }
	    }

	    // Sắp xếp chèn (Insertion Sort) - Khử đệ quy
	    public void sapXepChen(int[] arr) {
	        for (int i = 1; i < arr.length; i++) {
	            int key = arr[i];
	            int j = i - 1;
	            while (j >= 0 && arr[j] > key) {
	                arr[j + 1] = arr[j];
	                j--;
	            }
	            arr[j + 1] = key;
	        }
	    }

	    // Sắp xếp trộn (Merge Sort) - Khử đệ quy
	    public void sapXepTron(int[] arr) {
	        int n = arr.length;
	        int[] temp = new int[n];
	        for (int size = 1; size < n; size *= 2) {
	            for (int leftStart = 0; leftStart < n; leftStart += 2 * size) {
	                int mid = Math.min(leftStart + size - 1, n - 1);
	                int rightEnd = Math.min(leftStart + 2 * size - 1, n - 1);
	                tron(arr, temp, leftStart, mid, rightEnd);
	            }
	        }
	    }

	    private void tron(int[] arr, int[] temp, int left, int mid, int right) {
	        System.arraycopy(arr, left, temp, left, right - left + 1);
	        int i = left, j = mid + 1, k = left;
	        while (i <= mid && j <= right) {
	            arr[k++] = (temp[i] <= temp[j]) ? temp[i++] : temp[j++];
	        }
	        while (i <= mid) arr[k++] = temp[i++];
	    }


	    // Sắp xếp nhanh (Quick Sort) - Khử đệ quy
	    public void sapXepNhanh(int[] arr) {
	        Stack<int[]> stack = new Stack<>();
	        stack.push(new int[] {0, arr.length - 1});
	        
	        while (!stack.isEmpty()) {
	            int[] range = stack.pop();
	            int low = range[0], high = range[1];
	            if (low < high) {
	                int pivotIndex = phanDoan(arr, low, high);
	                stack.push(new int[] {low, pivotIndex - 1});
	                stack.push(new int[] {pivotIndex + 1, high});
	            }
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
}
