import java.util.List;
public class DatabaseViewTest {
    public static void main(String[] args){
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getAllEmployees();
        if(employees.isEmpty()){
            System.out.println("No employees found in the database.");
        }
        else{
            for(Employee emp: employees){
                emp.displayEmployee();
            }
        }
    }
    
}
