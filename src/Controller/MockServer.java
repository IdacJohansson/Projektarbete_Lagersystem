package Controller;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MockServer extends JFrame {

    private final AccessLevel accessLevel;
    public final Database database;
    private final Listener listener;
    private JPanel mainPanel;
    private JButton putOrder;
    private JTextField searchInput;
    private JList textField;
    private JComboBox dropDownMenu;
    private JButton addArticle;
    private JButton subtractArticle;
    private JButton showAllButton;
    private JButton searchButton;
    private JButton setBalance;
    private JButton lowBalance;

    public MockServer(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        switch (accessLevel) {
            case BUTIK -> butikAccess();
            case LAGER -> lagerAccess();
        }

        listener = new Listener(this);
        database = Database.getDatabas();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        addArticle.addMouseListener(listener);
        subtractArticle.addMouseListener(listener);
        showAllButton.addMouseListener(listener);
        addArticle.addMouseListener(listener);
        subtractArticle.addMouseListener(listener);
        putOrder.addMouseListener(listener);
        searchInput.addFocusListener(listener);
        dropDownMenu.addActionListener(e -> {
            int category = dropDownMenu.getSelectedIndex();
            /*switch (category) {
                case 0 -> showAll(database.getListOfArtNr());
                case 1 -> showList(database.getCategory(Garment.SWEATER));
                case 2 -> showList(database.getCategory(Garment.TROUSER));
                case 3 -> showList(database.getCategory(Garment.T_SHIRT));
                case 4 -> showList(database.getCategory(Garment.SKIRT));
                case 5 -> showList(database.getCategory(Garment.DRESS));
            }

             */
            showSelectedList(category);
        });
        subtractArticle.addActionListener(e -> balanceSubtractor());

        searchButton.addActionListener(e -> searchList());

        setBalance.addActionListener(e -> balanceAdjuster());

        lowBalance.addActionListener(e -> showList(database.getLowBalance()));

        putOrder.addActionListener(e -> {
            new OrderHandler(this, database, this.accessLevel, dropDownMenu.getSelectedIndex());
            setEnabled(false);
        });

        showAllButton.addActionListener(e -> {
            showAll(database.getListOfArtNr());
            dropDownMenu.setSelectedIndex(0);
        });

        addArticle.addActionListener(e -> {
            new NewArticle(database, this);
            setEnabled(false);
        });

        showAll(database.getListOfArtNr());
        pack();
    }

    private void searchList() {
        if (checkArticleNrString(searchInput.getText())) {
            List<String> articleAsString = new ArrayList<>();
            articleAsString.add(database.getArticle(searchInput.getText()).toString());
            DefaultListModel<String> listModel = new DefaultListModel<>();
            listModel.addAll(articleAsString);
            textField.setModel(listModel);
        }
    }

    private void balanceSubtractor() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Enter article number to remove");
            if (input == null) {
                break;
            }
            if (input.trim().chars().allMatch(Character::isDigit) && input.trim().length() == 7) {
                if (database.getArticle(input.trim()) != null) {
                    database.removeArticle(input.trim());
                    JOptionPane.showMessageDialog(null, "Article was removed");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Article with that number was not found");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Article number must be 7 digits without space.");
            }
        }
    }

    private void balanceAdjuster() {
        String articleString = (String) textField.getSelectedValue();
        if (articleString != null) {
            String articleNr = articleString.substring(8, 16);
            while (true) {
                String sum = JOptionPane.showInputDialog(null, "Enter new balance for: " + articleNr);
                if (sum == null) {
                    return;
                }
                sum = sum.trim();
                if (!sum.isBlank() && sum.chars().allMatch(Character::isDigit) && Integer.parseInt(sum) > 0) {
                    database.setBalance(articleNr.trim(), Integer.parseInt(sum));
                    showSelectedList(dropDownMenu.getSelectedIndex());
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "balance need to be a digit and can't be negative");
                }
            }
        }
    }

    private void butikAccess() {
        setBalance.setVisible(false);
        addArticle.setVisible(false);
        subtractArticle.setVisible(false);
        lowBalance.setVisible(false);
    }

    private void lagerAccess() {
        putOrder.setVisible(false);
        addArticle.setVisible(false);
        subtractArticle.setVisible(false);
        lowBalance.setVisible(false);
    }

    protected void showList(List<Article> articleList) {
        showAll(articleList);
        articleList.clear();
    }

    protected void showSelectedList (int category){
        List<Article> articleList= switch (category) {
            case 0 -> (database.getListOfArtNr());
            case 1 -> (database.getCategory(Garment.SWEATER));
            case 2 -> (database.getCategory(Garment.TROUSER));
            case 3 -> (database.getCategory(Garment.T_SHIRT));
            case 4 -> (database.getCategory(Garment.SKIRT));
            case 5 -> (database.getCategory(Garment.DRESS));
            default -> throw new IllegalStateException("Unexpected value: " + category);
        };
        showAll(articleList);
    }

    protected void showAll(List<Article> articleList) {
        List<String> articlesAsString = new ArrayList<>();
        for (Article article : articleList) {
            articlesAsString.add(article.toString());
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(articlesAsString);
        textField.setModel(listModel);
    }

    private boolean checkArticleNrString(String s) {
        if (s.trim().chars().allMatch(Character::isDigit) && s.trim().length() == 7) {
            return database.getArticle(s.trim()) != null;
        }
        return false;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JButton getAddArticle() {
        return addArticle;
    }

    public JButton getRemoveArticle() {
        return subtractArticle;
    }

    public JButton getShowAllButton() {
        return showAllButton;
    }

    public JButton getPutOrder() {
        return putOrder;
    }

    public JTextField getSearchInput() {
        return searchInput;
    }
}
