import java.util.*;


public class EmployeePayrollData {

    int id;
    String name;
    int salary;

    EmployeePayrollData(int id, String name, int salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + salary;
    }
}