package pl.edu.agh.assetory.client.service;

import java.util.HashMap;
import java.util.Map;

public class SystemService {
    private static final String OS_NAME_PROPERTY = "os.name";
    private static final String OS_VERSION_PROPERTY = "os.version";
    private static final String OS_ARCHITECTURE_PROPERTY = "os.arch";

    public Map<String, String> getSystemData() {
        Map<String, String> results = new HashMap<>();
        results.put("OS name", System.getProperty(OS_NAME_PROPERTY));
        results.put("OS version", System.getProperty(OS_VERSION_PROPERTY));
        results.put("OS architecture", System.getProperty(OS_ARCHITECTURE_PROPERTY));
        return results;
    }
}
