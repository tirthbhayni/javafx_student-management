package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class threadex implements Initializable {
     @FXML
    private Label coag;

    @FXML
    private Label coamark;

    @FXML
    private Label dmg;

    @FXML
    private Label dmmark;

    @FXML
    private Label oopg;
    
    @FXML
    private Label osg;

    @FXML
    private Label oopmark;

    @FXML
    private Label osmark;

    @FXML
    private Label pemg;

    @FXML
    private Label pemmark;

    @FXML
    private Label spi;

    @FXML
    private Label stuid;


       
     
@Override
public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    //throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    public String getLoggedInUsername() {
        // Implement a method to retrieve the currently logged-in username
        // For example:
    	String loggedInUsername = SessionManager.getLoggedInUsername(); 
        return loggedInUsername;
    }
    
    String name="";
    
    private void loadDataFromFile() {
        String filePath = "data.txt";
        String loggedInUsername = getLoggedInUsername(); // Get the currently logged-in username

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(":");

                if (data.length == 5 && data[1].equals(loggedInUsername)) {
                	stuid.setText(data[0]);
                	name=data[0];
                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    int oop_marks;
    int os_marks;
    int dm_marks;
    int pem_marks;
    int coa_marks;
    
    private void Loadmarks() {
        String filePath = "result.txt";
        boolean userFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(":");

                if (data.length == 6 && data[0].equals(name)) {
                	oopmark.setText(data[1]);
                	oop_marks = Integer.parseInt(data[1]);
                	osmark.setText(data[2]);
                	os_marks = Integer.parseInt(data[2]);
                    coamark.setText(data[3]);
                    coa_marks = Integer.parseInt(data[3]);
                    pemmark.setText(data[4]);
                    pem_marks = Integer.parseInt(data[4]);
                    dmmark.setText(data[5]);
                    dm_marks = Integer.parseInt(data[5]);
                    userFound = true;
                    return;
                }
                
                if (!userFound) {
                    oopmark.setText("");
                    osmark.setText("");
                    coamark.setText("");
                    pemmark.setText("");
                    dmmark.setText("");
                    // You might also want to reset the respective variables to 0
                    oop_marks = 0;
                    os_marks = 0;
                    coa_marks = 0;
                    pem_marks = 0;
                    dm_marks = 0;
                }
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    thrd t1 = new thrd();
    thrd t2 = new thrd();

    t1.start();
    t2.start();

    try {
        t1.join();
        t2.join();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

   
}
}
class thrd extends Thread
{
       
 
}
