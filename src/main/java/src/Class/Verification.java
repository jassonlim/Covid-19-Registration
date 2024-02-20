/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author handikaharianto
 */
public class Verification {
    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;
    private String userEmail;
    private String userPassword;
    private String userReEnterPassword;

    public Verification() {
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserReEnterPassword() {
        return userReEnterPassword;
    }

    public void setUserReEnterPassword(String userReEnterPassword) {
        this.userReEnterPassword = userReEnterPassword;
    }

    public boolean isTextFieldEmpty(String userEmail, String userPassword) {
        return userEmail.trim().equals("") || userPassword.trim().equals("");
    }
    
    public boolean isTextFieldEmpty(String userFirstName, String userLastName, String userPhonenumber, String userEmail, String userPassword) {
        return userFirstName.trim().equals("") || userLastName.trim().equals("") || userPhonenumber.trim().equals("") || userEmail.trim().equals("") || userPassword.trim().equals("");
    }
    
    public boolean isTextFieldEmpty(String userFirstName, String userLastName, String userPhonenumber, String userEmail, String userPassword, String userReEnterPassword) {
        return userFirstName.trim().equals("") || userLastName.trim().equals("") || userPhonenumber.trim().equals("") || userEmail.trim().equals("") || userPassword.trim().equals("") || userReEnterPassword.trim().equals("");
    }
    
    public boolean isCredentialsValid(String userEmail, String userPassword, String userType) {
        // replace '/' with "_" and add .txt
        userType = userType.replace("/", "_") + ".txt";
        try(BufferedReader file = new BufferedReader(new FileReader(userType))) {
            String line;
            // keep looping as long as it's not null
            while ((line = file.readLine()) != null) {
                // read line then convert to arrays of String
                String[] userDetails = line.split("\t");
                // check if email and password are equal
                if (userDetails[0].toLowerCase().equals(userEmail.toLowerCase()) && userDetails[1].equals(userPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        // return false if account is not found
        return false;
    }
    
    public boolean isValidPhoneNumber(String phoneNumber) {
        // checking whether the phone number is less than 2, empty or doesn't start with '+'
        if (phoneNumber.length() < 2 || phoneNumber.equals("") || !((phoneNumber.charAt(0) == '+'))) {
            return false;
        }
        
        // check if the phoneNumber contains non-integer
        for (var num : phoneNumber.substring(1).toCharArray()) { // converting String to array of characters
            if (!(Character.isDigit(num))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isPasswordsEqual(String password, String reEnterPassword) {
        return password.trim().equals(reEnterPassword.trim());
    }
    
    public boolean doesAccountExist(String phoneNumber, String email, String userType) {        
        try(BufferedReader file = new BufferedReader(new FileReader(userType + ".txt"))) {
            String line;
            // keep looping as long as it's not null
            while((line = file.readLine()) != null) {
                String[] userDetails = line.split("\t"); // read line then convert to arrays of String
                // check whether phone number or email have been registered
                if (userDetails[3].equals(phoneNumber) || userDetails[0].equals(email)) {
                    return true;
                }
            }
        } catch(IOException e) {
            return false;
        }
        return false;
    }
    
    public boolean isCorrectVaccineDose(String userEmail, String vaccineDose) {
        try(BufferedReader file = new BufferedReader(new FileReader("Citizen_Non-Citizen.txt"))) {
            String line;
            while((line = file.readLine()) != null) {
                String [] userDetails = line.split("\t");
                if (userDetails[0].equals(userEmail)) {
                    if (userDetails[4].equals("UNVACCINATED") && vaccineDose.equals("FIRST_DOSE")) {
                        return true;
                    } else if (userDetails[4].equals("FIRST_DOSE") && vaccineDose.equals("SECOND_DOSE")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    
}
