package mypack;

public class ThuatToanSort {
    
    public static void bubbleSort(int[] array, VisualSort visualizer, int speed) throws InterruptedException {
        // Vòng lặp qua mảng để thực hiện sắp xếp
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                // Đánh dấu hai phần tử đang được so sánh
                visualizer.setIndices(j, j + 1);
                // Đổi chỗ nếu phần tử lớn hơn phần tử kế tiếp
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                visualizer.repaint(); // Cập nhật hình ảnh
                Thread.sleep(speed);   // Điều chỉnh tốc độ của trực quan
            }
        }
        visualizer.clearIndices(); // Xóa các đánh dấu sau khi sắp xếp xong
    }

    public static void selectionSort(int[] array, VisualSort visualizer, int speed) throws InterruptedException {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            // Tìm phần tử nhỏ nhất trong phần chưa sắp xếp của mảng
            for (int j = i + 1; j < array.length; j++) {
                visualizer.setIndices(minIndex, j);
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                visualizer.repaint();
                Thread.sleep(speed);
            }
            // Đổi chỗ phần tử nhỏ nhất tìm được với phần tử đầu tiên của phần chưa sắp xếp
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            visualizer.repaint();
        }
        visualizer.clearIndices();
    }
    
    public static void insertionSort(int[] array, VisualSort visualizer, int speed) throws InterruptedException {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            
            // Di chuyển các phần tử lớn hơn `key` sang vị trí sau
            while (j >= 0 && array[j] > key) {
                visualizer.setIndices(j, j + 1);
                array[j + 1] = array[j];
                visualizer.repaint();
                Thread.sleep(speed);
                j--;
            }
            array[j + 1] = key; // Đặt `key` vào vị trí đúng của nó
            visualizer.repaint();
        }
        visualizer.clearIndices();
    }
    
    public static void mergeSort(int[] array, VisualSort visualizer, int speed) throws InterruptedException {
        mergeSortMethod(array, 0, array.length - 1, visualizer, speed);
        visualizer.clearIndices();
    }
    
    // Hàm hỗ trợ đệ quy cho Merge Sort
    private static void mergeSortMethod(int[] array, int left, int right, VisualSort visualizer, int speed) throws InterruptedException {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortMethod(array, left, mid, visualizer, speed);      // Sắp xếp nửa trái
            mergeSortMethod(array, mid + 1, right, visualizer, speed); // Sắp xếp nửa phải
            merge(array, left, mid, right, visualizer, speed);         // Trộn hai nửa đã sắp xếp
        }
    }
    
    // Hàm trộn hai nửa đã sắp xếp trong Merge Sort
    private static void merge(int[] array, int left, int mid, int right, VisualSort visualizer, int speed) throws InterruptedException {
        int n1 = mid - left + 1; // Kích thước của mảng con bên trái
        int n2 = right - mid;    // Kích thước của mảng con bên phải
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        System.arraycopy(array, left, leftArray, 0, n1);   // Sao chép dữ liệu vào mảng con bên trái
        System.arraycopy(array, mid + 1, rightArray, 0, n2); // Sao chép dữ liệu vào mảng con bên phải
        
        int i = 0, j = 0, k = left;
        // Trộn hai mảng con
        while (i < n1 && j < n2) {
            visualizer.setIndices(left + i, mid + 1 + j); // Đánh dấu các phần tử đang được so sánh
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
            visualizer.repaint();
            Thread.sleep(speed);
        }
        
        // Sao chép các phần tử còn lại của leftArray
        while (i < n1) {
            array[k++] = leftArray[i++];
            visualizer.repaint();
            Thread.sleep(speed);
        }
        
        // Sao chép các phần tử còn lại của rightArray
        while (j < n2) {
            array[k++] = rightArray[j++];
            visualizer.repaint();
            Thread.sleep(speed);
        }
    }
    
    public static void quickSort(int[] array, VisualSort visualizer, int speed) throws InterruptedException {
        quickSortMethod(array, 0, array.length - 1, visualizer, speed);
        visualizer.clearIndices();
    }

    // Hàm hỗ trợ đệ quy cho Quick Sort
    private static void quickSortMethod(int[] array, int low, int high, VisualSort visualizer, int speed) throws InterruptedException {
        if (low < high) {
            int pivotIndex = partition(array, low, high, visualizer, speed); // Phân đoạn mảng
            quickSortMethod(array, low, pivotIndex - 1, visualizer, speed);  // Sắp xếp phần trước phân đoạn
            quickSortMethod(array, pivotIndex + 1, high, visualizer, speed); // Sắp xếp phần sau phân đoạn
        }
    }

    // Hàm phân đoạn cho Quick Sort
    private static int partition(int[] array, int low, int high, VisualSort visualizer, int speed) throws InterruptedException {
        int pivot = array[high]; // Chọn phần tử cuối làm chốt (pivot)
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            visualizer.setIndices(j, high); // Đánh dấu chốt và phần tử hiện tại
            if (array[j] < pivot) { // Đổi chỗ nếu phần tử nhỏ hơn chốt
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
            visualizer.repaint();
            Thread.sleep(speed);
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        visualizer.repaint();
        return i + 1; // Trả về chỉ số của chốt
    }
    
}
