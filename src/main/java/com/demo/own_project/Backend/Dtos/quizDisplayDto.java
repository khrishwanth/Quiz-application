package com.demo.own_project.Backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class quizDisplayDto {
   public String question;
   public String  opt1;
   public String opt2;
   public String opt3;
   public String  opt4;
}
