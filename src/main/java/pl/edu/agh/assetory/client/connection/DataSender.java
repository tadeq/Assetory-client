package pl.edu.agh.assetory.client.connection;

import com.google.gson.Gson;
import pl.edu.agh.assetory.client.model.ComputerInformation;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DataSender {
    private static final String URL_INFO = "http://localhost:5444/info";
    private static final Gson gson = new Gson();

    public void sendData(ComputerInformation information) throws IOException {
        URL url = new URL(URL_INFO);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        String json = gson.toJson(information);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(json);
        writer.close();
        connection.getResponseMessage();
        connection.disconnect();
    }
}
