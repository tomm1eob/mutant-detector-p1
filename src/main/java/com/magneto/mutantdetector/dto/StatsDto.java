package com.magneto.mutantdetector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatsDto {
    private long countMutantDna;
    private long countHumanDna;
    private double ratio;

}
