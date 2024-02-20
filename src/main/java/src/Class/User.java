/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Class;

import src.UserInterface.LoginFrame;

/**
 *
 * @author handikaharianto
 */
public abstract class User {
    protected String email;
    protected String password;
    private final Logout userLogout;
    
    // no-args constructor
    public User() {
        this.userLogout = new Logout();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.userLogout = new Logout();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public abstract void login(String email, String password);
    
    public void logout(String email) {
        // create an instance of Logout class
        userLogout.writeLogoutHistory(email);
        
        // Display LoginFrame
        new LoginFrame().setVisible(true);
    }
}
