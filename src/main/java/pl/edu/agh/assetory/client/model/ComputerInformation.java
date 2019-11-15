package pl.edu.agh.assetory.client.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Getter
public class ComputerInformation {
    private String id;
    private String computerId;
    private String date;
    private String time;
    private Map<String, String> system;
    private Map<String, String> hardware;
    private Map<String, List<SoftwareRecord>> software;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ComputerInformation(String computerId, Map<String, String> systemInfo, Map<String, String> hardwareInfo, Map<String, List<SoftwareRecord>> softwareInfo) {
        LocalDateTime now = LocalDateTime.now();
        this.id = computerId + now.getYear() + now.getMonth() + now.getDayOfMonth() + now.getHour() + now.getMinute();
        this.computerId = computerId;
        String[] dateTime = now.format(dateTimeFormatter).split(" ");
        this.date = dateTime[0];
        this.time = dateTime[1];
        this.system = systemInfo;
        this.hardware = hardwareInfo;
        this.software = softwareInfo;
    }
}
