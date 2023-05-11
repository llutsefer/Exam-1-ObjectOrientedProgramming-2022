import java.io.IOException;
        import java.nio.file.Path;
        import java.util.Arrays;
        import java.util.Scanner;

public class NonFoodProduct extends Product {
    Double[] prices;

    private NonFoodProduct(String name, Double[] prices) {
        this.name = name;
        this.prices = prices;
    }

    public static NonFoodProduct fromCsv(Path path) {
        String name;
        Double[] prices;

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine();
            scanner.nextLine();
            prices = Arrays.stream(scanner.nextLine().split(";"))
                    .map(value -> value.replace(",","."))
                    .map(Double::valueOf)
                    .toArray(Double[]::new);

            scanner.close();

            return new NonFoodProduct(name, prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getPrice(int year, int month) {
        int indexOfDate = ((year-2010)*12)+(month-1);
        if(this.prices != null && year > 2009 && month < 13 && month > 0 && indexOfDate < 148){
            return prices[indexOfDate];
        }else{
            throw new IndexOutOfBoundsException();
        }
    }
}
