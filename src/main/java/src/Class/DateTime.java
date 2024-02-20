/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Class;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author handikaharianto
 */
public class DateTime {
    private final String currentFormattedDateTime;   // to store formatted date and time
    
    private final LocalDateTime currentDateTime;
    private final DateTimeFormatter formatter;
    
    
    
    public DateTime() {
        currentDateTime = LocalDateTime.now();  // to store the current date and time
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // to format date and time
        currentFormattedDateTime = currentDateTime.format(formatter);   // formatted result
    }

    public String getCurrentFormattedDateTime() {
        return currentFormattedDateTime;
    }
}
