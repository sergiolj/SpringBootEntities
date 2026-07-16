package br.edu.ucsal.sergiolj.entitiesspringboot.catalog;

import br.edu.ucsal.sergiolj.entitiesspringboot.Building;
import br.edu.ucsal.sergiolj.entitiesspringboot.BuildingModel;
import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionSet;
import br.edu.ucsal.sergiolj.entitiesspringboot.Design;
import jakarta.persistence.*;

/**
 * Uma das disciplinas que podem ser necessárias para a proposta de intervenção em um edifício.
 * Cada conjunto de esquemas construtivos pode ter apenas um projeto de arquitetura.
 * Assim como cada projeto de arquitetura deve estar relacionado a um modelo de construção virtual BIM.
 */
@Entity
@DiscriminatorValue("ARCHITECTURE")
public class Architecture extends Design {

    @ManyToOne
    private ConstructionSet constructionSet;

    @OneToOne
    private BuildingModel buildingModel;


    public Architecture(Building building, String company, String responsibleTechnician, ConstructionSet cs) {
        super(building, company, responsibleTechnician, cs);
    }

    public Architecture(Building building, String company, String responsibleTechnician) {
        this(building, company, responsibleTechnician, null);
    }

    public Architecture() {
    }
}
