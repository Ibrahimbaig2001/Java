
import java.util.Map;
import java.util.List;

public class EmployeeServices {

    private EmployeeDAO employeeDAO;

    public EmployeeServices() {
        employeeDAO = new EmployeeDAO();

    }

    // Check whether employee ID already exists
    public boolean employeeIdExists(int id) {
        return employeeDAO.employeeIdExists(id);
    }

    // Add Employee
    public void addEmployee(Employee employee)
            throws InvalidEmployeeException {

        if (employee.getId() <= 0) {
            throw new InvalidEmployeeException(
                    "Employee ID must be greater than 0."
            );
        }

        if (employeeIdExists(employee.getId())) {
            throw new InvalidEmployeeException(
                    "Employee ID already exists."
            );
        }

        if (employee.getName().isEmpty()) {
            throw new InvalidEmployeeException(
                    "Employee name cannot be empty."
            );
        }

        if (employee.getDepartment().isEmpty()) {
            throw new InvalidEmployeeException(
                    "Employee department cannot be empty."
            );
        }

        if (employee.getDesignation().isEmpty()) {
            throw new InvalidEmployeeException(
                    "Employee designation cannot be empty."
            );
        }

        if (employee.getSalary() <= 0) {
            throw new InvalidEmployeeException(
                    "Employee salary must be greater than 0."
            );
        }

        // Now adding to MySQL instead of ArrayList
        employeeDAO.addEmployee(employee);
    }

    // View Employees
    public void viewEmployees() {
        List<Employee> employeesFromDB = employeeDAO.getAllEmployees();
        if(employeesFromDB.isEmpty()){
            System.out.println("No employees found in the databse.");
            return;
        }
        for(Employee emp: employeesFromDB){
            emp.displayEmployee();
            System.out.println("-------------------");
        }


        // if (employees.isEmpty()) {
        //     System.out.println(
        //             "No Employees found, please add employees first."
        //     );
        //     return;
        // }

        // for (Employee emp : employees) {

        //     emp.displayEmployee();

        //     System.out.println("-------------------");
        // }
    }

    // Search Employee by ID
    public void searchEmployee(int id) throws EmployeeNotFoundException{
        Employee employee = employeeDAO.findEmployeeById(id);
        if(employee == null){
            throw new EmployeeNotFoundException("Employee with ID "+ id + "not found.");
        }
        System.out.println("Employee found:");
        employee.displayEmployee();
    }
        //     throws EmployeeNotFoundException {

        // for (Employee emp : employees) {

        //     if (emp.getId() == id) {

        //         System.out.println("Employee found:");

        //         emp.displayEmployee();

        //         return;
        //     }
        // }

        // throw new EmployeeNotFoundException(
        //         "Employee with ID " + id + " not found."
        // );
    

    // Update Employee
    public void updateEmployee(
            int id,
            String name,
            String department,
            String designation,
            double salary)
            throws EmployeeNotFoundException,
            InvalidEmployeeException {
                if(name.isEmpty()){
                    throw new InvalidEmployeeException("Employee name cannot be empty.");
                }
                if(department.isEmpty()){
                    throw new InvalidEmployeeException("Employee department cannot be empty.");
                }
                if(designation.isEmpty()){
                    throw new InvalidEmployeeException("Employee designation cannot be empty.");
                }
                if(salary<=0){
                    throw new InvalidEmployeeException("Employee salary must be greater than 0.");
                }
                Employee employee = new Employee(id, name, department, designation,salary);
                boolean updated = employeeDAO.updateEmployee(employee);
                if(!updated){
                    throw new EmployeeNotFoundException("Employee with ID " + id +"not found.");
                }
                System.out.println("Employee details updated successfully.");



        // for (Employee emp : employees) {

        //     if (emp.getId() == id) {

        //         if (name.isEmpty()) {
        //             throw new InvalidEmployeeException(
        //                     "Employee name cannot be empty."
        //             );
        //         }

        //         if (department.isEmpty()) {
        //             throw new InvalidEmployeeException(
        //                     "Employee department cannot be empty."
        //             );
        //         }

        //         if (designation.isEmpty()) {
        //             throw new InvalidEmployeeException(
        //                     "Employee designation cannot be empty."
        //             );
        //         }

        //         if (salary <= 0) {
        //             throw new InvalidEmployeeException(
        //                     "Employee salary must be greater than 0."
        //             );
        //         }

        //         emp.setName(name);
        //         emp.setDepartment(department);
        //         emp.setDesignation(designation);
        //         emp.setSalary(salary);

        //         System.out.println(
        //                 "Employee details updated successfully."
        //         );

        //         return;
        //     }
        // }

        // throw new EmployeeNotFoundException(
        //         "Employee with ID " + id + " does not exist."
        // );
    }

