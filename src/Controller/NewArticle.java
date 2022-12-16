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

    public NewArticle(Database database, MockServer mockServer) {
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
                                mockServer.showAll(database.getListOfArtNr());
                                mockServer.setEnabled(true);
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
                mockServer.setEnabled(true);
            }
        });
    }

    private Garment getGarmentComboBox() {
        return switch (color.getSelectedIndex()) {
            case 1 -> Garment.SWEATER;
            case 2 -> Garment.T_SHIRT;
            case 3 -> Garment.TROUSER;
            case 4 -> Garment.SKIRT;
            case 5 -> Garment.DRESS;
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
            case 1 -> Color.BLACK;
            case 2 -> Color.WHITE;
            case 3 -> Color.YELLOW;
            case 4 -> Color.ORANGE;
            case 5 -> Color.RED;
            case 6 -> Color.PURPLE;
            case 7 -> Color.BLUE;
            case 8 -> Color.GREEN;
            case 9 -> Color.PINK;
            default -> null;
        };
    }
}
