package com.example.rentme_backend_morgan.business.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Candidates {

    @JsonProperty("formatted_address")
    public String formatted_address;
    @JsonProperty("geometry")
    public Geometry geometry;
    @JsonProperty("name")
    public String name;
    @JsonProperty("types")
    public String[] types;
}