    // Delete Employee
    public void deleteEmployee(int id)
            throws EmployeeNotFoundException {
                boolean deleted = employeeDAO.deleteEmployeeById(id);
                if(!deleted){
                    throw new EmployeeNotFoundException("Employee with ID " + id + "not found.");
                }
                System.out.println("Employee deleted successfully.");

        // Iterator<Employee> iterator = employees.iterator();

        // while (iterator.hasNext()) {

        //     Employee emp = iterator.next();

        //     if (emp.getId() == id) {

        //         iterator.remove();

        //         System.out.println(
        //                 "Employee deleted successfully."
        //         );

        //         return;
        //     }
        // }

        // throw new EmployeeNotFoundException(
        //         "Employee with ID " + id + " does not exist."
        // );
    }

    // Get Employees
    public List<Employee> getEmployees() {
        return employeeDAO.getAllEmployees();
    }

    // Sort by Name
    public void sortByName() {
        List<Employee> employeesFromDB = employeeDAO.getEmployeesSortedByName();
        if(employeesFromDB.isEmpty()){
            System.out.println("No employees found in the databse.");
            return;
        }
        for(Employee emp: employeesFromDB){
            emp.displayEmployee();
            System.out.println("-------------------");
        }


        // Collections.sort(
        //         employees,
        //         (e1, e2) ->
        //                 e1.getName()
        //                         .compareToIgnoreCase(e2.getName())
        // );

        // System.out.println(
        //         "Employees sorted by name successfully."
        // );
    }

    // Sort by Salary
    public void sortBySalary() {
        List<Employee> employeesFromDB = employeeDAO.getEmployeesSortedBySalary();
        if(employeesFromDB.isEmpty()){
            System.out.println("No employees found in the database.");
            return;
        }
        for(Employee emp: employeesFromDB){
            emp.displayEmployee();
            System.out.println("-------------------");
        }

        // Collections.sort(
        //         employees,
        //         (e1, e2) ->
        //                 Double.compare(
        //                         e1.getSalary(),
        //                         e2.getSalary()
        //                 )
        // );

        // System.out.println(
        //         "Employees sorted by salary successfully."
        // );
    }

    // Highest Paid Employee
    public Employee getHighestPaidEmployee() {
        return employeeDAO.getHighestPaidEmployee();

        // if (employees.isEmpty()) {

        //     System.out.println(
        //             "No Employees found, please add employees first."
        //     );

        //     return null;
        // }

        // Employee highestPaid = employees.get(0);

        // for (Employee emp : employees) {

        //     if (emp.getSalary() > highestPaid.getSalary()) {

        //         highestPaid = emp;
        //     }
        // }

        // return highestPaid;
    }

    // Average Salary
    public double getAverageSalary() {
        return employeeDAO.getAverageSalary();

        // if (employees.isEmpty()) {
        //     return 0;
        // }

        // double total = 0;

        // for (Employee emp : employees) {

        //     total += emp.getSalary();
        // }

        // return total / employees.size();
    }

    // Sort by ID
    public void sortEmployeeById() {
        List<Employee> employeesFromDB = employeeDAO.getEmployeesSortedById();
        if(employeesFromDB.isEmpty()){
            System.out.println("No employees found in the database.");
            return;
        }
        for(Employee emp: employeesFromDB){
            emp.displayEmployee();
            System.out.println("-------------------");
        }

        // if (employees.isEmpty()) {

        //     System.out.println(
        //             "No Employees found, please add employees first."
        //     );

        //     return;
        // }

        // Collections.sort(
        //         employees,
        //         (e1, e2) ->
        //                 Integer.compare(
        //                         e1.getId(),
        //                         e2.getId()
        //                 )
        // );

        // System.out.println(
        //         "Employees sorted by ID successfully."
        // );
    }

