package com.java.covidtracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.java.covidtracker.models.LocationStats;

@Service
//normally we should not save the state of object in service class but for this project according to need we did.
public class CovidDataServices {
	//url from github free source -- department of novel cororna virus cases provided by JHUCSSE John hofkins university - center for systems science and engineering
	private static String virusDataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> allStats = new ArrayList<>();
	

	public List<LocationStats> getAllStats() {
		return allStats;
	}


	@PostConstruct     //to tell system after service is created pls execute this method
	@Scheduled(cron = "* * 1 * * *")       //for updating first hr of every day (* * 1 for scheduling once every day)
	public void fetchVirusData( ) throws IOException, InterruptedException {
		
		List<LocationStats> newStats = new ArrayList<>();   //for concurrancy reason
		
		HttpClient client = HttpClient.newHttpClient(); //creating client through which it is sending req to server
		
		HttpRequest request= HttpRequest.newBuilder()  //making the req using string url
				
		.uri(URI.create(virusDataUrl))
		
		.build();
		
		 HttpResponse<String> httpResponse =  client.send(request, HttpResponse.BodyHandlers.ofString());//sending req using http client , converting the response in string
		
		//System.out.println(httpResponse.body());//printing the string response
		 
		 StringReader csvBodyReader = new StringReader(httpResponse.body()); //reading response body using string reader
		 
		 
		   Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		   
	        for (CSVRecord record : records) {
	        	LocationStats locationStat = new LocationStats();
	        	
			    locationStat.setState( record.get("Province/State"));
			    locationStat.setLat(record.get("Lat"));
			    locationStat.setLongi(record.get("Long"));
			    locationStat.setCountry(record.get("Country/Region"));
			    locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
			     System.out.println(locationStat);
			     System.out.println("======");
			     int latestCases = Integer.parseInt(record.get(record.size() - 1));
			     int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
			     locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
//			     locationStat.setDateTime(LocalDateTime.now());
			     newStats.add(locationStat);
			     
			     
	        }
	        this.allStats = newStats;
		// Reader in = new FileReader("path/to/file.csv"); //
//		 Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);//parsing response
//		 for (CSVRecord record : records) {
//		     String state = record.get("Province/State");
//		     System.out.println(state);
//		     String customerNo = record.get("CustomerNo");
//		     String name = record.get("Name");
//		    // System.out.println();
//		 }
		 
		 
		 
		 
	}

}
