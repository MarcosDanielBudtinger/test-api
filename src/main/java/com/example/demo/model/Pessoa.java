package com.example.demo.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@EqualsAndHashCode
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotNull
    private String nome;

    @Embedded
    @Getter
    @Setter
    private Endereco endereco;

    @Getter
    @Setter
    @NotNull
    private boolean ativo;

    @JsonIgnore
    @Transient
    public boolean isInativo() {
        return !this.ativo;
    }
}
