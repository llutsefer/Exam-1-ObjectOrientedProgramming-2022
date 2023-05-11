import java.util.ArrayList;
import javafx.util.Pair;

public class Cart {
    ArrayList<Pair<Product, Integer>> productList = new ArrayList<>();
    public void addProduct(Product product, int quantity){
        productList.add(new Pair<>(product, quantity));
    }
    public double getPrice(int year, int month){
        double price = 0;
        for (Pair<Product, Integer> pair : productList) {
            price += pair.getKey().getPrice(year, month) * pair.getValue();
        }
        return price;
    }
    public double getInflation(int year1, int month1, int year2, int month2){
        double firstDateCourtPrice = getPrice(year1, month1);
        double secondDateCourtPrice = getPrice(year2, month2);
        int monthsBetweenDates = (year2 - year1) * 12 + month2 - month1;
        return (secondDateCourtPrice-firstDateCourtPrice)/firstDateCourtPrice*100/monthsBetweenDates*12;
    }
}
