package com.example.karizma_backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la recette ne peut pas être vide.")
    private String nom;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recette_id")
    private List<Ingredient> ingredients;

    private int dureePreparation;

    @ElementCollection
    private List<String> etapesPreparation;

    private String photoUrl; // facultatif

}
