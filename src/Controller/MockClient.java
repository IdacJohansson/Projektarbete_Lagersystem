package Controller;


import Model.AccessLevel;

import javax.swing.*;


public class MockClient extends JFrame {
    private JPanel mainPanel;
    private JLabel WelcomeLabel;
    private JPanel Panel2;
    private JButton butik;
    private JButton lager;
    private JLabel Text;
    private JButton inköp;
    private JFrame mockServer;

    public MockClient() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();

        butik.addActionListener(e -> {
            if (butik == e.getSource()) {
                mockServer = new MockServer(AccessLevel.BUTIK);
                dispose();
            }
        });

        lager.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.LAGER);
            dispose();
        });

        inköp.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.INKÖP);
            dispose();
        });
    }
}
