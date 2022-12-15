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


    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel mainPanel;
}
