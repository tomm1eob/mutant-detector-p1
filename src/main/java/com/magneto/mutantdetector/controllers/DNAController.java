package com.magneto.mutantdetector.controllers;

import com.magneto.mutantdetector.entities.DNA;
import com.magneto.mutantdetector.services.DNAServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DNAController extends BaseControllerImpl<DNA, DNAServiceImpl>{
}
