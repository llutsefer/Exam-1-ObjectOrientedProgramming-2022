import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Function;

public abstract class Product {
    String name;
    static ArrayList<Product> productsArray = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public abstract double getPrice(int year, int month);
    public static void clearProducts(){
        productsArray.clear();
    }
    public static void addProducts(Function<Path, Product> function, Path pathToDirectory){
        final File file = pathToDirectory.toFile();
        if (file.isDirectory()) {
            for (File listFile : file.listFiles()) {
                Product apply = function.apply(listFile.toPath());
                if (apply != null) {
                    productsArray.add(apply);
                }
            }
        }
    }
}
