package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class InterfaceController {

	@FXML
    private AnchorPane root;

    @FXML
    private Label label;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ListView<Employee> listView;

    @FXML
    private TextField empid;

    @FXML
    private TextField empName;

    @FXML
    private CheckBox fulltime;

    @FXML
    private ComboBox<String> job;

    @FXML
    private Label genderLabel;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton others;
    
    public Employee selectedObject=new Employee();
    public boolean selectionStatus=false;

 // Initialize method to populate listview and job combobox with data
    @FXML
    private void initialize() {
    	listView.getItems().addAll(Employee.employees);
        job.getItems().addAll("Director", "Manager", "Developer", "Tester", "Salesman");
     // Listener to track selected employee in listview
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedObject=newSelection;
                selectionStatus=true;
            } else {
               
            }
        });
        
    }

 // Method to add new employee
    @FXML
    public void addEmployee() {
    	
        String idString = empid.getText();
        String name = empName.getText();
        String jobTitle = (String) job.getValue();
        String gender = "";
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        } else if (others.isSelected()) {
            gender = "Others";
        }
        boolean fullTime = fulltime.isSelected();
        
        // Validate input data
        if (idString.isEmpty() || name.isEmpty() || jobTitle == null || gender.isEmpty()) {
            System.out.println("Please fill in all fields.");
            Alert a = new Alert(AlertType.NONE);
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Please fill all fields!");
    		a.showAndWait();
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idString);
            if (id <= 0) {
                System.out.println("ID must be a positive integer.");
                Alert a = new Alert(AlertType.NONE);
        		a.setAlertType(AlertType.ERROR);
        		a.setContentText("ID must be a positive integer!");
        		a.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ID must be a positive integer.");
            return;
        }
        for (Employee emp : Employee.employees) {
            if (emp.getId() == id) {
                System.out.println("ID already exists.");
                Alert a = new Alert(AlertType.NONE);
        		a.setAlertType(AlertType.ERROR);
        		a.setContentText("Duplicate Id not allowed!");
        		a.showAndWait();
                return;
            }
        }
        
        // Create new employee object and add to list
        Employee newEmployee = new Employee(id, name, jobTitle, gender, fullTime);
        Employee.employees.add(newEmployee);
        try {
			EmployeeFileHandler.writeEmployeesToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        listView.getItems().clear();
        listView.getItems().addAll(Employee.employees);
        
        Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.CONFIRMATION);
		a.setContentText("Employee added!");
		a.show();
        
        // Clear input fields
        empid.clear();
        empName.clear();
        job.getSelectionModel().clearSelection();
        male.setSelected(false);
        female.setSelected(false);
        others.setSelected(false);
        fulltime.setSelected(false);
    }
    
    // reset all fields
    @FXML
    public void reset() {
    	 // Clear input fields
        empid.clear();
        empName.clear();
        job.getSelectionModel().clearSelection();
        male.setSelected(false);
        female.setSelected(false);
        others.setSelected(false);
        fulltime.setSelected(false);
    }
    
    
    //delete employee
    public void deleteEmployee() {
    	
    	if(selectionStatus==true && selectedObject.id!=-1) {
    	
    	for(int i=0;i<Employee.employees.size();i++) {
    		if(Employee.employees.get(i).id == selectedObject.id) {
    			Employee.employees.remove(i);
    			try {
    				EmployeeFileHandler.writeEmployeesToFile();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			selectionStatus=false;
    			 listView.getItems().clear();
    		        listView.getItems().addAll(Employee.employees);
    			 Alert a = new Alert(AlertType.NONE);
    			 a.setAlertType(AlertType.CONFIRMATION);
    			 a.setContentText("Employee removed!");
    			 a.show();
    		}
    	 }
    	}
    }
    	
	
}
