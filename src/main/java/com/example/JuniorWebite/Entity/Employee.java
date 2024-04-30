package com.example.JuniorWebite.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NonNull
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    @NotNull
    @NotBlank(message = "First name is required")
    private String firstName;


    @Column(name = "last_name")
    @NotNull
    @NotBlank(message = "last name is required")
    private String lastName;

    @Column(name = "position")
    @NotNull
    @NotBlank(message = "position name is required")
    private String position;
    @Column(name = "email")
    @NotNull
    @NotBlank(message = "email name is required")
    private String email;
    @Column(name = "numero")
    @NotNull
    @NotBlank(message = "numero  is required")
    private int numero;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotNull
    @NotBlank(message = "description name is required")
    private String description;

    @Column(name = "evaluation")
    @NotNull
    @NotBlank(message = "evoluation name is required")
    private int evaluation;
    // You can store image as a path or byte array, here we store path
    @Column(name = "image_path1")
    @NotNull
    @NotBlank(message = "image_path1 name is required")
    private String imagePath1;
    @NotNull
    @Column(name = "creation_date")

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JsonBackReference
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firtName) {
        this.firstName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    // Getter pour la liste de projets


}
