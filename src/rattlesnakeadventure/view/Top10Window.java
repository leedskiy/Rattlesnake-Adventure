package rattlesnakeadventure.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import rattlesnakeadventure.db.DatabaseManager;

import java.awt.*;
import java.util.ArrayList;

public class Top10Window extends Window {
    private ArrayList<Window> windows;
    private DatabaseManager databaseManager;
    private ArrayList<String> top10PlayersList;

    public Top10Window(ArrayList<Window> windows, DatabaseManager databaseManager) {
        setTitle("Rattlesnake Adventure / top 10 scoreboard");
        this.windows = windows;
        this.databaseManager = databaseManager;
        this.top10PlayersList = this.databaseManager.readTop10Players();

        JTable top10Table = new JTable(createTableModel());

        // center the cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < top10Table.getColumnCount(); i++) {
            top10Table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        top10Table.setDefaultEditor(Object.class, null); // remove edit permission
        JScrollPane scrollPane = new JScrollPane(top10Table); // scrollPane

        // contentPane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private DefaultTableModel createTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Place");
        tableModel.addColumn("Name");
        tableModel.addColumn("Score");

        for (int i = 0; i < this.top10PlayersList.size(); i++) {
            String[] parts = this.top10PlayersList.get(i).split(" ");
            String name = parts[0];
            String score = parts[1];
            tableModel.addRow(new Object[] { i + 1, name, score });
        }

        return tableModel;
    }

    @Override
    protected void doOnExit() {
        super.doOnExit();
        windows.remove(this);
    }
}