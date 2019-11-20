package pl.edu.agh.assetory.client.service;

import pl.edu.agh.assetory.client.model.SoftwareRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoftwareService {
    public Map<String, List<SoftwareRecord>> getSoftwareData() throws IOException {
        Process powershellProcess = Runtime.getRuntime().exec("powershell Get-ItemProperty HKLM:\\Software\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\* | Select-Object DisplayName, DisplayVersion, Publisher, InstallDate | Format-list");
        BufferedReader reader = new BufferedReader(new InputStreamReader(powershellProcess.getInputStream()));
        Map<String, List<SoftwareRecord>> publishersSoftware = new HashMap<>();
        String line;
        String publisher;
        while ((line = reader.readLine()) != null) {
            publisher = "";
            if (line.startsWith("DisplayName")) {
                SoftwareRecord record = new SoftwareRecord();
                record.setName(line.split(":")[1].trim());
                String[] splitLine = reader.readLine().split(":");
                if (splitLine.length > 1) {
                    record.setVersion(splitLine[1].trim());
                }
                splitLine = reader.readLine().split(":");
                if (splitLine.length > 1) {
                    publisher = splitLine[1].trim();
                }
                splitLine = reader.readLine().split(":");
                if (splitLine.length > 1) {
                    String date = splitLine[1].trim();
                    if (date.length() >= 8) {
                        StringBuilder dateBuilder = new StringBuilder(date);
                        dateBuilder.insert(4, "-");
                        dateBuilder.insert(7, "-");
                        date = dateBuilder.toString();
                    }
                    record.setInstallDate(date);
                }
                if (!publisher.equals("")) {
                    if (publishersSoftware.containsKey(publisher)) {
                        List<SoftwareRecord> softwareRecords = publishersSoftware.get(publisher);
                        if (!softwareRecords.contains(record)) {
                            softwareRecords.add(record);
                        }
                    } else {
                        List<SoftwareRecord> softwareRecords = new ArrayList<>();
                        softwareRecords.add(record);
                        publishersSoftware.put(publisher, softwareRecords);
                    }
                }
            }
        }
        reader.close();
        powershellProcess.getOutputStream().close();

        return publishersSoftware;
    }
}
