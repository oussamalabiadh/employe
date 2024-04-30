package com.example.JuniorWebite.Dto;

import lombok.Data;
import lombok.ToString;
import java.util.List;

import java.util.Date;

@Data
@ToString
public class EmployeeWithProjectsDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String numero;
    private String position;
    private String evaluation;
    private String imagePath1;
    private List<ProjectDTO> projects;
    private int nombreDeProjet; // Ajout de l'attribut

    public int getNombreDeProjet() {
        return nombreDeProjet;
    }

    public void setNombreDeProjet(int nombreDeProjet) {
        this.nombreDeProjet = nombreDeProjet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}
