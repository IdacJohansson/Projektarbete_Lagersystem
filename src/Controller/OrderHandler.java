package Controller;

import Model.AccessLevel;
import Model.Database;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OrderHandler extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JLabel headLine;
    private JTextField artNrField;
    private JButton send;
    private JLabel artNrLabel;
    private JTextField amountField;
    private JLabel amountLabel;

    private JLabel wrongAmountLabel;
    private JLabel wrongArtNrLabel;
    private JButton returnButton;
    private JLabel confirmation;
    private String artNr;
    private int amount;

    private final Database database;
    private TaskController taskController;
    private AccessLevel accessLevel;
    private JFrame mockServer;
    int categoryShown;

    public OrderHandler(MockServer mockServer, Database database, AccessLevel accessLevel, int category,TaskController taskController) {  //tar in mockserverns accesslevel
        this.mockServer = mockServer;
        this.accessLevel = accessLevel;
        this.database = database;
        this.categoryShown = category;
        this.taskController=taskController;
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        pack();

        send.addActionListener(ae -> {
            wrongArtNrLabel.setText("");
            wrongAmountLabel.setText("");
            confirmation.setText("");

            artNr = artNrField.getText().trim();
            if (!amountField.getText().isBlank() && amountField.getText().chars().allMatch(Character::isDigit)) {
                amount = Integer.parseInt(amountField.getText().trim());
                if (database.getArticle(artNr) != null) {
                    if (accessLevel == AccessLevel.STORE) {
                        try {
                            //database.subtractBalance(artNr, amount);
                            amount= -amount;
                            taskController.addToList(artNr,amount);
                            confirmation.setText("Order sent successfully");
                            System.out.println("Email sent to warehouse");
                        } catch (IllegalArgumentException e) {
                            if (e.getMessage().contains("negative")) {
                                wrongAmountLabel.setText("Amount must be greater than 0");
                            } else if (e.getMessage().contains("larger")) {
                                wrongAmountLabel.setText("Order is too large");
                            }
                        }
                    } else if (accessLevel == AccessLevel.PURCHASE_DEPARTMENT) {
                        try {
                            //database.addBalance(artNr, amount);
                            taskController.addToList(artNr,amount);
                            confirmation.setText("Order sent successfully");
                            System.out.println("Order sent to manufacturer");
                        } catch (IllegalArgumentException e) {
                            wrongAmountLabel.setText("Amount must be greater than 0");
                        }
                    }
                } else {
                    wrongArtNrLabel.setText("Article does not exist");
                }
            }
        });
        returnButton.addActionListener(e -> {
            mockServer.setEnabled(true);
            mockServer.showSelectedList(categoryShown);
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mockServer.setEnabled(true);
            }
        });
    }


}
