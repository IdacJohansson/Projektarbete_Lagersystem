package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    protected List<Article> articles;
    protected List<Article> oneCategory;


    public Database() {
        retrieveList();
        oneCategory = new ArrayList<>();
    }


    public List<Article> getListOfArtNr() {
        return articles;
    }

    public List<Article> getCategory(Garment garment) {
        for (Article a : articles) {
            if (a.getGarment().equals(garment)) {
                oneCategory.add(a);
            }
        }
        return oneCategory;
    }

    public Article getArticle(String artNumb) {
        for (Article a : articles) {
            if (a.getArticleNumber().equals(artNumb)) {
                return a;
            }
        }
        return null;
    }

    private void storeList() {
        ObjectFileStore.storeObjectList(articles, "articles");
    }

    @SuppressWarnings("unchecked")
    private void retrieveList() {
        List<Article> articleList = (List<Article>) ObjectFileStore.retrieveObjectList("articles");
        if (articleList != null) {
            articles = articleList;
        } else {
            articles = new ArrayList<>();
        }
    }

    public void createArticle(Article newArticle) {
        articles.add(newArticle);
        storeList();
    }

    public void removeArticle(String articleNr) {
        articles.remove(getArticle(articleNr));
        storeList();
    }
}





