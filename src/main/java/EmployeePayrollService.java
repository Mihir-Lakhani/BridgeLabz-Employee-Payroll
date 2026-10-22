import java.io.IOException;
import java.util.*;


public class EmployeePayrollService {

    static List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();


    static void readEmployeePayrollData(Scanner consoleInputReader){
        System.out.println("Enter the Id: ");
        int id = consoleInputReader.nextInt();
        consoleInputReader.nextLine();
        System.out.println("Enter the name: ");
        String name = consoleInputReader.nextLine();
        System.out.println("Enter the Salary: ");
        int salary = consoleInputReader.nextInt();
        consoleInputReader.nextLine();
        employeePayrollDataList.add(new EmployeePayrollData(id, name, salary));
    }

    static void writeEmployeePayrollData(){
        System.out.println("Writing employee payroll data to the console :");

        for (EmployeePayrollData employeePayrollData : employeePayrollDataList){
            System.out.println("========================");
            System.out.println(employeePayrollData);
        }
        System.out.println("========================");
    }



    public static void main(String[] args){
        Scanner consoleInputReader = new Scanner(System.in);
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();

        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData();

        consoleInputReader.close();

        try{
            EmployeePayrollFileIOService.addContentInFile();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}