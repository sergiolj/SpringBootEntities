package br.edu.ucsal.sergiolj.entitiesspringboot;

import br.edu.ucsal.sergiolj.entitiesspringboot.util.DesignStatus;
import br.edu.ucsal.sergiolj.entitiesspringboot.util.ProjectType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Um Conjunto de esquemas construtivos representa um tipo de intervenção proposta para o edifício.
 * Ele contém plantas de projetos de diversas disciplinas.
 * Cada edifício pode ter muitos esquemas construtivos associados.
 */
@Data
@Entity
public class ConstructionSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProjectType projectType;
    private DesignStatus projectStatus;

    private String name;
    private String description;

    private LocalDate startedDate;
    private LocalDate concludedDate;

    @ManyToOne
    private Building building;

    @OneToMany(mappedBy = "constructionSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Design> designList = new ArrayList<>();

    public ConstructionSet(String name, String description, ProjectType projectType,
                           DesignStatus projectStatus, LocalDate startedDate,
                           LocalDate concludedDate, Building building) {
        this.name = name;
        this.description = description;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
        this.startedDate = startedDate;
        this.concludedDate = concludedDate;
        this.building = building;
    }

    public ConstructionSet() {

    }
}
