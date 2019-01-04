package com.example.SystemDatabase;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.example.SystemDatabase.domain.Application;
import com.example.SystemDatabase.domain.Defect;
import com.example.SystemDatabase.domain.DefectInstance;
import com.example.SystemDatabase.log.App;
import com.example.SystemDatabase.log.LogAnalysis;
import com.example.SystemDatabase.log.Severity;
import com.example.SystemDatabase.repository.AppRepository;
import com.example.SystemDatabase.repository.DefectInstanceRepository;
import com.example.SystemDatabase.repository.DefectsRepository;

@SpringBootApplication
public class SystemDatabaseApplication {
	@Autowired
	private AppRepository repAppTable;
	@Autowired
	private DefectInstanceRepository repDefInstanceTable;
	@Autowired
	private DefectsRepository repDefTable;

	static ArrayList<App> list = new ArrayList<>();
	static ArrayList<App> defectsList = new ArrayList<>();
	static Severity severity = new Severity();
	
	public static void main(String[] args) {
		
		SpringApplication.run(SystemDatabaseApplication.class, args);
		
		

	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			ArrayList<App> List = getErrorLogs();
			repAppTable.deleteAll();
			repDefInstanceTable.deleteAll();
			repDefTable.deleteAll();
			for(App b : List) {
				Application app = new Application(b.getName(),(b.getT()).name());
				repAppTable.save(app);
				
				Defect defect = new Defect((b.getT()).name(), b.getErrorNum(),"this is a temp solution");
				repDefTable.save(defect);
				
				DefectInstance defectIns = new DefectInstance(app, defect);
				repDefInstanceTable.save(defectIns);
				
				}
		};

	}
	
	public static ArrayList<App> getErrorLogs(){
		LogAnalysis log = new LogAnalysis();
		/*ArrayList<App> list = new ArrayList<>();
		ArrayList<App> defectsList = new ArrayList<>();
		Severity severity = new Severity();*/

		try {
			
			String fileName = "C:\\Users\\Rental-Media\\eclipse-workspace\\Log\\Logs\\CMServer.20170924_1557.log";
			String output = "C:\\Users\\Rental-Media\\eclipse-workspace\\Log\\Report.txt";

			list = log.searchFile(fileName, "Caused by");
			
			/*System.out.println("------------Log Description------------\n");
			for (int i = 0; i < list.size(); i++) {
				System.out.println("App Name:" + list.get(i).getName() + "  Error Type:" + list.get(i).getT()
						+ "  Error Code:" + list.get(i).getErrorNum() + "  Number Of Errors:" + list.get(i).getCount());
			}*/

			log.writeReport(output, list);

			//severity = log.defectsBySeverity(fileName);
			
			/*System.out.println("\n-------------Defects By Severity-------------\n");
			System.out.println("Number Of Defects With Critical Severity is:" + severity.getCriticalCnt());
			System.out.println("Number Of Defects With Error Severity is:" + severity.getErrorCnt());
			System.out.println("Number Of Defects With Warning Severity is:" + severity.getWarningCnt());*/
			
			//defectsList = log.defectsByApp(fileName);
			
			/*System.out.println("\n-------------Defects By App-------------\n");
			for (int i = 0; i < defectsList.size(); i++) {
				System.out.println(defectsList.get(i).getName() + " " + defectsList.get(i).getT()
						+ " - Number Of Defects With Critical Severity is:" + defectsList.get(i).getCriticalCnt());
				System.out.println(defectsList.get(i).getName() + " " + defectsList.get(i).getT()
						+ " - Number Of Defects With Error Severity is:" + defectsList.get(i).getErrorCnt());
				System.out.println(defectsList.get(i).getName() + " " + defectsList.get(i).getT()
						+ " - Number Of Defects With Warning Severity is:" + defectsList.get(i).getWarningCnt());
				System.out.println("");
			}*/

		} catch (Exception e) {

			System.out.println(e);
		}
		return list;
		
	}
	
	

}
