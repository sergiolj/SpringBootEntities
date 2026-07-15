package br.edu.ucsal.sergiolj.entitiesspringboot;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Um projeto representa uma das disciplinas necessárias para executar uma intervenção num edifício.
 * Um ou vários projetos são necessários para se propor intervenções em um edifício e compor um conjunto
 * de esquemas construtivos.
 */
@Data
@Entity
@Table(name = "design",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"construction_set_id", "discipline_type"})})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discipline_type", discriminatorType = DiscriminatorType.STRING)

public abstract class Design {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String responsibleTechnician;

    @ManyToOne
    @JoinColumn
    private ConstructionSet constructionSet;

    public Design(String company, String responsibleTechnician, ConstructionSet cs) {
        this.company = company;
        this.responsibleTechnician = responsibleTechnician;
        this.constructionSet = cs;
    }

    public Design() {

    }
}
