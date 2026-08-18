import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        EmployeeServices service = new EmployeeServices();
        while(true){
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Update Employee by ID");
            System.out.println("5. Delete Employee by ID");
            System.out.println("6. Sort Employees by Name");
            System.out.println("7. Sort Employees by Salary");
            System.out.println("8. Get Employees with highest salary");
            System.out.println("9. Get Average Salary of Employees.");
            System.out.println("10. Sort Employees by ID.");
            System.out.println("11. Get Employees Count by Department.");
            System.out.println("12. Find Employee using Name.");
            System.out.println("13. Show Employees by Department.");
            System.out.println("14. Exit");
            System.out.print("Enter your choice: ");

try {

    int choice = sc.nextInt();

    switch (choice) {

        // your casee
            
                case 1:
                    System.out.println("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Employee Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Employee Department: ");
                    String department = sc.nextLine();
                    System.out.println("Enter Employee Designation: ");
                    String designation = sc.nextLine();
                    System.out.println("Enter Employee Salary: ");
                    double salary = sc.nextDouble();
                    Employee emp = new Employee(id, name, department, designation, salary);
                    try{
                       service.addEmployee(emp);
                    }
                    catch(InvalidEmployeeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    service.viewEmployees();
                    break;
                case 3:
                    System.out.println("Enter Employee ID to search: ");
                    int searchId = sc.nextInt();
                    try{
                        service.searchEmployee(searchId);
                    }
                    catch(EmployeeNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Enter the Employee ID to be updated: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the name to be updated: ");
                    String updateName = sc.nextLine();
                    System.out.println("Enter the department to be updated: ");
                    String updateDepartment = sc.nextLine();
                    System.out.println("Enter the designation to be updated: ");
                    String updateDesignation = sc.nextLine();
                    System.out.println("Enter the salary to be updated: ");
                    double updateSalary = sc.nextDouble();
                    try{
                        service.updateEmployee(updateId, updateName, updateDepartment, updateDesignation, updateSalary);
                    }
                    catch(InvalidEmployeeException e){
                        System.out.println(e.getMessage());
                    }
                    catch(EmployeeNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter the Employee ID to be deleted: ");
                    int deleteId = sc.nextInt();
                    try{
                        service.deleteEmployee(deleteId);
                    }
                    catch(EmployeeNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    service.sortByName();
                    break;
                case 7:
                    service.sortBySalary();
                    break;
                case 8:
                    Employee highestPaid = service.getHighestPaidEmployee();
                    if(highestPaid == null){
                        System.out.println("No employees found, with highest salary.");
                    }
                    else{
                        System.out.println("Highest Paid Employee : ");
                        highestPaid.displayEmployee();
                    }
                    break;
                case 9:
                    double averageSalary = service.getAverageSalary();
                    System.out.println("Average Salary of Employees is: "+ averageSalary);
                    break;
                case 10:
                    service.sortEmployeeById();
                    break;
                case 11:
                        service.countByDepartment();
                        break;
                case 12:
                    sc.nextLine();
                    System.out.println("Enter the name of the emplyee to be searched:");
                    String searchName = sc.nextLine();
                    try{
                        service.searchByName(searchName);
                    }
                    catch(EmployeeNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 13:
                    sc.nextLine();
                    System.out.println("Enter the department of the employee to be searched :");
                    String searchDepartment = sc.nextLine();
                    try{
                        service.showEmployeesByDepartment(searchDepartment);
                    }
                    catch(EmployeeNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 14:
                    System.out.println("Thank you for using the Employee Management System.");
                    sc.close();
                    return ;
                default:
                    System.out.println("Please enter a valid choice.");
            }
            } catch (InputMismatchException e) {

    System.out.println("Please enter a number.");

    sc.nextLine();
}
        }

    }
}
