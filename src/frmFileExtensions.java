import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class frmFileExtensions extends JFrame {
    private JTextField txtExtension;
    private JTextField txtProgram;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnFind;
    private JButton btnListAll;
    private JList<String> listExtensions;
    private DefaultListModel<String> listModel;
    private FileExtensions fileExtensions;

    public frmFileExtensions() {
        setTitle("File Extensions Default Program");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        listExtensions = new JList<>(listModel);
        listExtensions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        txtExtension = new JTextField(20);
        txtProgram = new JTextField(20);

        btnAdd = new JButton("Add Entry");
        btnDelete = new JButton("Delete Entry");
        btnFind = new JButton("Find Entry");
        btnListAll = new JButton("List All Entries");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEntry();
            }
        });

        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findEntry();
            }
        });

        btnListAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllEntries();
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Extension:"));
        panel.add(txtExtension);
        panel.add(new JLabel("Program:"));
        panel.add(txtProgram);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnFind);
        buttonPanel.add(btnListAll);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(listExtensions), BorderLayout.CENTER);

        loadFromFile();
    }

    private void addEntry() {
        String extension = txtExtension.getText();
        String program = txtProgram.getText();
        if (!extension.isEmpty() && !program.isEmpty() && !fileExtensions.getProgram(extension).equals(program)) {
            fileExtensions.addEntry(extension, program);
            listModel.addElement(extension + " -> " + program);
            saveToFile();
            JOptionPane.showMessageDialog(this, "Entry added successfully.");
            listExtensions.updateUI(); // 更新列表显示
        } else {
            JOptionPane.showMessageDialog(this, "Extension or program is empty or already exists.");
        }
    }

    private void deleteEntry() {
        int index = listExtensions.getSelectedIndex();
        if (index != -1) {
            String entry = listModel.get(index);
            String[] parts = entry.split(" -> ");
            if (parts.length == 2) {
                fileExtensions.removeEntry(parts[0]);
                listModel.remove(index);
                saveToFile();
                JOptionPane.showMessageDialog(this, "Entry deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting entry.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an entry to delete.");
        }
    }

    private void findEntry() {
        String extension = txtExtension.getText();
        String program = fileExtensions.getProgram(extension);
        if (program != null) {
            JOptionPane.showMessageDialog(this, "Default program for " + extension + " is " + program);
        } else {
            JOptionPane.showMessageDialog(this, "No default program found for " + extension);
        }
    }

    private void listAllEntries() {
        listModel.clear();
        Set<String> extensions = fileExtensions.getExtensions();
        for (String ext : extensions) {
            listModel.addElement(ext + " -> " + fileExtensions.getProgram(ext));
        }
    }

    private void loadFromFile() {
        fileExtensions = FileExtensions.loadFromFile("fileExtensions.dat");
        listAllEntries();
    }

    private void saveToFile() {
        fileExtensions.saveToFile("fileExtensions.dat");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frmFileExtensions().setVisible(true));
    }
}