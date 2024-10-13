
package com.bank.analysis;

/** * This is an example of a log entry used in a bank office to process documents.
 *  * Office name, user name, and processing times are among the details of the log 
 *  that are encapsulated in this class.OOP Principles Used: 1. **Encapsulation**: 
 *  Every field is private, guaranteeing that external parties cannot directly access 
 *  the LogEntry object's internal state. Access is offered via methods called public 
 *  getter  2. Abstraction: By hiding the implementation details and offering a clear
 *   API (getters), the class abstracts the idea of a log entry.
*/

public class LogEntry {
    
    private String officeName;  // Name of the office
    private String userName;    // Name of the user
    private int day;            // Day of the entry (1-31)
    private int hour;           // Hour of the entry (0-23)
    private long scanTime;      // Time taken to scan the document (in milliseconds)
    private long saveTime;      // Time taken to save the document (in milliseconds)
    private long showTime;      // Time taken to show the document (in milliseconds)

    /**
     * Constructor to initialize a LogEntry object.
     *
     * @param officeName the name of the office
     * @param userName the name of the user
     * @param day the day of the log entry
     * @param hour the hour of the log entry
     * @param scanTime the time taken to scan the document
     * @param saveTime the time taken to save the document
     * @param showTime the time taken to show the document
     */
    public LogEntry(String officeName, String userName, int day, int hour, long scanTime, long saveTime, long showTime) {
        this.officeName = officeName; 
        this.userName = userName;     
        this.day = day;               
        this.hour = hour;             
        this.scanTime = scanTime;     
        this.saveTime = saveTime;    
        this.showTime = showTime;     
    }


    /**
     * Gets the name of the office.
     *
     * @return the office name
     */
    public String getOfficeName() {
        return officeName;
    }

    /**
     * Gets the name of the user.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the day of the log entry.
     *
     * @return the day (1-31)
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the hour of the log entry.
     *
     * @return the hour (0-23)
     */
    public int getHour() {
        return hour;
    }

    /**
     * Gets the time taken to scan the document.
     *
     * @return the scan time in milliseconds
     */
    public long getScanTime() {
        return scanTime;
    }

    /**
     * Gets the time taken to save the document.
     *
     * @return the save time in milliseconds
     */
    public long getSaveTime() {
        return saveTime;
    }

    /**
     * Gets the time taken to show the document.
     *
     * @return the show time in milliseconds
     */
    public long getShowTime() {
        return showTime;
    }
}
