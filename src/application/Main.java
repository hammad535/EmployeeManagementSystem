package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			EmployeeFileHandler.readEmployeesFromFile();
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
		        AnchorPane root = loader.load();
		        primaryStage.setTitle("Employee Management System");
		        primaryStage.setScene(new Scene(root, 610, 675));
		        primaryStage.show();
		        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
