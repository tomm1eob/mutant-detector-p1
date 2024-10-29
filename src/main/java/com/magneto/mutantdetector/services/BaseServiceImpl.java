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

    // Repositorio base para manejar operaciones de persistencia
    protected BaseRepository<E, ID> baseRepository;

    // Constructor que recibe el repositorio base
    public BaseServiceImpl(BaseRepository<E, ID> baseRepository){
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        // Busca y devuelve todas las entidades
        try {
            List<E> entities = baseRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findBy(ID id) throws Exception {
        // Busca y devuelve una entidad por su ID
        try {
            Optional<E> enityOptional = baseRepository.findById(id);
            return enityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        // Metodo para guardar una entidad en el repositorio
        try {
            String[] dna = ((DNA) entity).getDna(); // Obtiene el arreglo de ADN

            // Verifica si el ADN es mutante y establece el resultado
            ((DNA) entity).setEsMutant(comprobarDNA(dna));

            // Guarda la entidad en el repositorio
            entity = baseRepository.save(entity);

            return entity; // Retorna la entidad guardada
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static boolean comprobarDNA(String[] dna) {
        // Verifica si el ADN cumple con las características de un mutante
        final int LONGITUD_SECUENCIA = 4; // Longitud mínima de la secuencia
        final int SECUENCIAS_NECESARIAS = 2; // Secuencias necesarias para ser considerado mutante
        DNAverification.validateDNA(dna); // Valida el ADN

        // Cuenta cuántas secuencias mutantes se han encontrado
        long secuenciasEncontradas = IntStream.range(0, dna.length)
                .boxed() // Convierte a Stream<Integer>
                .flatMap(fila -> IntStream.range(0, dna[0].length())
                        .mapToObj(columna -> new int[]{fila, columna}))
                .filter(pos -> esParteDeSecuencia(dna, pos[0], pos[1])) // Filtra las posiciones que son parte de una secuencia
                .count();

        // Retorna true si se encontraron suficientes secuencias mutantes
        boolean esMutante = secuenciasEncontradas >= SECUENCIAS_NECESARIAS;
        return esMutante;
    }

    private static boolean esParteDeSecuencia(String[] dna, int fila, int columna) {
        // Verifica si hay una secuencia mutante en la posición dada
        return buscarSecuencia(dna, fila, columna, 0, 1) || // Horizontal
                buscarSecuencia(dna, fila, columna, 1, 0) || // Vertical
                buscarSecuencia(dna, fila, columna, 1, 1) || // Diagonal izquierda a derecha
                buscarSecuencia(dna, fila, columna, 1, -1);  // Diagonal derecha a izquierda
    }

    private static boolean buscarSecuencia(String[] dna, int fila, int columna, int incFila, int incColumna) {
        // Busca una secuencia de ADN a partir de la posición inicial en la dirección especificada
        int filas = dna.length;
        int columnas = dna[0].length();
        char caracterInicial = dna[fila].charAt(columna); // Caracter inicial para la búsqueda

        // Verifica que los siguientes caracteres en la secuencia coincidan con el inicial
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
        // Elimina una entidad por su ID
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
                return true; // Retorna true si la eliminación fue exitosa
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
