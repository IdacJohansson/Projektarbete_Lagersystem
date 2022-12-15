package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {

    private List<Article> articles;

    protected List<Article> oneCategory;


    public Database() {
        articles = new ArrayList<>();
        oneCategory = new ArrayList<>();

        setListOfArtNr();
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


    // create a constructor that calls retrieveList().

    // call storeList() in the end of every method that makes changes to the list or its objects.


    public void setListOfArtNr() {
        Article tXS = new Article("500 06", Garment.TRÖJA, Color.SVART, Size.XS);
        Article tS = new Article("500 08", Garment.TRÖJA, Color.SVART, Size.S);
        Article tM = new Article("500 10", Garment.TRÖJA, Color.SVART, Size.M);
        Article tL = new Article("500 12", Garment.TRÖJA, Color.SVART, Size.L);

        Article bXS = new Article("500 06", Garment.BYXA, Color.VIT, Size.XS);
        Article bS = new Article("500 08", Garment.BYXA, Color.VIT, Size.S);
        Article bM = new Article("500 10", Garment.BYXA, Color.VIT, Size.M);
        Article bL = new Article("500 12", Garment.BYXA, Color.VIT, Size.L);

        articles.add(tXS);
        articles.add(tS);
        articles.add(tM);
        articles.add(tL);

        articles.add(bXS);
        articles.add(bS);
        articles.add(bM);
        articles.add(bL);

        //Collections.sort();
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
}




