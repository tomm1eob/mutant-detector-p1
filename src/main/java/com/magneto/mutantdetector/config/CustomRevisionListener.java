package com.magneto.mutantdetector.config;

import com.magneto.mutantdetector.audit.Revision;
import org.hibernate.envers.RevisionListener;

// Implementa la interfaz RevisionListener para manejar eventos de auditoría.
public class CustomRevisionListener implements RevisionListener {

    // Este metodo se invoca cada vez que se crea una nueva revisión.
    public void newRevision(Object revisionEntity) {
        // Convierte el objeto revisionEntity a una instancia de la clase Revision.
        final Revision revision = (Revision) revisionEntity;
    }
}