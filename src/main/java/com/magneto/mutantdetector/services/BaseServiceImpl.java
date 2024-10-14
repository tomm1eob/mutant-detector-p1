package com.magneto.mutantdetector.services;

import com.magneto.mutantdetector.entities.Base;
import com.magneto.mutantdetector.entities.DNA;
import com.magneto.mutantdetector.repositories.BaseRepository;
import com.magneto.mutantdetector.verifications.DNAverification;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {

    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository){
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findBy(ID id) throws Exception {
        try {
            Optional<E> enityOptional = baseRepository.findById(id);
            return enityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try {
            String[] dna = ((DNA) entity).getDna();

            ((DNA) entity).setEsMutant(comprobarDNA(dna));

            // Guardar la entidad
            entity = baseRepository.save(entity);

            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static boolean comprobarDNA(String[] dna){
        final int LONGITUD_SECUENCIA = 4;
        final int SECUENCIAS_NECESARIAS = 2;
        DNAverification.validateDNA(dna);

        long secuenciasEncontradas = IntStream.range(0, dna.length)
                .boxed() // Convertimos a Stream<Integer>
                .flatMap(fila -> IntStream.range(0, dna[0].length())
                        .mapToObj(columna -> new int[]{fila, columna}))
                .filter(pos -> esParteDeSecuencia(dna, pos[0], pos[1]))
                .count();

        boolean esMutante = secuenciasEncontradas >= SECUENCIAS_NECESARIAS;
        return esMutante;
    }

    private static boolean esParteDeSecuencia(String[] dna, int fila, int columna) {
        return buscarSecuencia(dna, fila, columna, 0, 1) || // Horizontal
                buscarSecuencia(dna, fila, columna, 1, 0) || // Vertical
                buscarSecuencia(dna, fila, columna, 1, 1) || // Diagonal izq -> der
                buscarSecuencia(dna, fila, columna, 1, -1);  // Diagonal der -> izq
    }


    private static boolean buscarSecuencia(String[] dna, int fila, int columna, int incFila, int incColumna) {
        int filas = dna.length;
        int columnas = dna[0].length();
        char caracterInicial = dna[fila].charAt(columna);

        return IntStream.range(1, 4) // LONGITUD_SECUENCIA = 4
                .allMatch(k -> {
                    int nuevaFila = fila + k * incFila;
                    int nuevaColumna = columna + k * incColumna;

                    return nuevaFila < filas &&
                            nuevaColumna >= 0 &&
                            nuevaColumna < columnas &&
                            dna[nuevaFila].charAt(nuevaColumna) == caracterInicial;
                });
    }



    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try {
            if (baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
