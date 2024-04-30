package com.example.JuniorWebite.userRepository;

import com.example.JuniorWebite.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    // Vous pouvez ajouter des méthodes personnalisées pour les opérations de requête spécifiques ici si nécessaire
}
