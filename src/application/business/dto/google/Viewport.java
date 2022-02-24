package application.business.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Viewport {
    @JsonProperty("northeast")
    Northeast northeast;
    @JsonProperty("southwest")
    Southwest southwest;
}
