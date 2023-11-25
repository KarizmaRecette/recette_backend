package com.example.karizma_backend.controllers;

import com.example.karizma_backend.entity.Recette;
import com.example.karizma_backend.services.RecetteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recettes")
public class RecetteController {

    @Autowired
    private RecetteService recetteService;

    // Endpoint pour créer une recette
    @PostMapping
    public ResponseEntity<?> createRecette(@Valid @RequestBody Recette recette, BindingResult result) {
        if (result.hasErrors()) {
            // Gérer les erreurs de validation
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Recette createdRecette = recetteService.createRecette(recette);
        return new ResponseEntity<>(createdRecette, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer toutes les recettes d'un utilisateur
    @GetMapping
    public ResponseEntity<List<Recette>> getAllRecettes() {
        List<Recette> recettes = recetteService.getAllRecettes();
        return new ResponseEntity<>(recettes, HttpStatus.OK);
    }

    // Endpoint pour récupérer une recette par ID
    @GetMapping("/recette/{id}")
    public ResponseEntity<Recette> getRecetteById(@PathVariable Long id) {
        Recette recette = recetteService.getRecetteById(id);
        if (recette != null) {
            return new ResponseEntity<>(recette, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour mettre à jour une recette par ID
    @PutMapping("/recette/{id}")
    public ResponseEntity<Recette> updateRecette(@PathVariable Long id, @RequestBody Recette updatedRecette) {
        Recette recette = recetteService.updateRecette(id, updatedRecette);
        if (recette != null) {
            return new ResponseEntity<>(recette, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer une recette par ID
    @DeleteMapping("/recette/{id}")
    public ResponseEntity<Void> deleteRecette(@PathVariable Long id) {
        boolean deleted = recetteService.deleteRecette(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

