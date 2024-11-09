package cnts21s4;

import javax.swing.*;

public class CrimeReportApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (authenticateUser()) {
                new CrimeReportView();
            } else {
                JOptionPane.showMessageDialog(null, "Authentication failed. Exiting program.", "Authentication Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        });
    }

    private static boolean authenticateUser() {
        String enteredUsername = JOptionPane.showInputDialog("Enter username:");
        String enteredPassword = JOptionPane.showInputDialog("Enter password:");

        
        return enteredUsername != null && enteredPassword != null &&
                enteredUsername.equals("jerome") && enteredPassword.equals("cantos");
    }
}
