package pl.edu.agh.assetory.client;

import pl.edu.agh.assetory.client.service.HardwareService;
import pl.edu.agh.assetory.client.service.SoftwareService;
import pl.edu.agh.assetory.client.service.SystemService;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(new SystemService().getSystemData());
        System.out.println("-------------------------------------------------------------------------------");
        new SoftwareService().getSoftwareData().forEach(
                r -> System.out.println(r.getPublisher() + " " + r.getName() + " " + r.getVersion() + " " + r.getInstallDate()));
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(new HardwareService().getHardwareData());
    }
}