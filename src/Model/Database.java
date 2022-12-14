package Model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Article> articles;

    // create a constructor that calls retrieveList().

    // call storeList() in the end of every method that makes changes to the list or its objects.






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
