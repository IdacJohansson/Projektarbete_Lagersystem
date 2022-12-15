package Controller;

import Model.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NewArticle extends JFrame {

    private JPanel mainPanel;
    private JTextField articleNrField;
    private JComboBox<Garment> garment;
    private JComboBox<Size> size;
    private JComboBox<Color> color;
    private JTextField balanceField;
    private JButton addArticle;

    public NewArticle(JFrame parentWindow, Database database) {
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        //garment.a("Tröja", (Garment) Garment.TRÖJA);
        garment.addItem(Garment.BYXA);
        garment.addItem(Garment.T_SHIRT);
        garment.addItem(Garment.KJOL);
        garment.addItem(Garment.KLÄNNING);
        addArticle.addActionListener(e -> {
            if (garment != null && getColorComboBox() != null && getSizeComboBox() != null) {
                try {
                    database.createArticle(new Article(articleNrField.getText(),(Garment) garment.getSelectedItem(),
                            getColorComboBox(), getSizeComboBox(), Integer.parseInt(balanceField.getText())));
                    JOptionPane.showMessageDialog(null, "Article was added");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentWindow.setEnabled(true);
            }
        });
    }

    private Size getSizeComboBox() {
        return switch (size.getSelectedIndex()) {
            case 0 -> Size.XXS;
            case 1 -> Size.XS;
            case 2 -> Size.S;
            case 3 -> Size.M;
            case 4 -> Size.L;
            case 5 -> Size.XL;
            case 6 -> Size.XXL;
            default -> null;
        };
    }

    private Color getColorComboBox() {
        return switch (color.getSelectedIndex()) {
            case 0 -> Color.SVART;
            case 1 -> Color.VIT;
            case 2 -> Color.GUL;
            case 3 -> Color.ORANGE;
            case 4 -> Color.RÖD;
            case 5 -> Color.LILA;
            case 6 -> Color.BLÅ;
            case 7 -> Color.GRÖN;
            case 8 -> Color.ROSA;
            default -> null;
        };
    }
}
