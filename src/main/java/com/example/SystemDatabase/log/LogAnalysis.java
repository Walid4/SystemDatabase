package com.example.SystemDatabase.log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogAnalysis {

	private static final String newLine = "\r\n";

	public ArrayList<App> searchFile(String fileName, String searchStr) {

		String line;
		char type;
		int startIndex, endIndex, index;
		ArrayList<App> list = new ArrayList<>();

		try {

			BufferedReader buffer = new BufferedReader(new FileReader(fileName));

			while ((line = buffer.readLine()) != null) {
				
				if (line.contains(searchStr) && line.contains("(") && line.contains("-")) {
					//System.out.println(line);
					App err = new App();
					boolean flag = true;

					startIndex = line.indexOf("(") + 1;
					endIndex = line.indexOf(")");
					index = line.indexOf("-");
					type = line.charAt(index - 1);

					err.setName(line.substring(startIndex, index - 1));

					if (type == '1')
						err.setT(Type.CORE);
					else
						err.setT(Type.CUSTOM);

					err.setErrorNum(line.substring(index + 1, endIndex));

					if (list.isEmpty()) {
						list.add(err);

					} else {
						for (int i = 0; i < list.size(); i++) {
							if ((list.get(i).getName()).equals(err.getName()) && (list.get(i).getT()).equals(err.getT())
									&& (list.get(i).getErrorNum()).equals(err.getErrorNum())) {

								list.get(i).setCount((list.get(i).getCount()) + 1);
								flag = false;
								break;
							}

						}
						if (flag) {
							list.add(err);

						}
					}

				}
			}

			buffer.close();

		} catch (FileNotFoundException ex) {

			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException e) {

			System.out.println("IO Error Occurred: " + e.toString());

		}
		return list;

	}

	public void writeReport(String fileName, ArrayList<App> list) {

		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter buffer = new BufferedWriter(fileWriter);

			for (int i = 0; i < list.size(); i++) {

				buffer.append("App Name:" + list.get(i).getName() + "  Error Type:" + list.get(i).getT()
						+ "  Error Code:" + list.get(i).getErrorNum() + "  Number Of Errors:" + list.get(i).getCount());
				if (i < list.size() - 1)
					buffer.append(newLine);
			}

			buffer.close();

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find file: " + fileName);
			System.exit(1);
		} catch (IOException e) {
			System.out.println(e + "encountered while initializing cash drawer writer.");
		}

	}

	public Severity defectsBySeverity(String logName) {

		int index;
		String line;
		char sType;
		Severity severity = new Severity();

		try {

			BufferedReader buffer = new BufferedReader(new FileReader(logName));

			while ((line = buffer.readLine()) != null) {

				if (line.contains("Caused by") && line.contains("(")) {

					index = line.indexOf(")");
					sType = line.charAt(index - 1);

					if (sType >= '1' || sType <= '3') {
						severity.setS(SeverityLevel.CRITICAL);
						severity.setCriticalCnt(severity.getCriticalCnt() + 1);

					} else if (sType >= '4' || sType <= '6') {
						severity.setS(SeverityLevel.ERROR);
						severity.setErrorCnt(severity.getErrorCnt() + 1);

					} else {
						severity.setS(SeverityLevel.WARNING);
						severity.setWarningCnt(severity.getWarningCnt() + 1);
					}
				}

			}
			buffer.close();

		} catch (FileNotFoundException ex) {

			System.out.println("Unable to open file '" + logName + "'");
		} catch (IOException e) {

			System.out.println("IO Error Occurred: " + e.toString());

		}
		return severity;

	}

	public ArrayList<App> defectsByApp(String logName) {

		ArrayList<App> list = new ArrayList<>();
		ArrayList<App> defectsList = new ArrayList<>();
		int type;

		list = searchFile(logName, "Caused by");

		for (int i = 0; i < list.size(); i++) {
			App app = new App();
			boolean flag = true;

			app.setName(list.get(i).getName());
			app.setT(list.get(i).getT());

			type = Integer.parseInt((list.get(i).getErrorNum()));
			type = type % 10;

			if (type >= 1 || type <= 3) {
				app.setS(SeverityLevel.CRITICAL);
				app.setCriticalCnt(list.get(i).getCount());

			} else if (type >= 4 || type <= 6) {
				app.setS(SeverityLevel.ERROR);
				app.setErrorCnt(list.get(i).getCount());

			} else {
				app.setS(SeverityLevel.WARNING);
				app.setWarningCnt(list.get(i).getCount());
			}

			if (defectsList.isEmpty())
				defectsList.add(app);
			else {
				for (int j = 0; j < defectsList.size(); j++) {
					if ((defectsList.get(j).getName()).equals(app.getName())
							&& (defectsList.get(j).getT()).equals(app.getT())) {

						flag = false;

						if ((app.getS()).equals(SeverityLevel.CRITICAL)) {
							defectsList.get(j)
									.setCriticalCnt(defectsList.get(j).getCriticalCnt() + app.getCriticalCnt());

						} else if ((app.getS()).equals(SeverityLevel.ERROR)) {
							defectsList.get(j).setErrorCnt(defectsList.get(j).getErrorCnt() + app.getErrorCnt());

						} else {
							defectsList.get(j).setWarningCnt(defectsList.get(j).getWarningCnt() + app.getWarningCnt());

						}
					}

				}
				if (flag)
					defectsList.add(app);

			}
		}
		return defectsList;

	}
}
