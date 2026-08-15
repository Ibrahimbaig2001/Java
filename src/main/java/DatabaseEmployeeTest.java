public class DatabaseEmployeeTest {
    public static void main(String[] args){
        Employee employee = new Employee(101,"Ibrahim Baig","IT","Software Engineer",75000.0);
        EmployeeDAO dao = new EmployeeDAO();

        dao.addEmployee(employee);

    }
    
}
