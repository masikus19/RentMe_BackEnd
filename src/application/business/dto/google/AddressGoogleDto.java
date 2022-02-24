package application.business.dto.google;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressGoogleDto {

    public Candidates[] candidates;
    public String status;
}