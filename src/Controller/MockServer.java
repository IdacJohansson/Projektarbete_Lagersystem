package Controller;

import Model.AccessLevel;
import Model.Article;
import Model.Database;
import Model.Garment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MockServer extends JFrame {

    private final AccessLevel accessLevel;
    private final Database database;
    private DefaultListModel<String> listModel;
    private List<String> articlesAsString;
    private JFrame orderHandler;

    private void showList(List<Article> articleList) {
        articlesAsString = new ArrayList<>();
        for (Article article : articleList){
            articlesAsString.add(article.toString());
        }
       listModel = new DefaultListModel<>();
       listModel.addAll(articlesAsString);
       textField.setModel(listModel);
       articleList.clear();
    }


    public MockServer(AccessLevel accesLevel) {
        this.accessLevel = accesLevel;
        database = new Database();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
       /* dropDownMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 12/15/2022 Add switch for dropdown alternatives
                int category = dropDownMenu.getSelectedIndex();
                switch (category) {
                    case 0 -> showList(database.getCategory(Garment.TRÖJA));
                    case 1 -> showList(database.getCategory(Garment.BYXA));
                    case 2 -> showList(database.getCategory(Garment.T_SHIRT));
                    case 3 -> showList(database.getCategory(Garment.KJOL));
                    case 4 -> showList(database.getCategory(Garment.KLÄNNING));
                    case 5 -> showList(database.getList());
                }
            }
        });

        */
        putOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderHandler=new OrderHandler(accessLevel);
                dispose();
            }
        });
    }


    private JPanel mainPanel;
    private JButton putOrder;
    private JTextField searchInput;
    private JList textField;
    private JComboBox dropDownMenu;
    private JButton addArticle;
    private JButton removeArticle;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
}
