package br.edu.ucsal.sergiolj.entitiesspringboot;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade Edificação deve ter vínculos com o conjunto de projetos construtivos necessários ao tipo de intervenção
 * vinculada ao edifício.
 * Cada Edificação pode ter mais de um conjunto de projetos construtivos ao longo da sua vida útil.
 * Esses são os projetos originais de construção e projetos de reformas ou ampliações.
 */
@Data
@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    public Building(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @OneToMany (mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConstructionProject> constructionProjectList = new ArrayList<>();


    public Building() {

    }

    public void addConstructionProject(ConstructionProject cs){
        this.constructionProjectList.add(cs);
        cs.setBuilding(this);
    }
}
