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
    private JButton createNewArticle;
    private JButton deleteArticle;
    private String searchWord;

    public MockServer(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        switch (accessLevel) {
            case BUTIK -> {
                addArticle.setVisible(false);
                subtractArticle.setVisible(false);
                createNewArticle.setVisible(false);
                deleteArticle.setVisible(false);
            }
            case LAGER -> {
                putOrder.setVisible(false);
                createNewArticle.setVisible(false);
                deleteArticle.setVisible(false);
            }
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
        createNewArticle.addMouseListener(listener);
        deleteArticle.addMouseListener(listener);
        putOrder.addMouseListener(listener);
        searchInput.addFocusListener(listener);
        dropDownMenu.addActionListener(e -> {
            int category = dropDownMenu.getSelectedIndex();
            switch (category) {
                case 0 -> showList(database.getCategory(Garment.TRÖJA));
                case 1 -> showList(database.getCategory(Garment.BYXA));
                case 2 -> showList(database.getCategory(Garment.T_SHIRT));
                case 3 -> showList(database.getCategory(Garment.KJOL));
                case 4 -> showList(database.getCategory(Garment.KLÄNNING));
            }
        });
        putOrder.addActionListener(e -> {
            orderHandler = new OrderHandler(this, database, this.accessLevel);
            setEnabled(false);
        });
        showAllButton.addActionListener(e -> showAll(database.getListOfArtNr()));
    }


    private void showList(List<Article> articleList) {
        showAll(articleList);
        articleList.clear();
    }

    private void showAll(List<Article> articleList) {
        List<String> articlesAsString = new ArrayList<>();
        for (Article article : articleList) {
            articlesAsString.add(article.toString());
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(articlesAsString);
        textField.setModel(listModel);
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

    public JButton getCreateNewArticle() {
        return createNewArticle;
    }

    public JButton getDeleteArticle() {
        return deleteArticle;
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
