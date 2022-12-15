package Controller;

import Model.Database;

import javax.swing.*;

public class OrderHandler extends JFrame{
    private JPanel panel1;
    private JPanel panel2;
    private JLabel headLine;
    private JTextField artNrField;
    private JButton Skicka;
    private JLabel artNrLabel;
    private JTextField antalField;
    private JLabel antalLabel;
    private JLabel bekräftelse;
    private Database database;
    int artNr;
    int antal;
    public OrderHandler() {

        artNrField.addActionListener(e -> {
            artNr = Integer.parseInt(artNrField.getText().trim());
            if (!isAvailable()) {
                bekräftelse.setText("Inget plagg med artikelnumret finns");
            }
        });
        antalField.addActionListener(e -> {//lagra ner antal till antal
            antal = Integer.parseInt(antalField.getText().trim());
            if (!isPossible()){
                bekräftelse.setText("Det finns inte så många tillgängliga plagg");
            }
        });
        Skicka.addActionListener(e -> {
            //TODO ta informationen från fälten och skicka Database?
            //ALT subtractArticles();
            this.dispose();
        });
    }
    private void subtractArticles (){
        //göra uppdateringen av listan? borde kanske istället bara ligga som ett metodanrop av setSaldo() el liknande

    }
    private boolean isAvailable(){
        //kolla att artikelnumret finns i listan över saluförda produkter
        return true;
    }
    private boolean isPossible(){
       // return antal <= article.getSaldo; //kontroll att beställnignen är möjlig
        return true;
    }

}
