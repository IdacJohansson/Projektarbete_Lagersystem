package Controller;


import Model.AccessLevel;

import javax.swing.*;


public class MockClient extends JFrame {
    private JPanel mainPanel;
    private JPanel Panel2;
    private JButton storeButton;
    private JButton warehouse;
    private JLabel headLine;
    private JButton purchase;
    private JLabel headLogo;
    private JFrame mockServer;

    public MockClient() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 600);
        setLocationRelativeTo(null);
        pack();

        storeButton.addActionListener(e -> {
            if (storeButton == e.getSource()) {
                mockServer = new MockServer(AccessLevel.STORE);
                dispose();
            }
        });

        warehouse.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.WAREHOUSE);
            dispose();
        });

        purchase.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.PURCHASE_DEPARTMENT);
            dispose();
        });
    }
}
