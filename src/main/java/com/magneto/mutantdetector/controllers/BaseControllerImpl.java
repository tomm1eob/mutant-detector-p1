package com.magneto.mutantdetector.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.magneto.mutantdetector.Views.View;
import com.magneto.mutantdetector.entities.Base;
import com.magneto.mutantdetector.entities.DNA;
import com.magneto.mutantdetector.services.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {

    @Autowired
    protected S servicio;


    @JsonView(View.Internal.class)
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @JsonView(View.Internal.class)
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findBy(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @JsonView(View.Public.class)
    @PostMapping("/mutant")
    public ResponseEntity<?> save(@RequestBody E entity) {
        try {
            DNA savedEntity = (DNA) servicio.save(entity);

            // Comprobar si es mutante
            if (savedEntity.isEsMutant()) {
                return ResponseEntity.ok(savedEntity);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"No es un mutante\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde o revise el DNA enviado\"}");
        }
    }


    @JsonView(View.Internal.class)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}
