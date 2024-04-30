package com.example.JuniorWebite.EmployeeController;

import com.example.JuniorWebite.Entity.Position;
import com.example.JuniorWebite.exception.ResourceNotFoundException;
import com.example.JuniorWebite.userRepository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @PostMapping("/positions")
    public ResponseEntity<?> createPosition(@Valid @RequestBody Position position) {
        try {
            Position savedPosition = positionRepository.save(position);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPosition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la création de la position.");
        }
    }

    @DeleteMapping("/positions")
    public ResponseEntity<?> deletePosition(@Valid @RequestBody Position position) {
        try {
            if (positionRepository.existsById(position.getId())) {
                positionRepository.delete(position);
                return ResponseEntity.ok().body("Suppression réussie");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de la position.");
        }
    }

    @PutMapping("/positions")
    public ResponseEntity<?> updatePosition(@Valid @RequestBody Position position) {
        try {
            if (positionRepository.existsById(position.getId())) {
                Position updatedPosition = positionRepository.save(position);
                return ResponseEntity.ok().body(updatedPosition);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de la position.");
        }
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
}
