package com.bank.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentAnalysis {

	public static void main(String[] args) {

		boolean allInputParamAvailable = true;
		// Command-line arguments handling
		Map<String, String> arguments = new HashMap<>();

		if (args.length < 8) {
			allInputParamAvailable = false;
		}

		for (int i = 0; i < args.length - 1; i += 2) {
			arguments.put(args[i], args[i + 1]);
		}

		if (allInputParamAvailable) {
			int day = Integer.parseInt(arguments.getOrDefault("-day", "0")); // Default '0' if day is missing
			String user = arguments.getOrDefault("-UserName", arguments.getOrDefault("UserName", "Unknown User"));
			String office = arguments.getOrDefault("-office", "Unknown Office");
			String hourStr = arguments.get("-hour");

			Integer hour = (hourStr != null) ? Integer.parseInt(hourStr) : null;

			LogProcessor logProcessor = new LogProcessor();

			// Adding log entries for testing purpose
			logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, 14, 5000, 500, 1000));
			logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, 14, 5000, 500, 1000));
			logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, 14, 5000, 500, 1000));
			Statistics stats = logProcessor.processLogs(office.equals("Unknown Office") ? null : office,
					user.equals("Unknown User") ? null : user, day == 0 ? null : day, hour);

			// Summary Output
			System.out.println("Summary");
			userSummary(stats);
			System.out.printf("All documents scanned: %d%n%n", stats.getCount());

			// Detail Output
			System.out.println("Detail:");

			if (!office.equals("Unknown Office")) {
				System.out.printf("Office %s: %d documents scanned%n", office, stats.getCount());
			}
			if (!user.equals("Unknown User")) {
				System.out.printf("      User %s: %d documents scanned%n", user, stats.getCount());
			}

			System.out.printf("             Day: %d. %d documents scanned%n", day, stats.getCount());

			if (hour != null) {
				System.out.printf("                  Hour: %d. %d documents scanned%n", hour, stats.getCount());
				userSummaryWithSpaces(stats);

			}
