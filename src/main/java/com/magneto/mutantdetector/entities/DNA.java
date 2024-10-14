package com.magneto.mutantdetector.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.magneto.mutantdetector.Views.View;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "Dna")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Audited

public class DNA extends Base{

    @JsonView(View.Public.class)
    @Column(name = "DNA")
    private String[] dna;

    @JsonView(View.Internal.class)
    @Column(name = "Es_Mutante")
    private boolean esMutant;
}
