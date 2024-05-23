package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TeacherController implements Initializable{

    @FXML
    private Button addStudent_btn;

    @FXML
    private Button addStudents_addBtn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_course;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_date;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_gender;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_name;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_semester;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_studentID;

    @FXML
    private TableColumn<DataStudentHandle, String> addStudents_col_year;

    @FXML
    private ComboBox<String> addStudents_course;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private Label addStudents_label_course;

    @FXML
    private Label addStudents_label_fullName;

    @FXML
    private Label addStudents_label_gender;

    @FXML
    private Label addStudents_label_semester;

    @FXML
    private Label addStudents_label_year;

    @FXML
    private Button addStudents_removeBtn;

    @FXML
    private ComboBox<String> addStudents_studentID;

    @FXML
    private TableView<DataStudentHandle> addStudents_tableView;

    @FXML
    private ComboBox<String> addStudents_year;

    @FXML
    private Label current_form;

    @FXML
    private Label greet_name;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button subjectHandle_btn;

    @FXML
    private AnchorPane subjectHandle_form;

    @FXML
    private Button subjecthandle_addBtn;

    @FXML
    private Button subjecthandle_clearBtn;

    @FXML
    private ComboBox<String> subjecthandle_code;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_dateInsert;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_status;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_subjectCode;

    @FXML
    private TableColumn<DataSubjectHandle, String> subjecthandle_col_subjectName;

    @FXML
    private Button subjecthandle_removeBtn;

    @FXML
    private ComboBox<String> subjecthandle_status;

    @FXML
    private ComboBox<String> subjecthandle_subject;

    @FXML
    private TableView<DataSubjectHandle> subjecthandle_tableView;

    @FXML
    private Label teacher_id;
   
    @FXML
    private Button teacher_result_btn;
    
    @FXML
    private Button logout_btn ;
    
    private AlertMessage alert = new AlertMessage();
    
    public void addStudentsAddBtn() {
        if (addStudents_course.getSelectionModel().getSelectedItem().isEmpty()
                || addStudents_year.getSelectionModel().getSelectedItem().isEmpty()
                || addStudents_studentID.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            
            StringBuilder data = new StringBuilder();
            data.append(teacher_id.getText()).append(":");
            data.append(addStudents_studentID.getSelectionModel().getSelectedItem()).append(":");
            data.append(addStudents_label_fullName.getText()).append(":");
            data.append(addStudents_label_gender.getText()).append(":");
            data.append(addStudents_year.getSelectionModel().getSelectedItem()).append(":");
            data.append(addStudents_course.getSelectionModel().getSelectedItem()).append(":");
            data.append(addStudents_label_semester.getText()).append(":");
            data.append(java.time.LocalDate.now()).append(":");
            data.append("Active");

            try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
                writer.println(data.toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            addStudentDisplayData();
            
            alert.successMessage("Added successfully!");
            
            addStudentClearBtn();
        }
   }
   

public void addStudentsRemoveBtn() {
	
    if (addStudents_course.getSelectionModel().getSelectedItem() == null
            || addStudents_year.getSelectionModel().getSelectedItem()== null
            || addStudents_studentID.getSelectionModel().getSelectedItem() == null) {
        alert.errorMessage("Please fill all blank fields");
    } else {
        if (alert.confirmMessage("Are you sure you want to delete Student ID:"
                + addStudents_studentID.getSelectionModel().getSelectedItem())) {
            // Remove data from data.txt
            removeFromDataFile(addStudents_studentID.getSelectionModel().getSelectedItem());

//            addStudentDisplayData();

            alert.successMessage("Deleted successfully!");

            addStudentClearBtn();
        } else {
            alert.errorMessage("Cancelled.");
        }
    }
}

private void removeFromDataFile(String studentID) {
    String filePath = "data.txt";
    String tempFilePath = "temp.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.contains(studentID)) {
                bw.write(line);
                bw.newLine();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Replace the original file with the temporary file
    try {
        java.nio.file.Files.move(java.nio.file.Paths.get(tempFilePath), java.nio.file.Paths.get(filePath), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void addStudentClearBtn() {
    addStudents_course.getSelectionModel().clearSelection();
    addStudents_year.getSelectionModel().clearSelection();
    addStudents_studentID.getSelectionModel().clearSelection();

    addStudents_label_fullName.setText("----------");
    addStudents_label_gender.setText("----------");
    addStudents_label_semester.setText("----------");
    addStudents_label_year.setText("----------");
    addStudents_label_course.setText("----------");
}

 public synchronized void addresult() {
	
	String fxmlPath = "Result.fxml";
    String portalTitle = "Result Details";

    try {
        AnchorPane root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setTitle("University Management System | " + portalTitle);
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void logoutBtn() {
    
    try {
        if (alert.confirmMessage("Are you sure you want to logout?")) {
        	StackPane root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            
            logout_btn.getScene().getWindow().hide();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
}

public void subjectHandleStatusList() {

    List<String> listS = new ArrayList<>();

    for (String data : ListData.status) {
        listS.add(data);
    }

    ObservableList listData = FXCollections.observableArrayList(listS);
    subjecthandle_status.setItems(listData);

}

public void switchForm(ActionEvent event) {

    if (event.getSource() == addStudent_btn) {
        addStudents_form.setVisible(true);
        subjectHandle_form.setVisible(false);

       

        current_form.setText("Add Students form");
    } else if (event.getSource() == subjectHandle_btn) {
        addStudents_form.setVisible(false);
        subjectHandle_form.setVisible(true);

        

        current_form.setText("Subject Handles form");
    }

}

public void initialize(URL location, ResourceBundle resources) {
        
	
        
    }

}