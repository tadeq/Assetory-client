package pl.edu.agh.assetory.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SoftwareRecord {
    private String name;
    private String version;
    private String installDate;
}
