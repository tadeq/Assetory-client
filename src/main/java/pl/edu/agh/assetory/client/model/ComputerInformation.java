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
    private String dateTime;
    private Map<String, String> system;
    private Map<String, String> hardware;
    private List<SoftwareRecord> software;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ComputerInformation(String computerId, Map<String, String> systemInfo, Map<String, String> hardwareInfo, List<SoftwareRecord> softwareInfo) {
        LocalDateTime now = LocalDateTime.now();
        this.id = computerId + now.getYear() + now.getMonth() + now.getDayOfMonth() + now.getHour() + now.getMinute();
        this.computerId = computerId;
        this.dateTime = now.format(dateTimeFormatter);
        this.system = systemInfo;
        this.hardware = hardwareInfo;
        this.software = softwareInfo;
    }
}
