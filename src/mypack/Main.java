package mypack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    
    private static boolean isSorting = false;  // Cờ để kiểm soát trạng thái sắp xếp, ngăn chặn việc thực hiện nhiều thuật toán cùng lúc.
    private static int sortSpeed = 50;  // Đặt tốc độ sắp xếp mặc định là 50ms giữa mỗi bước sắp xếp.
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sort Visualizer");  // Tạo cửa sổ chính.
        VisualSort visualizer = new VisualSort(50);  // Khởi tạo đối tượng VisualSort với kích thước mảng ban đầu là 50.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Đóng chương trình khi tắt cửa sổ.
        frame.setSize(800, 500);  // Kích thước cửa sổ.
        frame.setLayout(new BorderLayout());  // Đặt layout của frame là BorderLayout.
        frame.add(visualizer, BorderLayout.CENTER);  // Thêm visualizer vào phần trung tâm của cửa sổ.

        JPanel controlPanel = new JPanel();  // Bảng điều khiển chứa các nút sắp xếp.
        controlPanel.setLayout(new FlowLayout());  // Đặt layout của controlPanel là FlowLayout.

        // Tạo các nút cho các thuật toán sắp xếp.
        JButton bubbleSortButton = new JButton("Bubble Sort");
        JButton selectionSortButton = new JButton("Selection Sort");
        JButton insertionSortButton = new JButton("Insertion Sort");
        JButton mergeSortButton = new JButton("Merge Sort");
        JButton quickSortButton = new JButton("Quick Sort");

        JButton resetButton = new JButton("Reset");  // Nút để đặt lại mảng.
        JButton setSizeButton = new JButton("Set Array Size");  // Nút để thay đổi kích thước mảng.
        
        // Thêm thanh trượt điều chỉnh tốc độ.
        JSlider speedSlider = new JSlider(1, 100, sortSpeed);  // Thanh trượt cho tốc độ từ 1 đến 100 ms.
        speedSlider.setMajorTickSpacing(10);
        speedSlider.setPaintTicks(true);  // Hiển thị các vạch đánh dấu trên thanh trượt.
        speedSlider.setPaintLabels(true);  // Hiển thị nhãn cho các vạch.
        speedSlider.addChangeListener(e -> sortSpeed = speedSlider.getValue());  // Cập nhật tốc độ khi thay đổi giá trị trên thanh trượt.

        // Sự kiện cho nút Bubble Sort
        bubbleSortButton.addActionListener(e -> {
            if (!isSorting) {  // Kiểm tra nếu không có thuật toán nào đang chạy.
                isSorting = true;
                new Thread(() -> {  // Chạy thuật toán trong luồng riêng biệt.
                    try {
                        ThuatToanSort.bubbleSort(visualizer.getArray(), visualizer, sortSpeed);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        isSorting = false;  // Đặt lại cờ sau khi hoàn thành.
                    }
                }).start();
            }
        });
        
        // Tương tự, sự kiện cho các nút sắp xếp khác.
        selectionSortButton.addActionListener(e -> {
            if (!isSorting) {
                isSorting = true;
                new Thread(() -> {
                    try {
                        ThuatToanSort.selectionSort(visualizer.getArray(), visualizer, sortSpeed);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        isSorting = false;
                    }
                }).start();
            }
        });

        insertionSortButton.addActionListener(e -> {
            if (!isSorting) {
                isSorting = true;
                new Thread(() -> {
                    try {
                        ThuatToanSort.insertionSort(visualizer.getArray(), visualizer, sortSpeed);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        isSorting = false;
                    }
                }).start();
            }
        });
        
        mergeSortButton.addActionListener(e -> {
            if (!isSorting) {
                isSorting = true;
                new Thread(() -> {
                    try {
                        ThuatToanSort.mergeSort(visualizer.getArray(), visualizer, sortSpeed);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        isSorting = false;
                    }
                }).start();
            }
        });
        
        quickSortButton.addActionListener(e -> {
            if (!isSorting) {
                isSorting = true;
                new Thread(() -> {
                    try {
                        ThuatToanSort.quickSort(visualizer.getArray(), visualizer, sortSpeed);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        isSorting = false;
                    }
                }).start();
            }
        });

        // Nút Reset - tạo lại mảng ngẫu nhiên.
        resetButton.addActionListener(e -> {
            if (!isSorting) {
                visualizer.generateRandomArray();  // Gọi hàm để tạo mảng ngẫu nhiên mới.
            }
        });
        
        // Nút Set Array Size - thay đổi kích thước mảng.
        setSizeButton.addActionListener(e -> {
            if (!isSorting) {
                String sizeStr = JOptionPane.showInputDialog(frame, "Nhập kích thước mảng:", "Array Size", JOptionPane.PLAIN_MESSAGE);
                if (sizeStr != null && !sizeStr.isEmpty()) {
                    try {
                        int size = Integer.parseInt(sizeStr);  // Chuyển đổi kích thước từ chuỗi sang số nguyên.
                        if (size > 0) {
                            visualizer.setArraySize(size);  // Đặt kích thước mới cho mảng.
                        } else {
                            JOptionPane.showMessageDialog(frame, "Hãy nhập số dương.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Vui lòng nhập số hợp lệ.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Thêm các nút vào controlPanel.
        controlPanel.add(bubbleSortButton);
        controlPanel.add(selectionSortButton);
        controlPanel.add(insertionSortButton);
        controlPanel.add(mergeSortButton);
        controlPanel.add(quickSortButton);
        
        controlPanel.add(resetButton);
        controlPanel.add(setSizeButton);
        controlPanel.add(new JLabel("Speed:"));
        controlPanel.add(speedSlider);

        frame.add(controlPanel, BorderLayout.NORTH);  // Thêm controlPanel vào phần phía trên của cửa sổ.

        frame.setVisible(true);  // Hiển thị cửa sổ.
    }
}
