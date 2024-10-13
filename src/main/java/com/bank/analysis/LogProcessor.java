package com.bank.analysis;

import java.util.ArrayList;
import java.util.List;

public class LogProcessor {
    private List<LogEntry> logEntries = new ArrayList<>();


    public void processLogLine(LogEntry entry) {
        logEntries.add(entry);
    }

    public Statistics processLogs(String office, String user, Integer day, Integer hour) {
        Statistics stats = new Statistics();

        for (LogEntry entry : logEntries) {
         
                stats.addLog(entry);
        }

        return stats;
    }

}
