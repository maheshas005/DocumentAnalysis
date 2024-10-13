package com.bank.analysis;

import java.util.Map;

public class Statistics {
    private long totalScanTime;
    private long totalSaveTime;
    private long totalShowTime;
    private int documentCount;

    public void addLog(LogEntry entry) {
   
        totalScanTime += entry.getScanTime();
        totalSaveTime += entry.getSaveTime();
        totalShowTime += entry.getShowTime();
        documentCount++;
     
    }


    public long getAverageScanTime() {
        long avgScanTime = documentCount == 0 ? 0 : totalScanTime / documentCount;
        return avgScanTime;
    }

    public long getAverageSaveTime() {
        long avgSaveTime = documentCount == 0 ? 0 : totalSaveTime / documentCount;
        return avgSaveTime;
    }

    public long getAverageShowTime() {
        long avgShowTime = documentCount == 0 ? 0 : totalShowTime / documentCount;
        return avgShowTime;
    }
    
    public int getCount() {
        return documentCount;
    }
    
    
    public long getWeightedAverageScanTime(Map<Integer, Statistics> hourlyStats) {
        long totalWeightedScanTime = 0;
        int totalDocs = 0;
        
        for (Map.Entry<Integer, Statistics> entry : hourlyStats.entrySet()) {
            Statistics hourStat = entry.getValue();
            totalWeightedScanTime += hourStat.getAverageScanTime() * hourStat.getCount();
            totalDocs += hourStat.getCount();
        }
        
        return totalDocs == 0 ? 0 : totalWeightedScanTime / totalDocs;
    }
}
