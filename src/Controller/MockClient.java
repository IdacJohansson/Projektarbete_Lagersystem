package Controller;


import Model.AccessLevel;

import javax.swing.*;


public class MockClient extends JFrame {
    private JPanel mainPanel;
    private JPanel Panel2;
    private JButton store;
    private JButton wearhouse;
    private JLabel chooseDepartment;
    private JButton purechasingDepartment;
    private JLabel headLogo;
    private JFrame mockServer;

    public MockClient() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 600);
        setLocationRelativeTo(null);
        pack();

        store.addActionListener(e -> {
            if (store == e.getSource()) {
                mockServer = new MockServer(AccessLevel.BUTIK);
                dispose();
            }
        });

        wearhouse.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.LAGER);
            dispose();
        });

        purechasingDepartment.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.INKÖP);
            dispose();
        });
    }
}
