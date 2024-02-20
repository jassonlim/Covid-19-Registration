/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Class;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author handikaharianto
 */
public class Login {
    private String userEmail;
    private String userPassword;

    public Login() {
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
    
    public void writeLoginHistory(String userEmail) {
        DateTime dateTime = new DateTime();
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("loggingHistory.txt", true))) {
            // write to text file
            writer.write(userEmail + "\t" + dateTime.getCurrentFormattedDateTime() + "\t" + "login to system");
            writer.newLine();   // write line separator
        } catch(IOException e) {
            // add something later on
            System.out.println("error");
        }
    }
}
