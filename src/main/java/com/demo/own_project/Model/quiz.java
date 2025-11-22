package com.demo.own_project.Model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Entity
@Setter
@Getter
@Service
public class quiz {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
   private String title;
   private String category;
   @JsonIgnore
   private Integer score;
   private List<String> response;
   private Integer bestScore;
}
