package br.edu.ucsal.sergiolj.entitiesspringboot.repositories;

import br.edu.ucsal.sergiolj.entitiesspringboot.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
