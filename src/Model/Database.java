package Model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    protected List<Article> articles;

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
        Article tXS = new Article("500 06", Garment.SWEATER, Color.BLACK, Size.XS);
        Article tS = new Article("500 08", Garment.SWEATER, Color.BLACK, Size.S);
        Article tM = new Article("500 10", Garment.SWEATER, Color.BLACK, Size.M);
        Article tL = new Article("500 12", Garment.SWEATER, Color.BLACK, Size.L);

        Article bXS = new Article("601 06", Garment.TROUSER, Color.WHITE, Size.XS);
        Article bS = new Article("601 08", Garment.TROUSER, Color.WHITE, Size.S);
        Article bM = new Article("601 10", Garment.TROUSER, Color.WHITE, Size.M);
        Article bL = new Article("601 12", Garment.TROUSER, Color.WHITE, Size.L);

        Article tsXS = new Article("701 06", Garment.T_SHIRT, Color.WHITE, Size.XS);
        Article tsS = new Article("701 08", Garment.T_SHIRT, Color.WHITE, Size.S);
        Article tsM = new Article("701 10", Garment.T_SHIRT, Color.WHITE, Size.M);
        Article tsL = new Article("701 12", Garment.T_SHIRT, Color.WHITE, Size.L);

        articles.add(tXS);
        articles.add(tS);
        articles.add(tM);
        articles.add(tL);

        articles.add(bXS);
        articles.add(bS);
        articles.add(bM);
        articles.add(bL);

        articles.add(tsXS);
        articles.add(tsS);
        articles.add(tsM);
        articles.add(tsL);

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

    /*  private void storeList() {
            ObjectFileStore.storeObjectList(articles, "articles");
        } */

       /* @SuppressWarnings("unchecked")
        private void retrieveList() {
            List<Article> articleList = (List<Article>) ObjectFileStore.retrieveObjectList("articles");
            if (articleList != null) {
                articles = articleList;
            } else {
                articles = new ArrayList<>();
            }
        }*/
}





