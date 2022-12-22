package Controller;

import Model.Article;
import Model.Database;
import Model.Order;

import java.util.ArrayList;
import java.util.List;

public class TaskController {
    public final Database database;
    protected List<Order> todoList;


    Article article;
    Order order;

    public TaskController(){
        database=Database.getDatabase();
        todoList=new ArrayList<>();

        setUpTodoList();
    }

    public List<Order> getTodoList() {
        return todoList;
    }

    public void setUpTodoList() {
        int amount;
        for (int i = 0; i < 10; i++) {
            int rand = (int)(Math.random()*database.getAllArticles().size());
            if (i%3==0){
                amount=(int)(Math.random()*50);
            }else {
                amount=(int)(Math.random()*-30);
            }

            addToList(database.getAllArticles().get(rand).getArticleNumber(),amount);
        }
    }
    public void addToList(String artNr, int amount){//amount är neg om från store
        article=database.getArticle(artNr);
        int id = todoList.size()+1;
        todoList.add(new Order(article,amount,id));
    }

    public void markComplete(int id){
        for (Order order:todoList) {
            if (order.getOrderID()==id){
                todoList.remove(order);
                updateBalance(order);
            }
        }

    }

    public void updateBalance(Order order){
        if(order.getAmount()<0){
            database.subtractBalance(order.getArticle().getArticleNumber(),Math.abs(order.getAmount()));
        }else {
            database.addBalance(order.getArticle().getArticleNumber(),order.getAmount());
        }
    }
}
