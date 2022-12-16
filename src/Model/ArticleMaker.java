package Model;

import java.util.ArrayList;
import java.util.List;

public class ArticleMaker {
    Garment garment;
    Color color;
    Size size;
    List<Article> articlesList;
    String artNr;
    int balance;
    List<String> artNrList;

    public ArticleMaker(List<Article>articleList){
        this.articlesList =articleList;

        setArtNrList();
    }

    public void setArticlesList(){
        for (String artNr:artNrList) {
            balance=100;
            addOneArticleToList(artNr,balance);
        }
    }

    public void addOneArticleToList (String artNr, int balance){
        garment=getGarment(artNr.substring(0,1));
        color=getColor(artNr.substring(1,3));
        size=getSize(artNr.substring(4));
        articlesList.add(new Article(artNr,garment,color,size,balance));
    }
    private void setArtNrList() {
        artNrList = new ArrayList<>();
        String si;
        String sj;
        String sk;
        for (int i = 5; i <= 9; i++) {
            si = String.valueOf(i);
            for (int j = 0; j <= 9; j++) {
                sj = "0" + j;
                for (int k = 4; k <= 16; k += 2) {
                    if (k < 10) {
                        sk = " 0" + k;
                    } else {
                        sk = " " + k;
                    }
                    artNrList.add((si + sj + sk));
                }
            }
        }
    }
    private Garment getGarment(String s) {
        garment = switch (s) {
            case "5" -> Garment.SWEATER;
            case "6" -> Garment.TROUSER;
            case "7" -> Garment.T_SHIRT;
            case "8" -> Garment.SKIRT;
            case "9" -> Garment.DRESS;
        };
        return garment;
    }

    private Color getColor(String s) {
        color = switch (s) {
            case "00" -> Color.BLACK;
            case "01" -> Color.WHITE;
            case "02" -> Color.GRAY;
            case "03" -> Color.PINK;
            case "04" -> Color.RED;
            case "05" -> Color.ORANGE;
            case "06" -> Color.YELLOW;
            case "07" -> Color.GREEN;
            case "08" -> Color.BLUE;
            case "09" -> Color.PURPLE;
        };
        return color;
    }

    private Size getSize(String s) {
        size = switch (s) {
            case "04" -> Size.XXS;
            case "06" -> Size.XS;
            case "08" -> Size.S;
            case "10" -> Size.M;
            case "12" -> Size.L;
            case "14" -> Size.XL;
            case "16" -> Size.XXL;
        };
        return size;
    }
}
