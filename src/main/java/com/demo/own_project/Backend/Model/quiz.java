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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Entity
@Setter
@Getter
@Service
public class quiz {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
   private String title;
   private String category;
   private Integer score;
   private List<String> response;
   private Integer bestScore;
   @JsonIgnore
   @ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
   @JoinColumn(name="user_id", referencedColumnName = "id")
   private users user;
}
