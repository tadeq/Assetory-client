package pl.edu.agh.assetory.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode
public class SoftwareRecord {
    private String name;
    private String version;
    private String installDate;
}
