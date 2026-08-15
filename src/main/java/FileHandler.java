import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
public class FileHandler {
    private static final String FILE_NAME = "employees.txt";
    public static void saveEmployees(ArrayList<Employee> employees){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for(Employee emp: employees){
                writer.write(emp.getId() + "," + emp.getName() + "," + emp.getDepartment() + "," + emp.getDesignation() + "," + emp.getSalary());
                writer.newLine(); 
            }
            writer.close();
            System.out.println("Employees saved successfully to " + FILE_NAME);
        }
        catch(IOException e){
            System.out.println("Error saving employees to file.");
        }
    }
    public static ArrayList<Employee> loadEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        File file = new File(FILE_NAME);
        if(!file.exists()){
            return employees;
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String department = data[2];
                String designation = data[3];
                double salary = Double.parseDouble(data[4]);
                Employee emp = new Employee(id, name, department, designation, salary);
                employees.add(emp);

            }
            reader.close();
        }
        catch(IOException | NumberFormatException e){
            System.out.println("Error loading employees from file.");

        }
        return employees;
    }
}
