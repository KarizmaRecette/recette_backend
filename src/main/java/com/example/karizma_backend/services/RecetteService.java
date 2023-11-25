package com.example.karizma_backend.services;

import com.example.karizma_backend.entity.Recette;
import com.example.karizma_backend.repositories.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetteService {

    @Autowired
    private RecetteRepository recetteRepository;

    public Recette createRecette(Recette recette) {
        // Logique de validation si nécessaire
        return recetteRepository.save(recette);
    }

    public List<Recette> getAllRecettes() {
        // Logique de récupération des recettes par utilisateur si nécessaire
        return recetteRepository.findAll();
    }

    public Recette getRecetteById(Long id) {
        return recetteRepository.findById(id).orElse(null);
    }

    public Recette updateRecette(Long id, Recette updatedRecette) {
        Recette existingRecette = recetteRepository.findById(id).orElse(null);
        if (existingRecette != null) {
            // Logique de mise à jour si nécessaire
            return recetteRepository.save(updatedRecette);
        } else {
            return null;
        }
    }

    public boolean deleteRecette(Long id) {
        Recette existingRecette = recetteRepository.findById(id).orElse(null);
        if (existingRecette != null) {
            recetteRepository.delete(existingRecette);
            return true;
        } else {
            return false;
        }
    }
}

