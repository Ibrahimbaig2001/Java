public class DatabaseSearchTest {
    public static void main(String[] args){
        EmployeeDAO dao = new EmployeeDAO();
        Employee employee = dao.findEmployeeById(101);
        if(employee == null){
            System.out.println("Employee not found.");
        }
        else{
            employee.displayEmployee();
        }
    }
    
}
