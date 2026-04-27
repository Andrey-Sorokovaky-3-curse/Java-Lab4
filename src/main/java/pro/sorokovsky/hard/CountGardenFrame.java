package pro.sorokovsky.hard;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Random;

public class CountGardenFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private GardenCounter counter;
    private GardenChecker checker;
    private JLabel resultLabel;

    public CountGardenFrame() {
        setTitle("Садова ділянка - Аналіз грядок");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 600);

        counter = new GardenCounter();
        checker = new GardenChecker();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton randomBtn = new JButton("🎲 Випадкова ділянка");
        JButton analyzeBtn = new JButton("🔍 Аналізувати");
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
        table.setRowHeight(40);
        table.setFont(new Font("Monospaced", Font.BOLD, 14));
        table.setGridColor(Color.GRAY);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(i).setPreferredWidth(60);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        // Результат
        resultLabel = new JLabel("Режим: перевірка форми", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Легенда
        JPanel legendPanel = new JPanel(new GridLayout(2, 1));
        JLabel legend1 = new JLabel("📌 0 = порожньо | 1,2,3... = номери грядок");
        JLabel legend2 = new JLabel("⚠️ Грядки повинні бути прямокутними та не дотикатися по горизонталі/вертикалі");
        legend2.setFont(new Font("Arial", Font.ITALIC, 10));
        legend2.setForeground(Color.GRAY);
        legendPanel.add(legend1);
        legendPanel.add(legend2);

        mainPanel.add(controlPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(legendPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

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
                {0, 0, 0, 0, 0, 3, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        updateTable(defaultGarden);
    }

    private void generateRandomGarden() {
        Random rand = new Random();
        int[][] garden = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (rand.nextDouble() < 0.2) {
                    garden[i][j] = rand.nextInt(5) + 1;
                } else {
                    garden[i][j] = 0;
                }
            }
        }
        updateTable(garden);
        resultLabel.setText("Випадкова ділянка згенерована");
        resultLabel.setForeground(Color.BLACK);
    }

    private void clearGarden() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(0, i, j);
            }
        }
        resultLabel.setText("Ділянка очищена");
        resultLabel.setForeground(Color.BLACK);
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

            String result = checker.checkGarden(garden);

            resultLabel.setText(result);

            if (result.startsWith("OK")) {
                result = counter.countBeds(garden);
                if (result.startsWith("Кількість")) {
                    resultLabel.setText(result);
                    resultLabel.setForeground(new Color(0, 150, 0));
                }
            } else {
                resultLabel.setForeground(Color.RED);
            }

        } catch (Exception e) {
            resultLabel.setText("ПОМИЛКА: Некоректні дані");
            resultLabel.setForeground(Color.RED);
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