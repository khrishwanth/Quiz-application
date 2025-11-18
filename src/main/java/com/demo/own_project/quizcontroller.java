package com.demo.own_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hi")
@Controller
@RestController
@Component
public class quizcontroller {
    @Autowired
    public questionrepo repo1;
    @Autowired
    public quizrepo repo2;

    public void create(String title , String category){              // Create a quiz 

      List<questions> quiz = repo1.createquiz(title, category);        // Fetches data from "question" entity which has all data
      List<quiz> existingQuiz = repo2.createquiz(title, category);     // Fetches specific data from "quiz" entity which has the all the required fields to create data
      if(existingQuiz.isEmpty()){                                      // If theres no data on that specific category & title then create a new quiz
      quiz newquiz = new quiz();
      newquiz.setCategory(category);
      newquiz.setTitle(title);
      newquiz.setScore(0);
      newquiz.setBestScore(0);
      List<String> response = quizcontroller.setans(quiz);         
      newquiz.setResponse(response);
      repo2.save(newquiz);
      }
      else{                                                            // If data alredy present then update only "response" , "score" and "bestscore"
        List<String> response = quizcontroller.setans(quiz);
        quiz existing = existingQuiz.get(0);                              
        existing.setResponse(response);
        repo2.save(existing);
      }
}
 @PutMapping("{title}/{category}/submit")
public Integer submit(@PathVariable String title , @PathVariable String category, @RequestBody List<String> answer){   //Submit answer based on the query
      
    int count = 0;                                                       // counter to set the score based on the count of matched answers
      quiz existingQuiz = repo2.createquiz(title, category).get(0);
      List<String> correctAnswer = existingQuiz.getResponse();      
    for (int idx = 0; idx < correctAnswer.size(); idx++) {                 // Loops through the user submitted answer
        if(answer.get(idx).equals(correctAnswer.get(idx))){                // Checks whether the user submitted answer matches the actual answer
            count+=1;    } 
    }
    int bestscore = existingQuiz.getBestScore();
    if(count > bestscore){                                                   // If current score(count) is larger than best csore then update the best score                                   
        existingQuiz.setBestScore(count);
    }
    else{

    }
        existingQuiz.setScore(count);                                    
        repo2.save(existingQuiz);
        return count;
        //return arr.get(0).getResponse().get(pos);
        // @PathVariable Integer pos
}

public static List<String> setans(List<questions> quiz){    // returns the list of correct answer for the given list of quiz
      List<String> answerList = new ArrayList<>();     
      for (int idx = 0; idx < quiz.size(); idx++) {
            String answer = quiz.get(idx).getAnswer();       // Fetches answer from the list of all "quiz" seperately
            answerList.add(answer);
        }
    return answerList;
}

    @GetMapping("show")
     public List<quiz> display(){
      return repo2.findAll();
     }

     @DeleteMapping("delete")
     public String del(){
      repo2.deleteAll();
      return "success";
     }
     @DeleteMapping("{title}/{category}")
     public void dele(@PathVariable String title ,@PathVariable String category){
         List<quiz> quiz = repo2.createquiz(title, category);
         quiz q = quiz.get(0);
         repo2.delete(q);
     }
     }