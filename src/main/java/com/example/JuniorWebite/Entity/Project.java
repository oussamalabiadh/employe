package com.example.JuniorWebite.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@NonNull
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "image1")
    private String image1;


    @Column(name = "image2")
    private String image2;


    @Column(name = "image3")
    private String image3;


    @Column(name = "image4")
    private String image4;
    @Column(name = "description")
    private String description;


    @Column(name = "github_link")
    private String githubLink;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Getter pour la liste d'employés

    // Getters and setters

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

}

