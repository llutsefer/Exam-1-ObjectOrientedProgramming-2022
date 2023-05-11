import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class VerificationFile {
    public static void createVerificationFile(Path pathToDirectory) throws IOException {
        FileWriter fileWriter = new FileWriter(pathToDirectory.toString()+ "/verificationFile.csv");
        int year = 2010, month = 1, index = 0;
        while (year<2022){
            fileWriter.write(year + "," + month + "," + index + "\n");
            index++;
            month++;
            if(month == 13){
                month = 1;
                year++;
            }
        }
        fileWriter.write(2022 + "," + 1 + "," + index + "\n");
        index++;
        fileWriter.write(2022 + "," + 2 + "," + index + "\n");
        index++;
        fileWriter.write(2022 + "," + 3 + "," + index + "\n");
        fileWriter.close();
        System.out.println("Verification file created");
    }
}
