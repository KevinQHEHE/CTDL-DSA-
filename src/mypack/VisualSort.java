package mypack;

import javax.swing.*;
import java.awt.*;

public class VisualSort extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    private int[] array; // Mảng số nguyên đại diện cho dữ liệu cần sắp xếp
    private int currentIndex = -1; // Chỉ số phần tử đang được xử lý (đánh dấu bằng màu đỏ)
    private int comparingIndex = -1; // Chỉ số phần tử đang so sánh với phần tử xử lý (đánh dấu bằng màu xanh)

    // Hàm khởi tạo để tạo đối tượng VisualSort với kích thước mảng
    public VisualSort(int size) {
        setArraySize(size); // Thiết lập kích thước của mảng và tạo giá trị ngẫu nhiên
    }

    // Thiết lập kích thước mảng và tạo mảng ngẫu nhiên theo kích thước đó
    public void setArraySize(int size) {
        array = new int[size];
        generateRandomArray();
        repaint(); // Vẽ lại bảng trực quan
    }

    // Sinh giá trị ngẫu nhiên cho mảng với mỗi phần tử là số ngẫu nhiên từ 10 đến 400
    public void generateRandomArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 400) + 10;
        }
        repaint(); // Vẽ lại bảng trực quan với mảng ngẫu nhiên
    }

    // Thiết lập các chỉ số để đánh dấu phần tử hiện tại và phần tử đang so sánh
    public void setIndices(int currentIndex, int comparingIndex) {
        this.currentIndex = currentIndex;
        this.comparingIndex = comparingIndex;
    }

    // Xóa các đánh dấu chỉ số sau khi hoàn thành một bước sắp xếp
    public void clearIndices() {
        currentIndex = -1;
        comparingIndex = -1;
        repaint(); // Vẽ lại bảng mà không có đánh dấu
    }

    // Phương thức vẽ lại bảng trực quan hóa mảng và các bước sắp xếp
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / array.length; // Tính chiều rộng của mỗi cột dựa trên chiều rộng panel
        for (int i = 0; i < array.length; i++) {
            // Đổi màu để phân biệt các phần tử đang xử lý
            if (i == currentIndex) {
                g.setColor(Color.RED); // Phần tử đang xử lý màu đỏ
            } else if (i == comparingIndex) {
                g.setColor(Color.BLUE); // Phần tử đang so sánh màu xanh
            } else {
                g.setColor(Color.GRAY); // Các phần tử khác màu xám
            }
            // Vẽ cột đại diện cho giá trị của phần tử tại vị trí i
            g.fillRect(i * width, getHeight() - array[i], width, array[i]);
        }
    }

    // Lấy mảng hiện tại để thực hiện các thuật toán sắp xếp
    public int[] getArray() {
        return array;
    }
}
