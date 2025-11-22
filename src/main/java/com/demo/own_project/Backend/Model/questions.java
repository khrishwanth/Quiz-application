
package com.demo.own_project.Backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class questions {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
   private int id;
   private String  title;
   private String category;
   private String  question;
   private String answer;
   private String opt1;
   private String opt2;
   private String opt3;
   private String opt4;
}
