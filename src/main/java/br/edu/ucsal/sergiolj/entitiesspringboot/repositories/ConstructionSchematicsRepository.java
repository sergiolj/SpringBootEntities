package br.edu.ucsal.sergiolj.entitiesspringboot.repositories;

import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionSchematicsRepository extends JpaRepository<ConstructionSet, Long> {
    ConstructionSet findByName(String name);
}
