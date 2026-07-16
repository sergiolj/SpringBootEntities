package br.edu.ucsal.sergiolj.entitiesspringboot.repositories;

import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionSet;
import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConstructionSetRepository extends JpaRepository<ConstructionSet, Long> {
    ConstructionSet findByName(String name);
}
