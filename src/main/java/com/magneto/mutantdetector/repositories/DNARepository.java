package com.magneto.mutantdetector.repositories;

import com.magneto.mutantdetector.entities.DNA;

public interface DNARepository extends BaseRepository<DNA, Long> {
    long countByEsMutantTrue();   // Cuenta los mutantes
    long countByEsMutantFalse();  // Cuenta los no mutantes
}
