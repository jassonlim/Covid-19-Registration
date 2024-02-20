/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import src.UserInterface.PersonMenuFrame;
import src.UserInterface.PersonProfileFrame;

/**
 *
 * @author handikaharianto
 */
public class Person extends User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private VaccinationStatus vaccinationStatus;
    private final Login personLogin;
    
    // no-args constructor
    public Person() {
        this.personLogin = new Login();
    }

    public Person(String email, String password, String firstName, String lastName, String phoneNumber, VaccinationStatus vaccinationStatus) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.vaccinationStatus = vaccinationStatus;
        this.personLogin = new Login();
    }

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public void login(String email, String password) {
        // display menu for Citizen/Non-Citizen
        PersonMenuFrame personMenuUI = new PersonMenuFrame(email, password);
        personMenuUI.setVisible(true);

        personLogin.writeLoginHistory(email);
    }
    
    public void register(String email, String password, String firstName, String lastName, String phoneNumber, VaccinationStatus vaccinationStatus) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Citizen_Non-Citizen.txt", true))) {
            email = email.toLowerCase();    // convert email to lowercase
            
            // capitalize first letter of first name and last name
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            
            // write to text file
            writer.write(email + "\t" + password + "\t" + firstName + " " + lastName + "\t" + phoneNumber + "\t" + vaccinationStatus);
            writer.newLine();
        } catch(IOException e) {
            // add something later on
            System.out.println("error in register method");
        }
    }
    
    public void viewProfile(String email, String password) {
        // Display PersonProfileFrame
        new PersonProfileFrame(email, password).setVisible(true);
    }
    
    public boolean modifyDetails(String personEmail, String personDetails) {
        ArrayList<String> personData = new ArrayList<>();
        
        // Read & modify data
        boolean isModified = false;
        try(BufferedReader file = new BufferedReader(new FileReader("Citizen_Non-Citizen.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                if (line.contains(personEmail) && line.equals(personDetails)) {
                    personData.add(line);
                } else if(line.contains(personEmail) && !(line.equals(personDetails))) {
                    personData.add(personDetails);
                    isModified = true;
                } else {
                    personData.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        
        // Re-Write text file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Citizen_Non-Citizen.txt"))) {
            for(String line : personData) {
                writer.write(line);
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("error");
        }
        
        return isModified;
    }
    
    public ArrayList<String[]> getPeople() {
        ArrayList<String[]> peopleDetails = new ArrayList<>();
        try(BufferedReader file = new BufferedReader(new FileReader("Citizen_Non-Citizen.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                String[] personDetails = line.split("\t");
                String[] personNameAndEmail = (personDetails[2] + "\t" + personDetails[0]).split("\t");
                peopleDetails.add(personNameAndEmail);
            }
        } catch(IOException e) {
            System.out.println("error");
        }
        return peopleDetails;
    }
    
    public String[] getSpecificPerson(String email) {
        try(BufferedReader file = new BufferedReader(new FileReader("Citizen_Non-Citizen.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                String[] personDetails = line.split("\t");
                if (personDetails[0].equals(email)) {
                    return personDetails;
                }
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return null;
    }
}