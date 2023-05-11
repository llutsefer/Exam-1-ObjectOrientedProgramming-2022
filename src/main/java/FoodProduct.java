import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FoodProduct extends Product {
    private Map<String, double[]> regionsPrices = new HashMap<>();

    public FoodProduct(String name, Map<String, double[]> regionsPrices) {
        this.name = name;
        this.regionsPrices = regionsPrices;
    }
    public Map<String, double[]> getRegionsPrices() {
        return regionsPrices;
    }
    public static FoodProduct fromCsv(Path path) {
        String name;
        Map<String, double[]> regionsPrices = new HashMap<>();
        String[] prices;
        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                prices = Arrays.stream(scanner.nextLine().split(";"))
                        .map(value -> value.replace(",", "."))
                        .toArray(String[]::new);
                regionsPrices.put(prices[0], Arrays.stream(prices).skip(1).mapToDouble(Double::valueOf).toArray());
            }
            scanner.close();
            return new FoodProduct(name, regionsPrices);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPrice(int year, int month, String province) {
        int indexOfDate = Product.getIndexOfDate(year, month);
        if (this.regionsPrices.isEmpty() == false && year > 2009 && month < 13 && month > 0 && indexOfDate < 148 && this.regionsPrices.containsKey(province)) {
            return this.regionsPrices.get(province)[indexOfDate];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public double getPrice(int year, int month) {
        int indexOfDate = Product.getIndexOfDate(year, month);
        if(this.regionsPrices.isEmpty() == false && year > 2009 && month < 13 && month > 0 && indexOfDate < 148) {
            double result = 0;
            int iterator = 0;
            for (Map.Entry<String, double[]> entry : regionsPrices.entrySet()) {
                double values[] = entry.getValue();
                result+=values[indexOfDate];
                iterator++;
            }
            return result/iterator;
        }else{
            throw new IndexOutOfBoundsException();
        }
    }
}
