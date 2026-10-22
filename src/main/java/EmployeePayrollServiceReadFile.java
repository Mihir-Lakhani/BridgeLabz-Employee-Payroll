import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.io.IOException;

public class EmployeePayrollServiceReadFile {

    public static void main(String[] args) throws IOException {

        try(Stream<String> lines = Files.lines(Paths.get("src/main/java", "payroll_data.txt"))){
            lines.forEach((x) -> System.out.println(x));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static long count() throws IOException{
        try(Stream<String> lines = Files.lines(Paths.get("src/main/java", "payroll_data.txt"))){
            return lines.count();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
}