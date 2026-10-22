import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class EmployeePayrollFileIOService {


    public static final String PAYROLL_FILE_NAME = "payroll_data.txt";
    public static final String PAYROLL_FOLDER_NAME = "src/main/java";

    public static void addContentInFile()
        throws IOException{

        Path folder = Paths.get(PAYROLL_FOLDER_NAME);
        Path file = folder.resolve(PAYROLL_FILE_NAME);

        if (Files.notExists(folder)){
            Files.createDirectories(folder);
            Files.createFile(file);
        }


        StringBuffer buffer = new StringBuffer("");

        EmployeePayrollService.employeePayrollDataList.forEach(
                (employee) -> {
                    buffer.append(employee.toString());
                    buffer.append("\n");
                }
        );

        Files.write(file, buffer.toString().getBytes(StandardCharsets.UTF_8));

    }


}