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
    String artNr;
    int antal;

    private Database database = new Database();
    private JFrame mockServer;
    private Article tempArticle;

    public OrderHandler() {


        skicka.addActionListener(ae -> {

            artNr = artNrField.getText().trim();
            antal = Integer.parseInt(antalField.getText().trim());
            setTempArticle();
            if (tempArticle != null) {
                try {
                    tempArticle.subtractFromBalance(antal);
                } catch (IllegalArgumentException e) {
                    if (e.getMessage().contains("negative")) {
                        felAntalLabel.setText("Antal måste vara större än 0");
                    }else if (e.getMessage().contains("larger")) {
                        felAntalLabel.setText("Det finns inte så många exemplar på lagret");
                    }
                }
            } else {
                felArtNrLabel.setText("Inget plagg med artikelnumret finns");
            }

        });
        stäng.addActionListener(e -> {
            mockServer = new MockServer(AccessLevel.BUTIK);
            dispose();
        });
    }

    private Article getArticle() {
        if (database.getArticle(artNr) != null) {
            tempArticle = database.getArticle(artNr);
        } else {
            tempArticle = null;
        }
        return tempArticle;
    }

    private void setTempArticle() {
        tempArticle = database.getArticle(artNr);
    }

}
