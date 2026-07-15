package br.edu.ucsal.sergiolj.entitiesspringboot.catalog;

import br.edu.ucsal.sergiolj.entitiesspringboot.BuildingModel;
import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionSet;
import br.edu.ucsal.sergiolj.entitiesspringboot.Design;
import jakarta.persistence.*;

/**
 * Uma das disciplinas que podem ser necessárias para a proposta de intervenção em um edifício.
 * Cada conjunto de esquemas construtivos pode ter apenas um projeto de estrutura.
 * Assim como cada projeto de estrutura deve estar relacionado a um modelo de construção virtual BIM.
 */
@Entity
@DiscriminatorValue("STRUCTURE")
public class Structure extends Design {

    @ManyToOne
    private ConstructionSet constructionSet;

    @OneToOne
    private BuildingModel buildingModel;

    public Structure(){
        super();
    }

    public Structure(String company, String responsibleTechnician, ConstructionSet cs) {
        super(company, responsibleTechnician, cs);
    }
}
