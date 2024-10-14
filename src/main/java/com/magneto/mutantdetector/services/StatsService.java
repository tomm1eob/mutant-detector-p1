package com.magneto.mutantdetector.services;

import com.magneto.mutantdetector.dto.StatsDto;
import com.magneto.mutantdetector.repositories.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    @Autowired
    private DNARepository dnaRepository;

    public StatsDto getStats() {
        long countMutantDna = dnaRepository.countByEsMutantTrue();
        long countHumanDna = dnaRepository.countByEsMutantFalse();

        double ratio = countHumanDna > 0 ? (double) countMutantDna / countHumanDna : 0;

        StatsDto stats = new StatsDto();
        stats.setCountMutantDna(countMutantDna);
        stats.setCountHumanDna(countHumanDna);
        stats.setRatio(ratio);

        return stats;
    }
}
