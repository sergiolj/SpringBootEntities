package br.edu.ucsal.sergiolj.entitiesspringboot.catalog;

import br.edu.ucsal.sergiolj.entitiesspringboot.BuildingModel;
import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionProject;
import br.edu.ucsal.sergiolj.entitiesspringboot.Design;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 * Uma das disciplinas que podem ser necessárias para a proposta de intervenção em um edifício.
 * Cada conjunto de esquemas construtivos pode ter apenas um projeto de estrutura.
 * Assim como cada projeto de estrutura deve estar relacionado a um modelo de construção virtual BIM.
 */
@Entity
@DiscriminatorValue("ELECTRIC")
public class Electric extends Design {

    @ManyToOne
    private ConstructionProject constructionProject;

    @OneToOne
    private BuildingModel buildingModel;

    public Electric(){
        super();
    }

    public Electric(String company, String responsibleTechnician, BuildingModel buildingModel, ConstructionProject cs) {
        super(company, responsibleTechnician, buildingModel, cs);
    }

    public Electric(String company, String responsibleTechnician) {
        this(company, responsibleTechnician,null, null);
    }
}
