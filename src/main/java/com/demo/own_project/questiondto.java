package com.demo.own_project;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class questiondto {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   public Integer id;
   public String title;
   public String question;
   public String  opt1;
   public String opt2;
   public String opt3;
   public String  opt4;
}
