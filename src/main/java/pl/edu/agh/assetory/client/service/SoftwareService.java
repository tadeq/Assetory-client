package pl.edu.agh.assetory.client.service;

import pl.edu.agh.assetory.client.model.SoftwareRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SoftwareService {
    public List<SoftwareRecord> getSoftwareData() throws IOException {
        Process powershellProcess = Runtime.getRuntime().exec("powershell Get-ItemProperty HKLM:\\Software\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\* | Select-Object DisplayName, DisplayVersion, Publisher, InstallDate | Format-list");
        BufferedReader reader = new BufferedReader(new InputStreamReader(powershellProcess.getInputStream()));
        List<SoftwareRecord> records = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("DisplayName")) {
                SoftwareRecord record = new SoftwareRecord();
                record.setName(line.split(":")[1].trim());
                String[] splitLine = reader.readLine().split(":");
                if (splitLine.length > 1) {
                    record.setVersion(splitLine[1].trim());
                }
                splitLine = reader.readLine().split(":");
                if (splitLine.length > 1) {
                    record.setPublisher(splitLine[1].trim());
                }
                splitLine = reader.readLine().split(":");
                if (splitLine.length > 1) {
                    record.setInstallDate(splitLine[1].trim());
                }
                records.add(record);
            }
        }
        reader.close();
        powershellProcess.getOutputStream().close();

        return records;
    }
}
