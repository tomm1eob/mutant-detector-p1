package com.magneto.mutantdetector.verifications;

public class DNAverification {
    public static boolean validateDNA(String[] dna) throws IllegalArgumentException {
        // 1. Verificar si el array es null
        if (dna == null) {
            throw new IllegalArgumentException("El ADN es null.");
        }

        // 2. Verificar si el array está vacío
        if (dna.length == 0) {
            throw new IllegalArgumentException("El array de ADN está vacío.");
        }

        // 3. Verificar si el array es cuadrado (NxN)
        int n = dna.length;
        for (String row : dna) {
            if (row == null) {
                throw new IllegalArgumentException("El ADN contiene filas null.");
            }

            // Verifica que todas las filas tengan la misma longitud (NxN)
            if (row.length() != n) {
                throw new IllegalArgumentException("El ADN no es cuadrado NxN.");
            }

            // 4. Verificar que solo contenga los caracteres permitidos (A, T, C, G)
            if (!row.matches("[ATCG]+")) {
                throw new IllegalArgumentException("El ADN contiene caracteres no válidos.");
            }
        }
        return true;
    }
}
