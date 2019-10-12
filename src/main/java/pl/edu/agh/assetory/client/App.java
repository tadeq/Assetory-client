package pl.edu.agh.assetory.client;

import pl.edu.agh.assetory.client.service.HardwareService;
import pl.edu.agh.assetory.client.service.SoftwareService;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        new SoftwareService().getSoftwareData();
        System.out.println("-------------------------------------------------------------------------------");
        new HardwareService().getHardwareData();
    }
}