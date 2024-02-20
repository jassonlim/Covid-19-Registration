/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.Class;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import src.UserInterface.PersonnelCitizenFrame;
import src.UserInterface.PersonnelMenuFrame;
import src.UserInterface.PersonnelRegisterCitizenFrame;
import src.UserInterface.PersonnelVaccinationAppointmentFrame;
import src.UserInterface.PersonnelVaccineFrame;

/**
 *
 * @author jassonlim
 */
public class Personnel extends User {
    private String firstName;
    private String lastName;
    private String dateBirth;
    private int phoneNumber;
    private final Login personnelLogin;
    
    public Personnel(){
        this.personnelLogin = new Login();
    }
    
    public Personnel(String email, String password, String firstName, String lastName, String dateBirth, int phoneNumber){
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
        this.personnelLogin = new Login();
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getDateBirth(){
        return dateBirth;
    }
    
    public void setDateBirth(String dateBirth){
        this.dateBirth = dateBirth;
    }
    
    public int phoneNumber(){
        return phoneNumber;
    }
    
    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public void registerCitizen(String email, String password){
        PersonnelRegisterCitizenFrame prcf = new PersonnelRegisterCitizenFrame(email, password);
        prcf.setVisible(true);
    }
    
    public void searchCitizen(String email, String password){
        PersonnelCitizenFrame pcf = new PersonnelCitizenFrame(email, password);
        pcf.setVisible(true);
    }
    
    public void addAppointment(String email, String password){
        PersonnelVaccinationAppointmentFrame pvaf = new PersonnelVaccinationAppointmentFrame(email, password);
        pvaf.setVisible(true);
    }
    
    public void searchAppointment(String email, String password){
        PersonnelVaccinationAppointmentFrame pvaf = new PersonnelVaccinationAppointmentFrame(email, password);
        pvaf.setVisible(true);
    }
    
    public boolean modifyAppointment(String email, String kalimat) {
        ArrayList<String> personData = new ArrayList<>();
        
        // Read & modify data
        boolean isModified = false;
        try(BufferedReader file = new BufferedReader(new FileReader("Appointment.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                if (line.contains(email) && line.equals(kalimat)) {
                    personData.add(line);
                } else if(line.contains(email) && !(line.equals(kalimat))) {
                    personData.add(kalimat);
                    isModified = true;
                } else {
                    personData.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        
        // Re-Write text file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Appointment.txt"))) {
            for(String line : personData) {
                writer.write(line);
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("error");
        }
        
        return isModified;
    }
    
    
    public void removeAppointment(String userEmail) {
        ArrayList<String> personData = new ArrayList<>();
        
        // Read & modify data
        try(BufferedReader file = new BufferedReader(new FileReader("Appointment.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                if (!(line.contains(userEmail))) {
                    personData.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        
        // Re-Write text file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Appointment.txt"))) {
            for(String line : personData) {
                writer.write(line);
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("error");
        }
    }
    
    
    public void searchVaccineSup(String email, String password){
        PersonnelVaccineFrame pvf = new PersonnelVaccineFrame(email, password);
        pvf.setVisible(true);
    }
    
    public boolean modifyVaccine(String email, String kalimat) {
        ArrayList<String> personData = new ArrayList<>();
        
        // Read & modify data
        boolean isModified = false;
        try(BufferedReader file = new BufferedReader(new FileReader("Vaccine.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                if (line.contains(email) && line.equals(kalimat)) {
                    personData.add(line);
                } else if(line.contains(email) && !(line.equals(kalimat))) {
                    personData.add(kalimat);
                    isModified = true;
                } else {
                    personData.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        
        // Re-Write text file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Vaccine.txt"))) {
            for(String line : personData) {
                writer.write(line);
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("error");
        }
        
        return isModified;
    }
    
    public void removeVaccine(String userEmail) {
        ArrayList<String> personData = new ArrayList<>();
        
        // Read & modify data
        try(BufferedReader file = new BufferedReader(new FileReader("Vaccine.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                if (!(line.contains(userEmail))) {
                    personData.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        
        // Re-Write text file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Vaccine.txt"))) {
            for(String line : personData) {
                writer.write(line);
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("error");
        }
    }
    
     @Override
    public void login(String email, String password) {
        PersonnelMenuFrame personnelMenuUI = new PersonnelMenuFrame(email, password);
        personnelMenuUI.setVisible(true);
        
        personnelLogin.writeLoginHistory(email);
    }
}
