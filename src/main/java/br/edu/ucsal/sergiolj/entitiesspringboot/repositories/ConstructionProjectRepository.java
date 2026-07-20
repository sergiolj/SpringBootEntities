package br.edu.ucsal.sergiolj.entitiesspringboot.repositories;

import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConstructionProjectRepository extends JpaRepository<ConstructionProject, Long> {
    ConstructionProject findByName(String name);
    List<ConstructionProject> findByBuildingId(Long buildingId);

    //@Query("SELECT cp FROM ConstructionProject cp WHERE cp.building.id = :bId")
    //List<ConstructionProject> findByBuildingId(@Param("bId") Long buildingId);
}
