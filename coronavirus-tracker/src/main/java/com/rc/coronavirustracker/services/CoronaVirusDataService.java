package com.rc.coronavirustracker.services;

import com.rc.coronavirustracker.services.models.GlobalStats;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String CORONA_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<GlobalStats> getAllStatesData() {
        return allStatesData;
    }

    private List<GlobalStats> allStatesData = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<GlobalStats> globalStats = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(CORONA_VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        for (CSVRecord record : CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader)) {
            for (String field : record) {
                GlobalStats stateData = new GlobalStats();
                stateData.setState(record.get("Province/State"));
                stateData.setCountry(record.get("Country/Region"));
                long latestTotalCases = Long.parseLong(record.get(record.size() - 1));
                long prvDayCases = Long.parseLong(record.get(record.size() - 2));
                stateData.setLatestTotalCases(latestTotalCases);
                stateData.setDiffFrmPrvDay(latestTotalCases - prvDayCases);
                globalStats.add(stateData);
            }
            allStatesData = globalStats;
        }
    }
}
