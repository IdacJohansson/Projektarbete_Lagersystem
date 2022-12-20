package Model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final Database database = new Database();
    private final ObjectFileStore objectFileStore;
    private ArticleMaker articleMaker;
    protected List<Article> articles;
    protected List<Article> selection;


    private Database() {
        retrieveList();
        selection = new ArrayList<>();
        objectFileStore = ObjectFileStore.getObjectFileStore();
    }

    public static Database getDatabas(){
        return database;
    }

    public List<Article> getListOfArtNr() {
        return articles;
    }

    public List<Article> getCategory(Garment garment) {
        selection.clear();
        for (Article a : articles) {
            if (a.getGarment().equals(garment)) {
                selection.add(a);
            }
        }
        return selection;
    }

    public Article getArticle(String artNumb) {
        for (Article a : articles) {
            if (a.getArticleNumber().equals(artNumb)) {
                return a;
            }
        }
        return null;
    }
    public List<Article> getLowBalance() {
        selection.clear();
        for (Article a : articles) {
            if (a.getBalance() < 10) {
                selection.add(a);
            }
        }
        return selection;
    }

    private void storeList() {
        objectFileStore.storeObjectList(articles, "articles");
    }

    @SuppressWarnings("unchecked")
    private void retrieveList() {
        List<Article> articleList = (List<Article>) objectFileStore.retrieveObjectList("articles");
        if (articleList != null) {
            articles = articleList;
        } else {
            articles=new ArrayList<>();
            articleMaker = new ArticleMaker(articles);
            articleMaker.setArticlesList();
            storeList();
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

    public void addBalance(String artNr, int sum) {
        Article article = getArticle(artNr);
        article.addToBalance(sum);
        storeList();
    }

    public void subtractBalance(String artNr, int sum) {
        Article article = getArticle(artNr);
        article.subtractFromBalance(sum);
        storeList();
    }

    public void setBalance(String artNr, int sum) {
        Article article = getArticle(artNr);
        article.setBalance(sum);
        storeList();
    }
}





