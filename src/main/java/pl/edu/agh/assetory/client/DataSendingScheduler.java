package pl.edu.agh.assetory.client;

import pl.edu.agh.assetory.client.connection.DataSender;
import pl.edu.agh.assetory.client.model.ComputerInformation;
import pl.edu.agh.assetory.client.service.HardwareService;
import pl.edu.agh.assetory.client.service.SoftwareService;
import pl.edu.agh.assetory.client.service.SystemService;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DataSendingScheduler {
    private SystemService systemService = new SystemService();
    private HardwareService hardwareService = new HardwareService();
    private SoftwareService softwareService = new SoftwareService();
    private DataSender sender = new DataSender();
    private String computerId;

    public DataSendingScheduler(String computerId) {
        this.computerId = computerId;
    }

    public void scheduleDataSending() {
        System.out.println("Starting loop");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("a")){
                ComputerInformation information = fetchData();
                sendData(information);
            }
        }

        //TODO proper implementation below, above for test purposes
//        TimerTask dataSendTask = new TimerTask() {
//            @Override
//            public void run() {
//                Optional<ComputerInformation> computerInformation = Optional.ofNullable(fetchData());
//                computerInformation.ifPresent(information -> sendData(information));
//            }
//        };
//        Timer timer = new Timer("Data sending timer");
//        Calendar executionTime = Calendar.getInstance();
//        executionTime.set(Calendar.HOUR_OF_DAY, 9);
//        executionTime.set(Calendar.MINUTE, 31);
//        executionTime.set(Calendar.SECOND, 0);
//        timer.schedule(dataSendTask, executionTime.getTime(), TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES));
    }

    private ComputerInformation fetchData() {
        ComputerInformation computerInformation = null;
        try {
            computerInformation = new ComputerInformation(computerId,
                    systemService.getSystemData(),
                    hardwareService.getHardwareData(),
                    softwareService.getSoftwareData());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return computerInformation;
    }

    private void sendData(ComputerInformation computerInformation) {
        System.out.println("Start sending data");
        try {
            if (computerInformation != null) {
                sender.sendData(computerInformation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data sent");
    }

}
