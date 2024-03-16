import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameWindow extends JFrame {

   public static JTextField nameTextField;

    public NameWindow() {
        // Setting up the JFrame
        this.setSize(300, 200);  // Set the size of the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Name");
        //this.setLocationRelativeTo(null);

        // Creating a JPanel without a layout manager (null layout)
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Create components
        JLabel nameLabel = new JLabel("Enter your name: ");
        nameTextField = new JTextField();
        JButton enterButton = new JButton("Enter");

        // Manually setting component bounds
        nameLabel.setBounds(10, 20, 120, 25); // x, y, width, height
        nameTextField.setBounds(140, 20, 120, 25);
        enterButton.setBounds(110, 60, 80, 25);

        // Adding components to JPanel
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(enterButton);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when Enter button is clicked
                String enteredName = nameTextField.getText(); // Get the text from the text field
                System.out.println("Entered Name: " + enteredName); // For demonstration, print it out

                // Close the frame
                dispose();
            }
        });

        // Adding panel to JFrame
        this.add(panel);

        // Make the frame visible
        this.setVisible(true);
    }

}
