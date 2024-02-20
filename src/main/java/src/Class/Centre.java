/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author handikaharianto
 */
public class Centre {
    private String id;
    private String name;
    private String Address;

    public Centre() {
    }

    public Centre(String id, String name, String Address) {
        this.id = id;
        this.name = name;
        this.Address = Address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    public ArrayList<String[]> getAvailableCentres() {
        // to store the data of centres
        ArrayList<String[]> centres = new ArrayList<>();
        try(BufferedReader file = new BufferedReader(new FileReader("vaccinationCentre.txt"))) {
            String line;    // to store each line
            // keep looping if line exists
            while ((line = file.readLine()) != null) {
                centres.add(line.split("\t"));  // add each line to "centres" variable
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        return centres;
    }
}
