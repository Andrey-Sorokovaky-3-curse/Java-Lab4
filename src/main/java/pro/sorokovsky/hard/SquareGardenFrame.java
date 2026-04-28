package pro.sorokovsky.hard;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Random;

public class SquareGardenFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private GardenSquareCounter squareCounter;
    private JLabel resultLabel;
    private JLabel detailLabel;

    public SquareGardenFrame() {
        setTitle("Садова ділянка - Пошук квадратних грядок");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(750, 650);

        squareCounter = new GardenSquareCounter();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Панель керування
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton randomBtn = new JButton("🎲 Випадкова ділянка");
        JButton analyzeBtn = new JButton("🔍 Знайти квадратні грядки");
        JButton clearBtn = new JButton("🗑 Очистити");

        controlPanel.add(randomBtn);
        controlPanel.add(analyzeBtn);
        controlPanel.add(clearBtn);

        // Таблиця
        String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"};
        tableModel = new DefaultTableModel(columns, 8) {
            @Override
            public Class<?> getColumnClass(int column) {
                return Integer.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(45);
        table.setFont(new Font("Monospaced", Font.BOLD, 16));
        table.setGridColor(Color.GRAY);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(i).setPreferredWidth(65);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        // Панель результатів
        JPanel resultPanel = new JPanel(new GridLayout(2, 1));
        resultLabel = new JLabel("Натисніть 'Знайти квадратні грядки'", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailLabel = new JLabel("", SwingConstants.CENTER);
        detailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        resultPanel.add(resultLabel);
        resultPanel.add(detailLabel);

        // Легенда
        JPanel legendPanel = new JPanel(new GridLayout(3, 1));
        JLabel legend1 = new JLabel("📌 0 = порожньо | 1,2,3... = номери грядок");
        JLabel legend2 = new JLabel("📐 Прямокутна грядка: всі клітинки з однаковим номером утворюють прямокутник");
        JLabel legend3 = new JLabel("⬛ Квадратна грядка: висота = ширина (наприклад, 2×2, 3×3)");
        legend2.setFont(new Font("Arial", Font.ITALIC, 10));
        legend3.setFont(new Font("Arial", Font.ITALIC, 10));
        legend3.setForeground(new Color(0, 100, 0));
        legendPanel.add(legend1);
        legendPanel.add(legend2);
        legendPanel.add(legend3);

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(legendPanel, BorderLayout.NORTH);
        southPanel.add(resultPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Дії
        randomBtn.addActionListener(e -> generateRandomGarden());
        analyzeBtn.addActionListener(e -> analyzeGarden());
        clearBtn.addActionListener(e -> clearGarden());

        initDefaultGarden();
        setLocationRelativeTo(null);
    }

    private void initDefaultGarden() {
        int[][] defaultGarden = {
                {1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 2, 2, 0, 0, 0},
                {0, 0, 2, 2, 2, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 3, 0},
                {0, 0, 0, 0, 0, 3, 3, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        updateTable(defaultGarden);
    }

    private void generateRandomGarden() {
        Random rand = new Random();
        int[][] garden = new int[8][8];

        // Генеруємо випадкові прямокутні грядки
        for (int b = 1; b <= 4; b++) {
            if (rand.nextDouble() < 0.5) {
                int height = rand.nextInt(3) + 1;
                int width = rand.nextInt(3) + 1;
                int startRow = rand.nextInt(8 - height);
                int startCol = rand.nextInt(8 - width);

                // Перевіряємо чи місце вільне
                boolean free = true;
                for (int i = startRow; i < startRow + height; i++) {
                    for (int j = startCol; j < startCol + width; j++) {
                        if (garden[i][j] != 0) free = false;
                    }
                }

                if (free) {
                    for (int i = startRow; i < startRow + height; i++) {
                        for (int j = startCol; j < startCol + width; j++) {
                            garden[i][j] = b;
                        }
                    }
                }
            }
        }

        updateTable(garden);
        resultLabel.setText("Випадкова ділянка згенерована");
        resultLabel.setForeground(Color.BLACK);
        detailLabel.setText("");
    }

    private void clearGarden() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(0, i, j);
            }
        }
        resultLabel.setText("Ділянка очищена");
        resultLabel.setForeground(Color.BLACK);
        detailLabel.setText("");
    }

    private void analyzeGarden() {
        try {
            int rows = tableModel.getRowCount();
            int cols = tableModel.getColumnCount();
            int[][] garden = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Object value = tableModel.getValueAt(i, j);
                    if (value == null || value.toString().trim().isEmpty()) {
                        garden[i][j] = 0;
                    } else {
                        try {
                            garden[i][j] = Integer.parseInt(value.toString().trim());
                            if (garden[i][j] < 0) garden[i][j] = 0;
                        } catch (NumberFormatException e) {
                            garden[i][j] = 0;
                        }
                    }
                }
            }

            String result = squareCounter.countSquareBeds(garden);
            resultLabel.setText(result);

            if (result.startsWith("ПОМИЛКА")) {
                resultLabel.setForeground(Color.RED);
                detailLabel.setText("");
            } else if (result.startsWith("Квадратних грядок")) {
                resultLabel.setForeground(new Color(0, 150, 0));
                detailLabel.setText("📊 Всього прямокутних грядок: " + squareCounter.getTotalRectCount() +
                        " | Квадратних: " + squareCounter.getSquareCount());
                detailLabel.setForeground(new Color(0, 100, 0));
            } else if (result.startsWith("Всі грядки квадратні")) {
                resultLabel.setForeground(new Color(0, 150, 0));
                detailLabel.setText("🎉 Вітаємо! Ідеальна квадратна ділянка!");
                detailLabel.setForeground(new Color(0, 100, 0));
            } else {
                resultLabel.setForeground(Color.BLACK);
                detailLabel.setText("");
            }

        } catch (Exception e) {
            resultLabel.setText("ПОМИЛКА: Некоректні дані");
            resultLabel.setForeground(Color.RED);
            detailLabel.setText("");
        }
    }

    private void updateTable(int[][] garden) {
        for (int i = 0; i < garden.length && i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < garden[i].length && j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(garden[i][j], i, j);
            }
        }
    }
}