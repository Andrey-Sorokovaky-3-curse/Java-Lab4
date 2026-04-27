package pro.sorokovsky.hard;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Random;

public class GardenFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private GardenChecker checker;
    private JLabel resultLabel;

    public GardenFrame() {
        setTitle("Перевірка прямокутності грядок");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 500);

        checker = new GardenChecker();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Панель керування
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton randomBtn = new JButton("Випадкова ділянка");
        JButton checkBtn = new JButton("Перевірити");
        JButton clearBtn = new JButton("Очистити");

        controlPanel.add(randomBtn);
        controlPanel.add(checkBtn);
        controlPanel.add(clearBtn);

        // Таблиця
        tableModel = new DefaultTableModel(8, 8) {
            @Override
            public Class<?> getColumnClass(int column) {
                return Integer.class;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(40);
        table.setFont(new Font("Monospaced", Font.BOLD, 14));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(40);
        }

        // Редактор для введення чисел
        table.setDefaultEditor(Integer.class, new DefaultCellEditor(new JTextField()));

        JScrollPane scrollPane = new JScrollPane(table);

        // Результат
        resultLabel = new JLabel("Натисніть 'Перевірити'", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        add(mainPanel);

        // Дії
        randomBtn.addActionListener(e -> generateRandomGarden());
        checkBtn.addActionListener(e -> checkGarden());
        clearBtn.addActionListener(e -> clearGarden());

        generateRandomGarden();
        setLocationRelativeTo(null);
    }

    private void generateRandomGarden() {
        Random rand = new Random();
        int[][] garden = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (rand.nextDouble() < 0.3) {
                    garden[i][j] = rand.nextInt(5) + 1;
                } else {
                    garden[i][j] = 0;
                }
            }
        }

        updateTable(garden);
    }

    private void clearGarden() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(0, i, j);
            }
        }
        resultLabel.setText("Ділянка очищена");
    }

    private void checkGarden() {
        int rows = tableModel.getRowCount();
        int cols = tableModel.getColumnCount();
        int[][] garden = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Object value = tableModel.getValueAt(i, j);
                if (value instanceof Number) {
                    garden[i][j] = ((Number) value).intValue();
                } else {
                    try {
                        garden[i][j] = Integer.parseInt(value.toString());
                    } catch (Exception e) {
                        garden[i][j] = 0;
                    }
                }
            }
        }

        String result = checker.checkGarden(garden);
        resultLabel.setText(result);

        if (result.startsWith("OK")) {
            resultLabel.setForeground(Color.GREEN);
        } else {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CountGardenFrame().setVisible(true);
        });
    }
}