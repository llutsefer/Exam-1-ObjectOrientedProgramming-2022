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
    public static Product getProducts (String prefix) throws AmbigiousProductException, IndexOutOfBoundsException{
        ArrayList<Product> productsWithPrefix = new ArrayList<>();
        for (Product x:productsArray) {
           if(x.getName().startsWith(prefix)){
               productsWithPrefix.add(x);
           }
        }
        if(productsWithPrefix.size() == 1){
            return productsWithPrefix.get(0);
        }else if(productsWithPrefix.size() > 1){
            ArrayList<String> productsNamesForException = new ArrayList<>();
            for (Product x:productsWithPrefix) {
                productsNamesForException.add(x.getName());
            }
            throw new AmbigiousProductException(productsNamesForException);
        }else{
            throw new IndexOutOfBoundsException();
        }
    }
}