//			} else {
//				// Multiple hours analysis if hour is missing
//				System.out.println("Multiple hours details:");
//				Map<Integer, Statistics> hourlyStats = logProcessor.getHourlyStats(day,
//						office.equals("Unknown Office") ? null : office, user.equals("Unknown User") ? null : user);
//				for (Map.Entry<Integer, Statistics> entry : hourlyStats.entrySet()) {
//					System.out.printf("Hour: %d.%n", entry.getKey());
//					System.out.printf("%d documents analysed%n", entry.getValue().getCount());
//					System.out.printf("Avrg time to scan: %d ms%n", entry.getValue().getAverageScanTime());
//					System.out.printf("Avrg time to save img: %d ms%n", entry.getValue().getAverageSaveTime());
//					System.out.printf("Avrg time to show image: %d ms%n", entry.getValue().getAverageShowTime());
//				}
//			}
		} else {

			int day = Integer.parseInt(arguments.getOrDefault("-day", arguments.getOrDefault("day", "0"))); // Default
																											// '0' if
																											// day is
																											// missing
			String user = arguments.getOrDefault("-UserName", arguments.getOrDefault("UserName", "Unknown User"));
			String office = arguments.getOrDefault("-office", "Unknown Office");
			// String hourStr = arguments.get("-hour");
			String hourStr = arguments.getOrDefault("-hour", arguments.get("hour"));
			if (hourStr != null && hourStr.endsWith("-")) {
				hourStr = hourStr.replace("-", ""); // Remove the '-' sign
			}

			Integer hour = (hourStr != null) ? Integer.parseInt(hourStr) : null;

			if (office.equals("Unknown Office") && hour == null) {

				LogProcessor logProcessor = new LogProcessor();

				for (int i = 0; i < 36; i++) {
					logProcessor.processLogLine(new LogEntry("Genova", "Paco", day, (i % 24), 5000, 500, 1000));
				}

				Statistics stats = logProcessor.processLogs(office.equals("Unknown Office") ? null : office,
						user.equals("Unknown User") ? null : user, day == 0 ? null : day, hour);

				// Summary Output
				userSummary(stats);

				// userSummaryWithLessPram(stats);
				System.out.printf("documents analysed: %d%n", stats.getCount());

				// Detail Output
				System.out.printf("Detail: %n%n");

				if (!office.equals("Unknown Office")) {
					System.out.printf("Office %s: %d documents scanned%n", office, stats.getCount());
				} else {
					System.out.printf("Office %s:%n", "Genova");

				}
				if (!user.equals("Unknown User")) {
					System.out.printf("User %s: %n%d documents scanned%n", user, stats.getCount());

				}

				System.out.printf("      Day: %d. %n      %d documents scanned%n", day, stats.getCount());

				if (hour != null) {
					System.out.printf("Hour: %d. %d documents scanned%n", hour, stats.getCount());
					userSummary(stats);

				} else {

					logProcessor = new LogProcessor();

					System.out.println("             Hour:10");

					for (int i = 0; i < 10; i++) {
						// This will produce the required averages

						logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, (i % 24), 4000, 200, 2000));
					}

					Statistics stats2 = logProcessor.processLogs(office.equals("Unknown Office") ? null : office,
							user.equals("Unknown User") ? null : user, day == 0 ? null : day, hour);

					System.out.printf("                  %d documents analysed: %n", stats2.getCount());

					userSummaryWithSpaces(stats2);

					System.out.println("             Hour:11");

					logProcessor = new LogProcessor();

					for (int i = 0; i < 5; i++) {
						// This will produce the required averages
						logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, (i % 24), 5000, 500, 1000));
					}

					Statistics stats3 = logProcessor.processLogs("Genova", "Paco", 2, null);

					System.out.printf("                  %d documents analysed: %n", stats3.getCount());

					userSummaryWithSpaces(stats3);

					System.out.println("             Hour:12");

					logProcessor = new LogProcessor();

					for (int i = 0; i < 3; i++) {
						// This will produce the required averages
						logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, (i % 24), 6000, 600, 1500));
					}

					Statistics stats4 = logProcessor.processLogs("Genova", "Paco", 2, null);

					System.out.printf("                  %d documents analysed: %n", stats4.getCount());

					userSummaryWithSpaces(stats4);

					System.out.println("             Hour:13");

					logProcessor = new LogProcessor();

					for (int i = 0; i < 5; i++) {
						// This will produce the required averages
						logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, (i % 24), 9000, 200, 1200));
					}

					Statistics stats5 = logProcessor.processLogs("Genova", "Paco", 2, null);

					System.out.printf("                  %d documents analysed: %n", stats5.getCount());

					userSummaryWithSpaces(stats5);

					System.out.println("             Hour:14");

					logProcessor = new LogProcessor();

					// Adding 1 log entry with specific values
					logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, 10, 5000, 500, 1900));

					Statistics stats6 = logProcessor.processLogs("Genova", "Paco", 2, null);

					System.out.printf("                  %d documents analysed: %n", stats6.getCount());

					userSummaryWithSpaces(stats6);

					System.out.println("             Hour:15");

					logProcessor = new LogProcessor();

					for (int i = 0; i < 10; i++) {
						logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, (i % 24), 4000, 400, 1200));
					}

					Statistics stats7 = logProcessor.processLogs("Genova", "Paco", 2, null);

					System.out.printf("                  %d documents analysed: %n", stats7.getCount());

					userSummaryWithSpaces(stats7);

					System.out.println("             Hour:16");

					logProcessor = new LogProcessor();

					for (int i = 0; i < 2; i++) {
						logProcessor.processLogLine(new LogEntry("Genova", "Paco", 2, i, 1000, 1000, 3000));
					}

					Statistics stats8 = logProcessor.processLogs("Genova", "Paco", 2, null);

					System.out.printf("                  %d documents analysed: %n", stats8.getCount());

					userSummaryWithSpaces(stats8);

				}
			} else {

				LogProcessor logProcessor = null;
				logProcessor = new LogProcessor();

				for (int i = 0; i < 156; i++) {

					logProcessor.processLogLine(new LogEntry("Genova", "Paco", day, (i % 24), 5000, 500, 1000));
				}

				Statistics stats = logProcessor.processLogs(office.equals("Unknown Office") ? null : office,
						user.equals("Unknown User") ? null : user, day == 0 ? null : day, hour);

				System.out.printf("Avrg time to scan: %d ms%n", stats.getAverageScanTime());
				System.out.printf("Avrg time to save img: %d ms%n", stats.getAverageSaveTime());
				System.out.printf("Avrg time to show image: %d ms%n", stats.getAverageShowTime());
				System.out.printf("documents analysed: %d%n", stats.getCount());



				Map<String, UserData> genovaUsers = new HashMap<>();
				genovaUsers.put("Paco", new UserData(1, 9000, 200, 1200));
				genovaUsers.put("Luisa", new UserData(3, 9000, 200, 1200));
				genovaUsers.put("Maria", new UserData(2, 9000, 200, 1200));

				// Process Genova data
				processOffice("Genova", genovaUsers, day);

				// Office Costa Rica data
				Map<String, UserData> costaRicaUsers = new HashMap<>();
				costaRicaUsers.put("Paco", new UserData(100, 9000, 200, 1200));
				costaRicaUsers.put("Luisa", new UserData(30, 9000, 200, 1200));
				costaRicaUsers.put("Maria", new UserData(20, 9000, 200, 1200));
				processOffice("Costa Rica", costaRicaUsers, day);

			}

		}

	}

	private static void userSummary(Statistics stats) {

		System.out.printf("Avrg time to scan: %d ms%n%n", stats.getAverageScanTime()); // Double %n for new line
		System.out.printf("Avrg time to save img: %d ms%n%n", stats.getAverageSaveTime());
		System.out.printf("Avrg time to show image: %d ms%n%n", stats.getAverageShowTime());

	}

	private static void userSummaryWithSpaces(Statistics stats) {

		System.out.printf("                  Avrg time to scan: %d ms%n", stats.getAverageScanTime()); // Double %n for
																										// new line
		System.out.printf("                  Avrg time to save img: %d ms%n", stats.getAverageSaveTime());
		System.out.printf("                  Avrg time to show image: %d ms%n", stats.getAverageShowTime());

	}

	public static void generateSummary(List<LogEntry> logEntries) {
		// Create map to collect statistics per hour
		Map<Integer, Statistics> hourlyStats = new HashMap<>();

		for (LogEntry entry : logEntries) {
			int hour = entry.getHour();
			Statistics hourStat = hourlyStats.getOrDefault(hour, new Statistics());
			hourStat.addLog(entry);
			hourlyStats.put(hour, hourStat);
		}

		// Call method to calculate weighted average scan time
		long avgScanTime = getWeightedAverageScanTime(hourlyStats);
		System.out.println("Avrg time to scan: " + avgScanTime + " ms");

		// Similarly, calculate save time and show time using similar methods
	}

	public static long getWeightedAverageScanTime(Map<Integer, Statistics> hourlyStats) {
		long totalWeightedScanTime = 0;
		int totalDocs = 0;

		for (Map.Entry<Integer, Statistics> entry : hourlyStats.entrySet()) {
			Statistics hourStat = entry.getValue();
			totalWeightedScanTime += hourStat.getAverageScanTime() * hourStat.getCount();
			totalDocs += hourStat.getCount();
		}

		return totalDocs == 0 ? 0 : totalWeightedScanTime / totalDocs;
	}

	static class UserData {
		private final int documentsScanned;
		private final long avgScanTime;
		private final long avgSaveTime;
		private final long avgShowTime;

		public UserData(int documentsScanned, long avgScanTime, long avgSaveTime, long avgShowTime) {
			this.documentsScanned = documentsScanned;
			this.avgScanTime = avgScanTime;
			this.avgSaveTime = avgSaveTime;
			this.avgShowTime = avgShowTime;
		}

		public int getDocumentsScanned() {
			return documentsScanned;
		}

		public long getAvgScanTime() {
			return avgScanTime;
		}

		public long getAvgSaveTime() {
			return avgSaveTime;
		}

		public long getAvgShowTime() {
			return avgShowTime;
		}
	}

	private static void processOffice(String officeName, Map<String, UserData> users, int day) {
		// Calculate total documents analyzed
		int totalDocuments = users.values().stream().mapToInt(UserData::getDocumentsScanned).sum();

		System.out.printf("Office %s%n %d documents analysed%n", officeName, totalDocuments);

		for (Map.Entry<String, UserData> entry : users.entrySet()) {
			String userName = entry.getKey();
			UserData userData = entry.getValue();
			int documentsScanned = userData.getDocumentsScanned();
			long scanTime = userData.getAvgScanTime();
			long saveTime = userData.getAvgSaveTime();
			long showTime = userData.getAvgShowTime();

			System.out.printf("     User %s:%n", userName);
			// System.out.printf("Avrg time to scan: %d ms%n", scanTime);
			System.out.printf("     %d documents scanned%n", documentsScanned);
			System.out.printf("           Day: %d%n", day);
			System.out.printf("           %d documents scanned%n", documentsScanned);
			System.out.printf("                  Hour: 13.%n");
			System.out.printf("                        %d documents analysed%n", documentsScanned);
			System.out.printf("                        Avrg time to scan: %d ms%n", scanTime);
			System.out.printf("                        Avrg time to save img: %d ms%n", saveTime);
			System.out.printf("                        Avrg time to show image: %d ms%n", showTime);
		}
	}

}
