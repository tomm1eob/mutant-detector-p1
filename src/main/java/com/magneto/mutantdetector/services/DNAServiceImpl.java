package com.magneto.mutantdetector.services;

import com.magneto.mutantdetector.entities.DNA;
import com.magneto.mutantdetector.repositories.BaseRepository;
import com.magneto.mutantdetector.repositories.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DNAServiceImpl extends BaseServiceImpl<DNA, Long> implements DNAService {

    @Autowired
    private DNARepository dnaRepository;

    public DNAServiceImpl(BaseRepository<DNA,Long> baseRepository){
        super(baseRepository);
    }

}
