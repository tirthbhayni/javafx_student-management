package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class AddStudentController implements Initializable {
	
	
	  @FXML
	    private AnchorPane main_form;

	    @FXML
	    private Button student_addBtn;

	    @FXML
	    private TextField student_email;

	    @FXML
	    private TextField student_id;

	    @FXML
	    private TextField student_name;

	    @FXML
	    private TextField student_password;

	    @FXML
	    private TextField student_username;
	    
	    public void addBtn(){
	    	
	    	Alert alert;
	    	
	    	String id = student_id.getText();
	        String username = student_username.getText();
	        String email = student_email.getText();
	        String password = student_password.getText();
	        String name = student_name.getText();
	    	
	        StudentData newStudent = new StudentData(id, username, email, password, name);
	        
	        writeToFile(newStudent);
	        
	        alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Data Added Sucessfully.!");
            alert.showAndWait();
	    }
	    
	    private void writeToFile(StudentData student) {
	        String filePath = "data.txt";
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
	            writer.write(student.getStudentId() + ":" + student.getUsername() + ":" + student.getEmail() + ":" + student.getPassword() + ":" + student.getName());
	            writer.newLine(); // Add a new line for the next entry
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	public void initialize(URL url, ResourceBundle rb) {
    	
    }
}