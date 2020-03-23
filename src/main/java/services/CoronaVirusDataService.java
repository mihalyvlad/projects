package services;

import models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static java.util.Collections.sort;

@Service

public class CoronaVirusDataService  {
    public static String URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private List<LocationStats> allStats=new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
   @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
       List<LocationStats> temporaryStats = new ArrayList<>();
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
       HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
       StringReader csvBodyReader = new StringReader(httpResponse.body());
       Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
       for (CSVRecord record : records) {
           LocationStats locationStats = new LocationStats();
           locationStats.setState(record.get("Province/State"));
           locationStats.setCountry(record.get("Country/Region"));
           int lDay=Integer.parseInt(record.get(record.size() - 1));
           int prevDay=Integer.parseInt(record.get(record.size() - 2));
           locationStats.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
           locationStats.setLastDayCases(lDay-prevDay);
           System.out.println(locationStats.toString());
           temporaryStats.add((locationStats));

       }
this.allStats=temporaryStats;
   }


}
