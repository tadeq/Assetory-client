package pl.edu.agh.assetory.client.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HardwareService {
    public void getHardwareData() throws IOException, InterruptedException {
        ProcessBuilder dxdiagProcessBuilder = new ProcessBuilder("cmd.exe", "/c", "dxdiag", "/t", "dxdiag.txt");
        Process dxdiag = dxdiagProcessBuilder.start();
        dxdiag.waitFor();

        BufferedReader reader = new BufferedReader(new FileReader("dxdiag.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        dxdiag.getOutputStream().close();
    }
}
