package com.openclassrooms.safetyNet.DTO;

import lombok.Data;

@Data
public class FireDTO {

    private Iterable<PersonInfoDTO> personInfoDTOList;
    private Integer stationNumber;

}
