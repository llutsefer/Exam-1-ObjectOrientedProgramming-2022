import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        ///// 1 test////
        NonFoodProduct petrol =  NonFoodProduct.fromCsv(Paths.get("data/nonfood/benzyna.csv"));
        System.out.println(petrol.getPrice(2010, 4));
        System.out.println(petrol.getPrice(2022, 4));
        System.out.println(petrol.getPrice(2009, 4));
        System.out.println(petrol.getPrice(2010, 32));
        System.out.println(petrol.getPrice(2010, 2));
        ///// 2 test////
        FoodProduct buraki = FoodProduct.fromCsv(Paths.get("data/food/mleko.csv"));
        System.out.println(buraki.getPrice(2010, 1));
        System.out.println(buraki.getPrice(2010, 1, "MAŁOPOLSKIE"));
        System.out.println(buraki.getPrice(2022, 5, "MAŁOPOLSKIE"));
        System.out.println(buraki.getPrice(2022, 5));
        System.out.println(buraki.getPrice(2009, 3));
        ///// 3 test////
        Product.addProducts(FoodProduct::fromCsv, Path.of("data/food"));
        System.out.println(Product.productsArray.get(0).getPrice(2020, 1));
    }
}