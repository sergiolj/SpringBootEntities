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
 * Cada edifício pode ter múltiplos esquemas construtivos associados e cada esquema pode ter múltiplos
 * projetos (designs) associados.
 */
@Data
@Entity
public class ConstructionProject {
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
    @JoinColumn(name="building_id", nullable = false)
    private Building building;

    //Quando a anotação termina em Many (@OneToMany, @ManyToMany) o padrão JPA é LAZY,
    //onde os registros da lista não são colocados previamente em memória e sim apenas quando explicitamente
    //solicitados com algum conteúdo da lista como size(), get(), add()
    @OneToMany(mappedBy = "constructionProject", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Design> designList = new ArrayList<>();

    public ConstructionProject(String name, String description, ProjectType projectType,
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

    public ConstructionProject() {

    }
    public void addDesign(Design design){
        if(design!=null){
            this.designList.add(design);
            design.setConstructionProject(this);
        }
    }
}
