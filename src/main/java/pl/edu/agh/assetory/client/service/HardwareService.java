package pl.edu.agh.assetory.client.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HardwareService {
    private static final String OUT_FILENAME = "dxdiag.txt";

    public static List<String> USED_RECORDS = Arrays.asList("Machine name", "Machine Id", "System Manufacturer",
            "System Model", "Processor", "Memory", "Card name", "Manufacturer", "Chip type", "DAC type", "Display Memory");

    public Map<String, String> getHardwareData() throws IOException, InterruptedException {
        ProcessBuilder dxdiagProcessBuilder = new ProcessBuilder("cmd.exe", "/c", "dxdiag", "/t", OUT_FILENAME);
        Process dxdiag = dxdiagProcessBuilder.start();
        dxdiag.waitFor();

        BufferedReader reader = new BufferedReader(new FileReader(OUT_FILENAME));
        Map<String, String> results = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splitLine = line.split(":");
            if (splitLine.length > 1 && USED_RECORDS.contains(splitLine[0].trim())) {
                results.put(splitLine[0].trim(), splitLine[1].trim());
            }
        }
        reader.close();
        dxdiag.getOutputStream().close();
        File file = new File(OUT_FILENAME);
        file.delete();
        return results;
    }
}
