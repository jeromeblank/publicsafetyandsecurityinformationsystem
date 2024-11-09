package cnts21s4;

import javax.swing.*;
import java.awt.*;

public class CrimeInputPanel extends JPanel {
    private JTextField typeField = new JTextField(20);
    private JTextField timeField = new JTextField(20);
    private JTextField dateField = new JTextField(20);
    private JTextField locationField = new JTextField(20);

    public CrimeInputPanel() {
        setLayout(new GridLayout(4, 2));
        add(new JLabel("Type of Incident: "));
        add(typeField);
        add(new JLabel("Time Incident: "));
        add(timeField);
        add(new JLabel("Date of Incident: "));
        add(dateField);
        add(new JLabel("Location of Incident: "));
        add(locationField);
    }

    public Crime getCrime() {
        String type = typeField.getText().trim();
        String time = timeField.getText().trim();
        String date = dateField.getText().trim();
        String location = locationField.getText().trim();

        if (type.isEmpty() || time.isEmpty() || date.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new Crime(type, time, date, location);
    }

    public void clearFields() {
        typeField.setText("");
        timeField.setText("");
        dateField.setText("");
        locationField.setText("");
    }
}
