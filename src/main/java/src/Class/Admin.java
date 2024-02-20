/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Class;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import src.UserInterface.AdminMenuFrame;

/**
 *
 * @author handikaharianto
 */
public class Admin extends User{
    
    private final Login adminLogin;
    
    // no-args constructor
    public Admin() {
        this.adminLogin = new Login();
    }
    
    public Admin(String email, String password) {
        super(email, password);
        this.adminLogin = new Login();
    }
    
    @Override
    public void login(String email, String password) {
        // display menu for admin
        new AdminMenuFrame(email).setVisible(true);
        
        adminLogin.writeLoginHistory(email);
    }
    
    public void addPersonnel(String firstName, String lastName, String email, String phoneNumber, String password) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Personnel.txt", true))) {
            // write to Personnel.txt
            writer.write(email.toLowerCase() + "\t" + password + "\t" + firstName + " " + lastName + "\t" + phoneNumber);
            writer.newLine();   // add line separator
        } catch (IOException e) {
        }
    }
}
