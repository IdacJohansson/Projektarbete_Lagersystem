package Controller;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MockServer extends JFrame {

    private final AccessLevel accessLevel;
    public final Database database;
    private final Listener listener;
    private JFrame orderHandler;
    private JPanel mainPanel;
    private JButton putOrder;
    private JTextField searchInput;
    private JList textField;
    private JComboBox dropDownMenu;
    private JButton addArticle;
    private JButton subtractArticle;
    private JButton showAllButton;
    private JButton searchButton;
    private String searchWord;

    public MockServer(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        switch (accessLevel) {
            case BUTIK -> butikAccess();
            case LAGER -> lagerAccess();
        }

        listener = new Listener(this);
        database = new Database();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        pack();
        addArticle.addMouseListener(listener);
        subtractArticle.addMouseListener(listener);
        showAllButton.addMouseListener(listener);
        addArticle.addMouseListener(listener);
        subtractArticle.addMouseListener(listener);
        putOrder.addMouseListener(listener);
        searchInput.addFocusListener(listener);
        dropDownMenu.addActionListener(e -> {
            int category = dropDownMenu.getSelectedIndex();
            switch (category) {
                case 1 -> showList(database.getCategory(Garment.TRÖJA));
                case 2 -> showList(database.getCategory(Garment.BYXA));
                case 3 -> showList(database.getCategory(Garment.T_SHIRT));
                case 4 -> showList(database.getCategory(Garment.KJOL));
                case 5 -> showList(database.getCategory(Garment.KLÄNNING));
            }
        });
        putOrder.addActionListener(e -> {
            orderHandler = new OrderHandler(this, database, this.accessLevel,category);
            setEnabled(false);
        });
        showAllButton.addActionListener(e -> {
            showAll(database.getListOfArtNr());
            category = -1;
        });
    }
    private void butikAccess(){
        addArticle.setVisible(false);
        subtractArticle.setVisible(false);
    }
    private void lagerAccess(){
        putOrder.setVisible(false);
        addArticle.setVisible(false);
        subtractArticle.setVisible(false);

        showAllButton.addActionListener(e -> showAll(database.getListOfArtNr()));

        addArticle.addActionListener(e -> {
            new NewArticle(this, database);
            setEnabled(false);
        });

        subtractArticle.addActionListener(e -> {
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
        });

        searchButton.addActionListener(e -> {
            if (checkArticleNrString(searchInput.getText())) {
                List<String> articleAsString = new ArrayList<>();
                articleAsString.add(database.getArticle(searchInput.getText()).toString());
                DefaultListModel<String> listModel = new DefaultListModel<>();
                listModel.addAll(articleAsString);
                textField.setModel(listModel);
            }
        });

        showAll(database.getListOfArtNr());
    }

    protected void showList(List<Article> articleList) {
        showAll(articleList);
        articleList.clear();
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

    private boolean checkArticleNrString(String s){
        if (s.trim().chars().allMatch(Character::isDigit) && s.trim().length() == 7) {
            if (database.getArticle(s.trim()) != null) {
                return true;
            }
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

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
}
