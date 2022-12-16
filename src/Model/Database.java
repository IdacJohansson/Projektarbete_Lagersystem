package Model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    protected List<Article> allArticles;

    protected List<Article> oneCategory;
    private ArticleMaker articleMaker;


    public Database() {
        allArticles = new ArrayList<>();
        oneCategory = new ArrayList<>();

        articleMaker = new ArticleMaker(allArticles);
        articleMaker.setArticlesList();
    }


    public List<Article> getListOfArtNr() {
        return allArticles;
    }

    public List<Article> getCategory(Garment garment) {
        for (Article a : allArticles) {
            if (a.getGarment().equals(garment)) {
                oneCategory.add(a);
            }
        }
        return oneCategory;
    }


    // create a constructor that calls retrieveList().

    // call storeList() in the end of every method that makes changes to the list or its objects.


    public Article getArticle(String artNr) {
        for (Article a : allArticles) {
            if (a.getArticleNumber().equals(artNr)) {
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





