package com.magneto.mutantdetector.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.magneto.mutantdetector.Views.View;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Base implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Internal.class)
    protected Long id;
}
