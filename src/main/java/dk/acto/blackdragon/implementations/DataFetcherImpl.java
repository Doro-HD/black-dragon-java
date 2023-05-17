package dk.acto.blackdragon.implementations;

import java.util.List;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import dk.acto.blackdragon.service.DataFetcher;

public class DataFetcherImpl implements DataFetcher {
    
    public String fetchData(URL url) {
        String output = "";

        try {
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
            );

            List<String> lines = reader.lines()
                .map(line -> line)
                .toList();
            output = String.join("\n", lines);
        } catch (IOException e) {

            return output;
        }

        return output;
    }
}