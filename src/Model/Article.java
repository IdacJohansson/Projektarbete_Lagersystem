package Model;

public class Article {

    private final String articleNumber;
    private final Garment garment;
    private final Color color;
    private final Size size;
    private int balance;

    public Article(String articleNumber, Garment garment, Color color, Size size, int balance){
        if (articleNumber == null || articleNumber.isBlank()) {
            throw new IllegalArgumentException("article number is not valid");
        }

        String cleanString = articleNumber.replaceAll("\\s+","");
        if (!cleanString.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("article number must only contain numbers");
        } else if (cleanString.length() != 7) {
            throw new IllegalArgumentException("article number is not 7 numbers");
        }

        if (balance < 0){
            throw new IllegalArgumentException("balance can't be negative");
        }

        this.articleNumber = cleanString;
        this.garment = garment;
        this.color = color;
        this.size = size;
        this.balance = balance;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public Garment getGarment() {
        return garment;
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if (balance < 0){
            throw new IllegalArgumentException("balance can't be negative");
        }
        this.balance = balance;
    }

    public void addToBalance(int number) {
        if (number < 0){
            throw new IllegalArgumentException("you can't add a negative number to balance");
        }
        balance = balance + number;
    }

    public void subtractFromBalance(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("your withdraw can't be negative");
        } else if (balance - number < 0){
            throw new IllegalArgumentException("your withdraw is larger then the balance");
        }
        balance = balance - number;
    }
}
