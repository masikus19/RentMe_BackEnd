package application.business.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Geometry {

    @JsonProperty("location")
    public Location location;
    @JsonProperty("viewport")
    public Viewport viewport;
}
