package Model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Article> articles;

    // create a constructor that calls retrieveList().

    // call storeList() in the end of every method that makes changes to the list or its objects.


    private List<Article> annasArticles;



    private void setAnnasArticles(){    //OBS!! ska tas bort! bara för att kunna testa mina metoder (Anna)
        Article a1 = new Article("605 14",Garment.BYXA,Color.GRÖN,Size.XL);
        Article a2 = new Article("501 12",Garment.TRÖJA,Color.VIT,Size.XL);
        Article a3 = new Article("901 14",Garment.KLÄNNING,Color.VIT,Size.XL);
        annasArticles.add(a1);
        annasArticles.add(a2);
        annasArticles.add(a3);
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
    public Article getArticle (String artNum){
        for (Article temp:annasArticles) {
            if (temp.getArticleNumber().equals(artNum)){
                return temp;
            }
        }
        return null;
    }
}
