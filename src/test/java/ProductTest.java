import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
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
            assertEquals(expectedIndex, Product.getIndexOfDate(year, month));
        }
        //In order to create a ParameterizedTest with CsvFileSource and avoid getting 'Classpath resource does not exist' error,
        // you need to move the CSV file to the 'src/test/resources' directory
}