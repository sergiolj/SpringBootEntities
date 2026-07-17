package br.edu.ucsal.sergiolj.entitiesspringboot;

import br.edu.ucsal.sergiolj.entitiesspringboot.util.LevelOfDevelopment;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.sql.ast.tree.expression.JsonObjectAggUniqueKeysBehavior;

/**
 * Modelo digital do edifício criado especificamente para uma disciplina de projeto.
 *
 */
@Data
@Entity
public class BuildingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String software;
    private LevelOfDevelopment levelOfDetail;
    private String modelFormat;

    @OneToOne
    @JoinColumn(name = "design_id")
    private Design design;

    public BuildingModel(Design design, String software, LevelOfDevelopment levelOfDetail, String modelFormat) {
        try{
            this.design = design;
            design.setBuildingModel(this);
            this.software = software;
            this.levelOfDetail = levelOfDetail;
            this.modelFormat = modelFormat;
        }catch (Exception e){
            System.out.println("Erro ao criar modelo, ou vincular a um projeto (Design)");
        }


    }

    public BuildingModel() {

    }
}