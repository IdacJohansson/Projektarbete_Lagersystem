package Controller;

import Model.AccessLevel;

import javax.swing.*;

public class MockServer extends JFrame {

    private final AccessLevel accessLevel;

    public MockServer(AccessLevel accesLevel) {
        this.accessLevel = accesLevel;
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
    }


    private JPanel mainPanel;
    private JButton button1;
    private JTextField searchInput;
    private JRadioButton pantsRadioButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
