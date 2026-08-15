import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class EmployeeDAO {
    public void addEmployee(Employee employee){
        String sql = """
                INSERT INTO employees
                (id, name, department, designation, salary)
                VALUES (?, ?, ?, ?, ?)
                """;
                try (Connection connection = DBConnection.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1,employee.getId());
                        statement.setString(2,employee.getName());
                        statement.setString(3,employee.getDepartment());
                        statement.setString(4,employee.getDesignation());
                        statement.setDouble(5, employee.getSalary());
                        statement.executeUpdate();
                        System.out.println("Employee added to database successfullly.");
                
                    
                } catch (SQLException e) {
                    System.out.println("Error adding employee to database: " + e.getMessage());
                    e.printStackTrace();
                }
    }
    public List<Employee> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        String sql = """
                SELECT id, name, department, designation, salary FROM employees
                """;
        try(Connection connection = DBConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);
ResultSet resultSet = statement.executeQuery()){
    while(resultSet.next()){
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String department = resultSet.getString("department");
        String designation = resultSet.getString("designation");
        double salary = resultSet.getDouble("salary");
        Employee employee = new Employee(id,name,department,designation,salary);
        employees.add(employee);
    }
} catch(SQLException e){
    System.out.println("Error retrieving employees from database: " + e.getMessage());
    e.printStackTrace();
}
return employees;
    }
    public Employee findEmployeeById(int id){
        String sql = """
                SELECT id,name,department,designation,salary FROM employees WHERE id = ?
                """;
        try(Connection connection = DBConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1,id);
        try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()){
                return new Employee(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("department"), resultSet.getString("designation"), resultSet.getDouble("salary"));


            }
        }
    } catch(SQLException e){
        System.out.println("Error finding employee with ID: " + id +": "+ e.getMessage());
        e.printStackTrace();
    }
    return null;
    }
    public boolean deleteEmployeeById(int id){
        String sql = """
                DELETE FROM employees WHERE id = ?
                """;
        try(Connection connection = DBConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1,id);
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    }
    catch(SQLException e){
        System.out.println("Error deleting employee with ID: "+ id + ": "+ e.getMessage());
        e.printStackTrace();
    }
    return false;
    }
    public boolean employeeIdExists(int id){
        String sql = """
                SELECT id FROM employees WHERE id = ?
                """;
        try(Connection connection = DBConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1,id);
        try(ResultSet resultSet = statement.executeQuery()){
            return resultSet.next();
        }
    } catch(SQLException e){
        System.out.println("Error checking employee ID existence: "+ e.getMessage());
        e.printStackTrace();
    }
    return false;
    }
    public boolean updateEmployee(Employee employee){
        String sql = """
                UPDATE employees SET name = ?, department = ?, designation = ?, salary = ? WHERE id = ?
                """;
                try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1,employee.getName());
                statement.setString(2,employee.getDepartment());
                statement.setString(3,employee.getDesignation());
                statement.setDouble(4, employee.getSalary());
                statement.setInt(5,employee.getId());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch(SQLException e){
        System.out.println("Error updating employee with ID: " + employee.getId() + ": "+ e.getMessage());
        e.printStackTrace();
        return false;
    }
    }

}
