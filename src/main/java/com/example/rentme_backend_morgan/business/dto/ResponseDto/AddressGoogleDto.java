package com.example.rentme_backend_morgan.business.dto.ResponseDto;

import com.example.rentme_backend_morgan.business.dto.ResponseDto.Candidates;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressGoogleDto {

    public Candidates[] candidates;
    public String status;
}

