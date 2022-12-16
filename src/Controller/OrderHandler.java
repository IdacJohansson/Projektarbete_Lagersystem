package Controller;

import Model.AccessLevel;
import Model.Article;
import Model.Database;
import Model.Garment;

import javax.swing.*;
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
    private JFrame mockServer;
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
            if (database.getArticle(artNr) != null) {
                if (accessLevel == AccessLevel.BUTIK) {
                    try {
                        database.subtractBalance(artNr, antal);
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
                        database.addBalance(artNr, antal);
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
                case 0 -> mockServer.showAll(database.getListOfArtNr());
                case 1 -> mockServer.showList(database.getCategory(Garment.SWEATER));
                case 2 -> mockServer.showList(database.getCategory(Garment.TROUSER));
                case 3 -> mockServer.showList(database.getCategory(Garment.T_SHIRT));
                case 4 -> mockServer.showList(database.getCategory(Garment.SKIRT));
                case 5 -> mockServer.showList(database.getCategory(Garment.DRESS));
            }
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
