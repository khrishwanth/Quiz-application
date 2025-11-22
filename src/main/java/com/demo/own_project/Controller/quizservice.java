package com.demo.own_project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.own_project.Service.quizcontroller;

@RequestMapping("hi")
@Controller
@RestController
@Component
public class quizservice {
    @Autowired
    public quizcontroller controller;
       @PutMapping("create/{title}/{category}")
     public void create(@PathVariable String title , @PathVariable  String category){
        controller.create(title, category);
     }
}
