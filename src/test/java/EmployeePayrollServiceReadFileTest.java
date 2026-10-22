import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Scanner;


class EmployeePayrollServiceReadFileTest {

    @Test
    void givenThreeEmployeesWhenWrittenToFileShouldMatchEmployeeEntries() throws IOException {
        Scanner input = new Scanner(
                "1\nJeff Bezos\n100000\n" +
                        "2\nBill Gates\n200000\n" +
                        "3\nMark Zuckerberg\n300000\n"
        );

        EmployeePayrollService.readEmployeePayrollData(input);
        EmployeePayrollService.readEmployeePayrollData(input);
        EmployeePayrollService.readEmployeePayrollData(input);


        EmployeePayrollFileIOService.addContentInFile();


        assertEquals(3, EmployeePayrollServiceReadFile.count());
    }
    }