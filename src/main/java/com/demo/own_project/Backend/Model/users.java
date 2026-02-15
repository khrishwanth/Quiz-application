package com.demo.own_project.Backend.Model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
    // @JoinColumn(name="quiz_id", referencedColumnName = "id")
    private List<quiz> quiz;
}

    // @OneToMany(cascade=CascadeType.ALL , fetch=FetchType.LAZY)
    // @JoinColumn(name="user_id",
    // referencedColumnName = "id"
    // )
