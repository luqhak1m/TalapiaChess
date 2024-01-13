import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Create JFrame container
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // Create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create buttons
        JButton startButton = new JButton("Start");
        JButton loadButton = new JButton("Load");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to the buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Start button click
                System.out.println("Start button clicked");
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Load button click
                System.out.println("Load button clicked");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Exit button click
                System.exit(0); // Exit the application
            }
        });

        // Add buttons to panel
        panel.add(startButton);
        panel.add(loadButton);
        panel.add(exitButton);

        // Add panel to frame
        add(panel);

        // Set the window to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu();
            }
        });
    }
}

