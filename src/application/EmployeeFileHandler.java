package application;

import java.io.*;
import java.util.*;

public class EmployeeFileHandler {
    
    private static final String FILE_PATH = "data.csv";
    private static final String DELIMITER = ",";
    
    public static List<Employee> readEmployeesFromFile() throws IOException {
        List<Employee> employees = new ArrayList<>();
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(DELIMITER);
            
            if(fields.length>1) {
            int id = Integer.parseInt(fields[0]);
            String name = fields[1];
            String job = fields[2];
            String gender = fields[3];
            boolean fullTime = Boolean.parseBoolean(fields[4]);
            
            Employee employee = new Employee(id, name, job, gender, fullTime);
            Employee.employees.add(employee);
            }
        }
        reader.close();
        
        return employees;
    }
    
    public static void writeEmployeesToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Employee employee : Employee.employees) {
            String line = employee.getId() + DELIMITER +
                          employee.getName() + DELIMITER +
                          employee.getJob() + DELIMITER +
                          employee.getGender() + DELIMITER +
                          employee.isFullTime();
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}

