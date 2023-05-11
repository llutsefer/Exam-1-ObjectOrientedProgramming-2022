import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
        @Test
        void testGetIndexOfDate(){
            assertEquals(0, Product.getIndexOfDate(2010, 1));
        }
        @Test
        void testGetIndexOfDate2(){
            assertEquals(24, Product.getIndexOfDate(2012, 1));
        }
        @Test
        void testGetProductsIndexOutOfBoundsException(){
            Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
                Product.getProducts("test");
            });
            String expectedMessage = "No product with this prefix";
            String actualMessage = exception.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
        @ParameterizedTest
        @CsvSource({
                "2010, 1, 0",
                "2010, 2, 1",
                "2023, 5, 160",
                "2022, 12, 155"
        })
        void parameterizedTestGetIndexOfDate2(int year, int month, int expectedIndex){
            assertEquals(expectedIndex, Product.getIndexOfDate(year, month));
        }
        @ParameterizedTest
        @CsvFileSource(resources = "verificationFile.csv", numLinesToSkip = 0)
        void parameterizedTestGetIndexOfDateFromVerificationFile(int year, int month, int expectedIndex){
            //In order to create a ParameterizedTest with CsvFileSource and avoid getting 'Classpath resource does not exist' error,
            // you need to move the CSV file to the 'src/test/resources' directory
                assertEquals(expectedIndex, Product.getIndexOfDate(year, month));
        }
        @Test
        void testFromCsvFoodProduct(){
            assertEquals("Buraki - za 1 kg", FoodProduct.fromCsv(Path.of("data/food/buraki.csv")).getName());
        }
        @Test
        void testFromCsvFoodProduct2(){
            assertEquals("Cebula - za 1 kg", FoodProduct.fromCsv(Path.of("data/food/cebula.csv")).getName());
        }
        @Test
        void testFromCsvFoodProductWithGetPrice(){
            assertEquals(1.94, FoodProduct.fromCsv(Path.of("data/food/cebula.csv")).getPrice(2010,2, "DOLNOŚLĄSKIE"));
        }
        @Test
        void testFromCsvFoodProductWithGetPrice2(){
            assertEquals(1.51, FoodProduct.fromCsv(Path.of("data/food/buraki.csv")).getPrice(2010,3, "LUBELSKIE"));
        }
        @Test
        void testFromCsvFoodProductWithMap(){
            //I will not perform the task as described in the condition, because it is considered a bad programming practice and should not be done.
            // In order to implement this task, I have written a getter for the 'regionsPrices' map in the 'FoodProduct' class
            FoodProduct testFoodProduct = FoodProduct.fromCsv(Path.of("data/food/buraki.csv"));
            assertEquals("Buraki - za 1 kg", testFoodProduct.getName());
            Map<String, double[]> testRegionsPricesMap = testFoodProduct.getRegionsPrices();
            assertEquals(1.53, testRegionsPricesMap.get("DOLNOŚLĄSKIE")[0]);
            assertEquals(1.45, testRegionsPricesMap.get("KUJAWSKO-POMORSKIE")[0]);
            assertEquals(1.33, testRegionsPricesMap.get("LUBELSKIE")[0]);
            assertEquals(1.42, testRegionsPricesMap.get("LUBUSKIE")[0]);
            assertEquals(1.32, testRegionsPricesMap.get("ŁÓDZKIE")[0]);
            assertEquals(1.36, testRegionsPricesMap.get("MAŁOPOLSKIE")[0]);
            assertEquals(1.51, testRegionsPricesMap.get("MAZOWIECKIE")[0]);
            assertEquals(1.6, testRegionsPricesMap.get("OPOLSKIE")[0]);
            assertEquals(1.33, testRegionsPricesMap.get("PODKARPACKIE")[0]);
            assertEquals(1.35, testRegionsPricesMap.get("PODLASKIE")[0]);
            assertEquals(1.52, testRegionsPricesMap.get("POMORSKIE")[0]);
            assertEquals(1.36, testRegionsPricesMap.get("ŚLĄSKIE")[0]);
            assertEquals(1.53, testRegionsPricesMap.get("ŚWIĘTOKRZYSKIE")[0]);
            assertEquals(1.52, testRegionsPricesMap.get("WARMIŃSKO-MAZURSKIE")[0]);
            assertEquals(1.39, testRegionsPricesMap.get("WIELKOPOLSKIE")[0]);
            assertEquals(1.47, testRegionsPricesMap.get("ZACHODNIOPOMORSKIE")[0]);
        }
}