    // Count Employees by Department
    public void countByDepartment() {
        Map<String, Integer> departmentCount = employeeDAO.countEmployeesByDepartment();
        if(departmentCount.isEmpty()){
            System.out.println("No employees found in the database.");
            return;
        }
        for(Map.Entry<String,Integer> entry: departmentCount.entrySet()){
            System.out.println("Department: "+ entry.getKey() + ", Count: "+ entry.getValue());
        }

        // HashMap<String, Integer> departmentCount =
        //         new HashMap<>();

        // for (Employee emp : employees) {

        //     String department = emp.getDepartment();

        //     if (departmentCount.containsKey(department)) {

        //         departmentCount.put(
        //                 department,
        //                 departmentCount.get(department) + 1
        //         );

        //     } else {

        //         departmentCount.put(
        //                 department,
        //                 1
        //         );
        //     }
        // }

        // for (Map.Entry<String, Integer> entry :
        //         departmentCount.entrySet()) {

        //     System.out.println(
        //             "Department: "
        //                     + entry.getKey()
        //                     + ", Count: "
        //                     + entry.getValue()
        //     );
        // }
    }

    // Search by Name
    public void searchByName(String name)
            throws EmployeeNotFoundException {
        List<Employee> employeesFromDB = employeeDAO.findEmployeesByName(name);
        if(employeesFromDB.isEmpty()){
            throw new EmployeeNotFoundException("No employees found with name: " + name);
        }
        for(Employee emp: employeesFromDB){
            emp.displayEmployee();
            System.out.println("-------------------");
        }

        // boolean found = false;

        // for (Employee emp : employees) {

        //     if (emp.getName().equalsIgnoreCase(name)) {

        //         System.out.println("Employee found");

        //         emp.displayEmployee();

        //         found = true;
        //     }
        // }

        // if (!found) {

        //     throw new EmployeeNotFoundException(
        //             "Employee with name "
        //                     + name
        //                     + " not found."
        //     );
        // }
    }

    // Show Employees by Department
    public void showEmployeesByDepartment(String department)
            throws EmployeeNotFoundException {
            List<Employee> employeesFromDB = employeeDAO.findEmployeesByDepartment(department);
            if(employeesFromDB.isEmpty()){
                throw new EmployeeNotFoundException("No employees found in department: " + department);
            }
            System.out.println("Employees in department: " + department);
            for(Employee emp: employeesFromDB){
                emp.displayEmployee();
                System.out.println("-------------------");
            }

        // List<Employee> result =
        //         employees.stream()
        //                 .filter(employee ->
        //                         employee.getDepartment()
        //                                 .equalsIgnoreCase(department))
        //                 .collect(Collectors.toList());

        // if (result.isEmpty()) {

        //     throw new EmployeeNotFoundException(
        //             "No employees found in department "
        //                     + department
        //     );
        // }

        // for (Employee emp : result) {

        //     emp.displayEmployee();
        // }
    }

    // Highest Paid Employee using Stream
    // public Employee getHighestPaidEmployeeUsingStream()
    //         throws EmployeeNotFoundException {

    //     return employees.stream()
    //             .max(
    //                     Comparator.comparingDouble(
    //                             Employee::getSalary
    //                     )
    //             )
    //             .orElseThrow(() ->
    //                     new EmployeeNotFoundException(
    //                             "No Employees Found."
    //                     )
    //             );
    // }

    // // Average Salary using Stream
    // public double getAverageSalaryUsingStream()
    //         throws EmployeeNotFoundException {

    //     if (employees.isEmpty()) {

    //         throw new EmployeeNotFoundException(
    //                 "No Employees Found."
    //         );
    //     }

    //     return employees.stream()
    //             .mapToDouble(Employee::getSalary)
    //             .average()
    //             .orElse(0);
    // }
}