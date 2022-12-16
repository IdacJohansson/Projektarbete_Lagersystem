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

        addArticle.addActionListener(e -> {
            if (garment != null && getColorComboBox() != null && getSizeComboBox() != null &&
                    getGarmentComboBox() != null && !articleNrField.getText().isBlank() && !balanceField.getText().isBlank()) {
                if (articleNrField.getText().trim().chars().allMatch(Character::isDigit) && articleNrField.getText().trim().length() == 7) {
                    if (database.getArticle(articleNrField.getText().trim()) == null) {
                        if (balanceField.getText().chars().allMatch(Character::isDigit)) {
                            if (Integer.parseInt(balanceField.getText()) >= 0) {
                                try {
                                    database.createArticle(new Article(articleNrField.getText(), getGarmentComboBox(),
                                            getColorComboBox(), getSizeComboBox(), Integer.parseInt(balanceField.getText())));
                                    JOptionPane.showMessageDialog(null, "Article was added");
                                } catch (IllegalArgumentException ex) {
                                    JOptionPane.showMessageDialog(null, ex);
                                }
                                dispose();
                                parentWindow.setEnabled(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "Balance can't be negative");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Balance must be a number");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Article with that number already exists");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Article number must be 7 digits without space.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "All fields have to be filled.");
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentWindow.setEnabled(true);
            }
        });
    }

    private Garment getGarmentComboBox() {
        return switch (color.getSelectedIndex()) {
            case 1 -> Garment.TRÖJA;
            case 2 -> Garment.T_SHIRT;
            case 3 -> Garment.BYXA;
            case 4 -> Garment.KJOL;
            case 5 -> Garment.KLÄNNING;
            default -> null;
        };
    }

    private Size getSizeComboBox() {
        return switch (size.getSelectedIndex()) {
            case 1 -> Size.XXS;
            case 2 -> Size.XS;
            case 3 -> Size.S;
            case 4 -> Size.M;
            case 5 -> Size.L;
            case 6 -> Size.XL;
            case 7 -> Size.XXL;
            default -> null;
        };
    }

    private Color getColorComboBox() {
        return switch (color.getSelectedIndex()) {
            case 1 -> Color.SVART;
            case 2 -> Color.VIT;
            case 3 -> Color.GUL;
            case 4 -> Color.ORANGE;
            case 5 -> Color.RÖD;
            case 6 -> Color.LILA;
            case 7 -> Color.BLÅ;
            case 8 -> Color.GRÖN;
            case 9 -> Color.ROSA;
            default -> null;
        };
    }
}
