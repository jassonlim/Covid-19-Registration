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

/**
 *
 * @author handikaharianto
 */
public class Appointment {
    private long code;
    private String dateAndTime;
    private String centreName;
    private String userEmail;
    private String vaccineName;

    public Appointment() {
    }

    public Appointment(long code, String dateAndTime, String centreName, String userEmail, String vaccineName) {
        this.code = code;
        this.dateAndTime = dateAndTime;
        this.centreName = centreName;
        this.userEmail = userEmail;
        this.vaccineName = vaccineName;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
    
    public void addVaccinationAppointment(long code, String userEmail, String vaccineName, String dateAndTime, String centreName, String vaccineDose) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Appointment.txt", true))) {
            writer.write(code + "\t" + userEmail + "\t" + vaccineName + "\t" + dateAndTime + "\t" + centreName + "\t" + vaccineDose);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("error");
        }
    }
    
    public String[] displaySpecificAppointment(String userEmail) {
        String[] personDetails = null;
        try(BufferedReader file = new BufferedReader(new FileReader("Appointment.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                personDetails = line.split("\t");
                if (personDetails[1].equals(userEmail)) {
                    return personDetails;
                }
            }
        } catch(IOException e) {
            System.out.println("error");
        }
        return personDetails;
    }
    
    public boolean isAppointmentExist(String userEmail) {
        try(BufferedReader file = new BufferedReader(new FileReader("Appointment.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                String[] personDetails = line.split("\t");
                if (personDetails[1].equals(userEmail)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
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
    
    public void updateAppointment(String previousUserEmail, String newUserEmail) {
        ArrayList<String> personData = new ArrayList<>();
        
        // Read & modify data
        try(BufferedReader file = new BufferedReader(new FileReader("Appointment.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                // check if line contains previous email
                if (line.contains(previousUserEmail)) {
                    // update the line
                    personData.add(line.replace(previousUserEmail, newUserEmail));
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
    }
    
    public ArrayList<String[]>displaySpecificAppointment1(String centre) {
        // to store the data of centres
        ArrayList<String[]> centres = new ArrayList<>();
        String[] personDetails = new String[5];
        try(BufferedReader file = new BufferedReader(new FileReader("Appointment.txt"))) {
            String line;    // to store each line
            // keep looping if line exists
            while ((line = file.readLine()) != null) {
                personDetails = line.split("\t");
                if (personDetails[4].equals(centre)){
                    centres.add(line.split("\t"));  // add each line to "centres" variable
             
                }
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        return centres;
    }
}
