package com.magneto.mutantdetector.audit;

import com.magneto.mutantdetector.config.CustomRevisionListener;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;
import java.util.Date;

// Indica que esta clase representa una entidad de base de datos.
@Entity
// Especifica el nombre de la tabla en la base de datos donde se almacenarán las revisiones.
@Table(name = "REVISION_INFO")
// Asocia la clase con un listener de revisiones personalizado (CustomRevisionListener).
@RevisionEntity(CustomRevisionListener.class)
// Lombok se utiliza para generar métodos automáticamente (getters, setters, equals, hashCode, toString).
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Revision implements Serializable {

    // Definición de una versión serial para la clase, útil para la serialización.
    private static final long serialVersionID = 1L;

    // Marca esta columna como la clave primaria (ID) de la tabla y especifica el uso de una secuencia para la generación automática de IDs.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_seq")
    @SequenceGenerator(name = "revision_seq", sequenceName = "revision_sequence", allocationSize = 1)
    // Anotación que indica que este campo contiene el número de revisión.
    @RevisionNumber
    private int id;

    // Define la columna que almacenará la fecha de la revisión.
    @Column(name = "REVISION_DATE")
    // Indica que este campo debe ser almacenado como un TIMESTAMP en la base de datos.
    @Temporal(TemporalType.TIMESTAMP)
    // Anotación que indica que este campo contiene la marca de tiempo de la revisión.
    @RevisionTimestamp
    private Date date;
}