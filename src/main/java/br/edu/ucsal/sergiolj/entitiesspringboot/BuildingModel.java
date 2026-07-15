package br.edu.ucsal.sergiolj.entitiesspringboot;

import br.edu.ucsal.sergiolj.entitiesspringboot.util.LOD;
import jakarta.persistence.*;
import lombok.Data;

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
    private LOD levelOfDetail;
    private String modelFormat;

    @OneToOne
    private Design design;

}