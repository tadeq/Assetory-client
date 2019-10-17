package pl.edu.agh.assetory.client;

import pl.edu.agh.assetory.client.connection.DataSender;
import pl.edu.agh.assetory.client.model.ComputerInformation;
import pl.edu.agh.assetory.client.service.HardwareService;
import pl.edu.agh.assetory.client.service.SoftwareService;
import pl.edu.agh.assetory.client.service.SystemService;

import java.io.*;
import java.util.Scanner;

public class App {
    private static final String COMPUTER_ID = "testId";

    public static void main(String[] args) throws IOException, InterruptedException {
        SystemService systemService = new SystemService();
        HardwareService hardwareService = new HardwareService();
        SoftwareService softwareService = new SoftwareService();
        Scanner scanner = new Scanner(System.in);
        DataSender sender = new DataSender();
        System.out.println("Starting loop");
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("a")) {
                System.out.println("Start retrieving data");
                ComputerInformation computerInformation = new ComputerInformation(COMPUTER_ID,
                        systemService.getSystemData(),
                        hardwareService.getHardwareData(),
                        softwareService.getSoftwareData());
                System.out.println("Start sending data");
                sender.sendData(computerInformation);
                System.out.println("Data sent");
            }
        }
    }
}