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
public class Vaccine {
    private String name;
    private String code;
    private int quantity;

    public Vaccine() {
    }

    public Vaccine(String name, String code, int quantity) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public ArrayList<String[]> getAvailableVaccines() {
        // to store vaccines
        ArrayList<String[]> vaccines = new ArrayList<>();
        try(BufferedReader file = new BufferedReader(new FileReader("Vaccine.txt"))) {
            String line;
            // keep looping if line exist
            while((line = file.readLine()) != null) {
                vaccines.add(line.split("\t")); // add each line to vaccines variable
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        return vaccines;
    }
}
