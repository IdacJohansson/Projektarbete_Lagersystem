package Controller;

import Model.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MockServer extends JFrame {

    private final AccessLevel accessLevel;
    public final Database database;
    private DefaultListModel<String> listModel;
    private List<String> articlesAsString;
    private MouseListen mouseListen;
    private JFrame orderHandler;

    private void showList(List<Article> articleList) {
        showAll(articleList);
        articleList.clear();
    }
    private void showAll(List<Article> articleList){
        articlesAsString = new ArrayList<>();
        for (Article article : articleList) {
            articlesAsString.add(article.toString());
        }
        listModel = new DefaultListModel<>();
        listModel.addAll(articlesAsString);
        textField.setModel(listModel);
    }

    public MockServer(AccessLevel accesLevel) {
        this.accessLevel = accesLevel;
        mouseListen = new MouseListen(this);
        database = new Database();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        addArticle.addMouseListener(mouseListen);
        removeArticle.addMouseListener(mouseListen);
        showAllButton.addMouseListener(mouseListen);
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
            orderHandler = new OrderHandler(this,database,accessLevel);
            setEnabled(false);
        });
        showAllButton.addActionListener(e -> {
            showAll(database.getListOfArtNr());
        });
    }

    public JButton getAddArticle() {
        return addArticle;
    }

    public JButton getRemoveArticle() {
        return removeArticle;
    }

    public JButton getShowAllButton() {
        return showAllButton;
    }

    private JPanel mainPanel;
    private JButton putOrder;
    private JTextField searchInput;
    private JList textField;
    private JComboBox dropDownMenu;
    private JButton addArticle;
    private JButton removeArticle;
    private JButton showAllButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
}
