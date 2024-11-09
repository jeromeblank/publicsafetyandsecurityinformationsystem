package cnts21s4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.border.TitledBorder;

public class CrimeReportView extends JFrame {
    private CrimeInputPanel inputPanel = new CrimeInputPanel();
    private JTextArea displayArea = new JTextArea(20, 50);
    private JButton submitButton = new JButton("Submit");
    private JButton exitButton = new JButton("Exit");
    private JButton viewDataButton = new JButton("View Saved Data");

    private CrimeReportSystem crimeReportSystem = new CrimeReportSystem(); 

    public CrimeReportView() {
        setTitle("Public Safety and Security Information Systems");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Crime crime = inputPanel.getCrime();
                if (crime != null) {
                    displayArea.append(crime.toString() + "\n");
                    inputPanel.clearFields();
                    crimeReportSystem.addCrime(crime); // Add the crime to the crimeReportSystem
                    saveToFile(crime.toString());
                    showSuccessDialog("Crime Report Submitted submitted successfully!");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        CrimeReportView.this,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    displayEmergencyContacts();
                    System.exit(0);
                }
            }
        });

        viewDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySavedData();
            }
        });

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Improve CrimeInputPanel appearance
        TitledBorder inputPanelBorder = BorderFactory.createTitledBorder("Crime Report Information");
        inputPanel.setBorder(inputPanelBorder);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Add a title to the display area
        displayArea.setBorder(BorderFactory.createTitledBorder("Crime Reports"));
        mainPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Improve button panel appearance
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(viewDataButton); // Add the viewDataButton to the button panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveToFile(String crimeReport) {
        try (FileWriter fileWriter = new FileWriter("crime_reports.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(crimeReport);
            bufferedWriter.newLine();

        } catch (IOException e) {
            showErrorDialog("Error saving to file: " + e.getMessage());
        }
    }

    private void displayEmergencyContacts() {
        JOptionPane.showMessageDialog(this, "Emergency Contacts Directory:\nPolice: 911\nMedical: 911\nFire: 911");
    }

    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displaySavedData() {
    // Create a new dialog to display saved data
    JDialog viewDataDialog = new JDialog(this, "View Saved Data", true);
    JTextArea savedDataArea = new JTextArea(20, 50);

    // Read the content from the crime_reports.txt file and append it to savedDataArea
    try (BufferedReader reader = new BufferedReader(new FileReader("crime_reports.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            savedDataArea.append(line + "\n");
        }
    } catch (IOException e) {
        showErrorDialog("Error reading from file: " + e.getMessage());
    }

    savedDataArea.setEditable(false);

    // Add the savedDataArea to the dialog
    viewDataDialog.add(new JScrollPane(savedDataArea));
    viewDataDialog.pack();
    viewDataDialog.setLocationRelativeTo(this);
    viewDataDialog.setVisible(true);
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrimeReportView());
    }
}
