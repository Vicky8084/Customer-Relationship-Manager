package com.vicky.CustomerRelationshipManager.dbRepository;

import com.vicky.CustomerRelationshipManager.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction,Long> {
}
