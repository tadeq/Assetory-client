package pl.edu.agh.assetory.client.service;

import pl.edu.agh.assetory.client.model.SoftwareRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SoftwareService {
    private static final String OS_NAME_PROPERTY = "os.name";
    private static final String OS_VERSION_PROPERTY = "os.version";
    private static final String OS_ARCHITECTURE_PROPERTY = "os.arch";

    public void getSoftwareData() throws IOException {
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

        records.forEach(r -> System.out.println(r.getPublisher() + " " + r.getName() + " " + r.getVersion() + " " + r.getInstallDate()));

        System.out.println("Name of the OS: " + System.getProperty(OS_NAME_PROPERTY));
        System.out.println("Version of the OS: " + System.getProperty(OS_VERSION_PROPERTY));
        System.out.println("Architecture of THe OS: " + System.getProperty(OS_ARCHITECTURE_PROPERTY));
    }
}
