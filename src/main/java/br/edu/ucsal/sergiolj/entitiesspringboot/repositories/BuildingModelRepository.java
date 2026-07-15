package br.edu.ucsal.sergiolj.entitiesspringboot.repositories;

import br.edu.ucsal.sergiolj.entitiesspringboot.BuildingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingModelRepository extends JpaRepository<BuildingModel, Long> {
}
