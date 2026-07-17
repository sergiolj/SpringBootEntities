package br.edu.ucsal.sergiolj.entitiesspringboot.repositories;

import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionProjectRepository extends JpaRepository<ConstructionProject, Long> {
    ConstructionProject findByName(String name);
}
