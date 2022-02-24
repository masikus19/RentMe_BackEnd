package application.business.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Northeast {
    @JsonProperty("lat")
    Double lat;
    @JsonProperty("lng")
    Double lng;
}
