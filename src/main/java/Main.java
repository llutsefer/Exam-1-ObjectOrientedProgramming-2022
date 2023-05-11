import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        /// 1 test////
        NonFoodProduct petrol =  NonFoodProduct.fromCsv(Paths.get("data/nonfood/benzyna.csv"));
        System.out.println(petrol.getPrice(2010, 4));
        System.out.println(petrol.getPrice(2010, 2));
        ///// 2 test////
        FoodProduct buraki = FoodProduct.fromCsv(Paths.get("data/food/mleko.csv"));
        System.out.println(buraki.getPrice(2010, 1));
        System.out.println(buraki.getPrice(2010, 1, "MAŁOPOLSKIE"));
        ///// 3 test////
        Product.addProducts(FoodProduct::fromCsv, Path.of("data/food"));
        System.out.println(Product.productsArray.get(0).getPrice(2020, 1));
        ///// 4 test////
        try {
            System.out.println(Product.getProducts("Buraki - za 1 kg").getPrice(2020, 1));
        } catch (AmbigiousProductException e) {
            throw new RuntimeException(e);
        }
        ///// 5 test////
        Cart cartTest = new Cart();
        cartTest.addProduct(petrol, 2);
        System.out.println(cartTest.getPrice(2010, 4));
        System.out.println(cartTest.getInflation(2010, 4, 2010, 2));
    }
}