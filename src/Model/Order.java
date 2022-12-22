package Model;

public class Order {
    private Article article;
    private int amount;

    private int orderID;

    public Order(Article article, int amount, int orderID) {
        this.article = article;
        this.amount = amount;
        this.orderID=orderID;
    }



    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {

        if (amount<0){
            return "#" + orderID + ":    " + article.getArticleNumber() + "    " + Math.abs(amount) + "    UTGÅENDE";
        }else {
            return "#" + orderID + ":    " + article.getArticleNumber() + "    " + Math.abs(amount) + "    INKOMMANDE";
        }


    }
}
