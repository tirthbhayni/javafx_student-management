package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ShowResultController  implements Initializable {
	
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
	
    private String getLoggedInUsername() {
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
    
    
   
    

    
  
    
    private double calculateCGPA() {
    	
    	
    	double percentage = 0;
    	
    	percentage = ((oop_marks + os_marks + dm_marks + pem_marks + coa_marks) / 500.0) * 100;

        // Calculate the grade points from the percentage
        double gradePoints = percentage / 10.0 + 0.5;
        if(gradePoints >10)
        {
            return 10.0;
        }
        // Round to two decimal places
        double cgpa = Math.round(gradePoints * 100.0) / 100.0;

        return cgpa;
    }

    public void loadspi(){
    	
    	if (oop_marks == 0 && os_marks == 0 && coa_marks == 0 && pem_marks == 0 && dm_marks == 0) {
            // If marks are empty, set SPI to empty
            spi.setText("");
        } else {
            double cnt = calculateCGPA();
            spi.setText(String.valueOf(cnt));
        } 
    	
    }
    

    public void loadgrade() {
    	
    	if (oop_marks == 0 && os_marks == 0 && coa_marks == 0 && pem_marks == 0 && dm_marks == 0) {
            // If marks are empty, set grades to empty
            oopg.setText("");
            osg.setText("");
            coag.setText("");
            pemg.setText("");
            dmg.setText("");
        }
    	else {
    	
    	
         // set oop
         if(oop_marks>80 && oop_marks<=100)
         {
             oopg.setText("AA");
         }
         else if(oop_marks>70 && oop_marks<=80)
         {
             oopg.setText("AB");
         }
         else if(oop_marks>60 && oop_marks<=70)
         {
             oopg.setText("BB");
         }
         else if(oop_marks>50 && oop_marks<=60)
         {
             oopg.setText("BC");
         }
         else if(oop_marks>40 && oop_marks<=50)
         {
             oopg.setText("CC");
         }
         else if(oop_marks>33 && oop_marks<=40)
         {
             oopg.setText("DD");
         }
         else
         {
             oopg.setText("FF");
         }
//          osg.setText("po");
         //OS GRADE
         if(os_marks>80 && os_marks<=100)
         {
             osg.setText("AA");
         }
         else if(os_marks>70 && os_marks<=80)
         {
        	 osg.setText("AB");
         }
         else if(os_marks>60 && os_marks<=70)
         {
             osg.setText("BB");
         }
         else if(os_marks>50 && os_marks<=60)
         {
             osg.setText("BC");
         }
         else if(os_marks>40 && os_marks<=50)
         {
             osg.setText("CC");
         }
         else if(os_marks>33 && os_marks<=40)
         {
             osg.setText("DD");
         }
         else
         {
             osg.setText("FF");
         }

         //COA GRADE
         if(coa_marks>80 && coa_marks<=100)
         {
             coag.setText("AA");
         }
         else if(coa_marks>70 && coa_marks<=80)
         {
             coag.setText("AB");
         }
         else if(coa_marks>60 && coa_marks<=70)
         {
             coag.setText("BB");
         }
         else if(coa_marks>50 && coa_marks<=60)
         {
             coag.setText("BC");
         }
         else if(coa_marks>40 && coa_marks<=50)
         {
             coag.setText("CC");
         }
         else if(coa_marks>33 && coa_marks<=40)
         {
             coag.setText("DD");
         }
         else
         {
             coag.setText("FF");
         }
         //PEM GRADE
         if(pem_marks>80 && pem_marks<=100)
         {
             pemg.setText("AA");
         }
         else if(pem_marks>70 && pem_marks<=80)
         {
        	 pemg.setText("AB");
         }
         else if (pem_marks>60 && pem_marks<=70) {
        	 pemg.setText("BB");
         }
         else if(pem_marks>50 && pem_marks<=60) {
        	 pemg.setText("BC");
         }
         else if(pem_marks>40 && pem_marks<=50) {
        	 pemg.setText("CC");
         }
         
         else if(pem_marks>33 && pem_marks<=40) {
            	  pemg.setText("DD");
          }
         else
         {
        	 pemg.setText("FF");
       }
        
         
         //DM GRADE
         if(dm_marks>80 && dm_marks<=100)
         {
             dmg.setText("AA");
         }
         else if(dm_marks>70 && dm_marks<=80)
         {
             dmg.setText("AB");
         }
         else if(dm_marks>60 && dm_marks<=70)
         {
             dmg.setText("BB");
         }
         else if(dm_marks>50 && dm_marks<=60)
         {
             dmg.setText("BC");
         }
         else if(dm_marks>40 && dm_marks<=50)
         {
             dmg.setText("CC");
         }
         else if(dm_marks>33 && dm_marks<=40)
         {
             dmg.setText("DD");
         }
         
         else
         {
             dmg.setText("FF");
         }
         
    	}
     
     }
	public void initialize(URL url, ResourceBundle rb) {
		loadDataFromFile();
		Loadmarks();
		loadspi();
		loadgrade();
        
}
}
