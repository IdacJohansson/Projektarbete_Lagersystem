package Controller;

import Model.AccessLevel;
import Model.Article;
import Model.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderHandler extends JFrame {
    private JPanel panel1;
    private JPanel panel2;
    private JLabel headLine;
    private JTextField artNrField;
    private JButton skicka;
    private JLabel artNrLabel;
    private JTextField antalField;
    private JLabel antalLabel;

    private JLabel felAntalLabel;
    private JLabel felArtNrLabel;
    private JButton stäng;
    private String artNr;
    private int antal;

    private Database database;
    private AccessLevel accessLevel;
    private JFrame mockServer;
    private Article tempArticle;

    public OrderHandler(AccessLevel accessLevel) {  //tar in mockserverns accesslevel
        this.accessLevel = accessLevel;
        database = new Database();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);
        pack();

        skicka.addActionListener(ae -> {

            artNr = artNrField.getText().trim();
            antal = Integer.parseInt(antalField.getText().trim());
            setTempArticle();
            if (tempArticle != null) {
                if (accessLevel == AccessLevel.BUTIK) {
                    try {
                        tempArticle.subtractFromBalance(antal);
                    } catch (IllegalArgumentException e) {
                        if (e.getMessage().contains("negative")) {
                            felAntalLabel.setText("Antal måste vara större än 0");
                        } else if (e.getMessage().contains("larger")) {
                            felAntalLabel.setText("Det finns inte så många exemplar på lagret");
                        }
                    }
                } else if (accessLevel == AccessLevel.INKÖP) {
                    try {
                        tempArticle.addToBalance(antal);
                    } catch (IllegalArgumentException e) {
                        felAntalLabel.setText("Antal måste vara större än 0");
                    }
                }
            } else {
                felArtNrLabel.setText("Inget plagg med artikelnumret finns");
            }

        });
        stäng.addActionListener(e -> {
            mockServer = new MockServer(accessLevel);
            dispose();
        });
    }

    private void setTempArticle() {
        tempArticle = database.getArticle(artNr);
    }

}
