package Controller;

import Model.AccessLevel;
import Model.Article;
import Model.Database;
import Model.Garment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private JLabel bekräftelse;
    private String artNr;
    private int antal;

    private final Database database;
    private AccessLevel accessLevel;
    private final JFrame mockServer;
    private Article tempArticle;
    int categoryShown;

    public OrderHandler(MockServer mockServer, Database database, AccessLevel accessLevel, int category) {  //tar in mockserverns accesslevel
        this.mockServer = mockServer;
        this.accessLevel = accessLevel;
        this.database = database;
        this.categoryShown = category;
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        pack();

        skicka.addActionListener(ae -> {
            felArtNrLabel.setText("");
            felAntalLabel.setText("");
            bekräftelse.setText("");

            artNr = artNrField.getText().trim();
            antal = Integer.parseInt(antalField.getText().trim());
            setTempArticle();
            if (tempArticle != null) {
                if (accessLevel == AccessLevel.BUTIK) {
                    try {
                        tempArticle.subtractFromBalance(antal);
                        bekräftelse.setText("Beställning skickad");
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
                        bekräftelse.setText("Beställning skickad");
                    } catch (IllegalArgumentException e) {
                        felAntalLabel.setText("Antal måste vara större än 0");
                    }
                }
            } else {
                felArtNrLabel.setText("Inget plagg med artikelnumret finns");
            }

        });
        stäng.addActionListener(e -> {
            mockServer.setEnabled(true);
            switch (categoryShown) {
                case -1 -> mockServer.showAll(mockServer.database.getListOfArtNr());
                case 0 -> mockServer.showList(mockServer.database.getCategory(Garment.SWEATER));
                case 1 -> mockServer.showList(mockServer.database.getCategory(Garment.TROUSER));
                case 2 -> mockServer.showList(mockServer.database.getCategory(Garment.T_SHIRT));
                case 3 -> mockServer.showList(mockServer.database.getCategory(Garment.SKIRT));
                case 4 -> mockServer.showList(mockServer.database.getCategory(Garment.DRESS));
            }
            dispose();
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                mockServer.setEnabled(true);
                switch (categoryShown) {
                    case -1 -> mockServer.showAll(mockServer.database.getListOfArtNr());
                    case 0 -> mockServer.showList(mockServer.database.getCategory(Garment.SWEATER));
                    case 1 -> mockServer.showList(mockServer.database.getCategory(Garment.TROUSER));
                    case 2 -> mockServer.showList(mockServer.database.getCategory(Garment.T_SHIRT));
                    case 3 -> mockServer.showList(mockServer.database.getCategory(Garment.SKIRT));
                    case 4 -> mockServer.showList(mockServer.database.getCategory(Garment.DRESS));
                }
                dispose();
            }
        });
    }

    private void setTempArticle() {
        tempArticle = database.getArticle(artNr);
    }

}
