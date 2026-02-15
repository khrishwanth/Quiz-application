
package com.demo.own_project.Backend.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.own_project.Backend.Dtos.quizCreateDto;
import com.demo.own_project.Backend.Dtos.quizDisplayDto;
import com.demo.own_project.Backend.Model.questions;
import com.demo.own_project.Backend.Model.quiz;
import com.demo.own_project.Backend.Model.users;
import com.demo.own_project.Backend.Repository.questionrepo;
import com.demo.own_project.Backend.Repository.quizrepo;
import com.demo.own_project.Backend.Security.UserDetails.CustomUserDetailService;


@RequestMapping("hi")
@Controller
@RestController
@Component
public class quizcontroller {
    @Autowired
    public questionrepo repo1;
    @Autowired
    public quizrepo repo2;


public List<quizDisplayDto> createNewquiz(String title , String category){              // Create a quiz 
    users currentUser = new users();
    Integer ID = quizcontroller.fetchId();
    currentUser.setId(ID);
    List<questions> quiz = repo1.FetchQuiz(title, category);        // Fetches data from "question" entity which has all data
    quiz existingQuiz = repo2.createQuiz(title, category, ID);     // Fetches specific data from "quiz" entity which has the all the required fields to create data
    List<String> response = quizcontroller.findAnswer(quiz);

      if(existingQuiz == null){                                      // If theres no data on that specific category & title then create a new quiz
      quiz newquiz = new quiz();
      newquiz.setCategory(category);
      newquiz.setTitle(title);
      newquiz.setScore(0);
      newquiz.setBestScore(0);       
      newquiz.setResponse(response);
      newquiz.setUser(currentUser);
      repo2.save(newquiz);
      }
      else{                                                            // If data alredy present then update only "response" , "score" and "bestscore"
        existingQuiz.setResponse(response);
        repo2.save(existingQuiz);
      }
      return quizcontroller.displayQuiz(quiz);                           // Returns the list of quiz to be displayed in the frontend
}


 @PutMapping("{title}/{category}/submit")
public Integer SubmitAndUpdateScore(@PathVariable String title , @PathVariable String category, @RequestBody List<String> answer){   //Submit answer based on the query
    Integer ID = quizcontroller.fetchId();                                                       
    quiz existingQuiz = repo2.createQuiz(title, category, ID);             // Fetches specific data from "quiz" entity which has the all the required fields to create data
    List<String> correctAnswer = existingQuiz.getResponse();               // Fetches correct answer from the "quiz" entity
    int currentScore = quizcontroller.calculateScore(correctAnswer, answer);
    int bestscore = existingQuiz.getBestScore();
    if(currentScore > bestscore){                                                   // If current score is larger than best score then update the best score                                   
        existingQuiz.setBestScore(currentScore);
    }
        existingQuiz.setScore(currentScore);                                    
        repo2.save(existingQuiz);
        return currentScore;
}



   public static List<quizDisplayDto>  displayQuiz(List<questions> quiz){
    List<quizDisplayDto> quizToBeDisplayed = new ArrayList<>();
    
        for (questions cur_quiz : quiz) {
            String question = cur_quiz.getQuestion();
            String option1 = cur_quiz.getOpt1();
            String option2 = cur_quiz.getOpt2();
            String option3 = cur_quiz.getOpt3();
            String option4 = cur_quiz.getOpt4();
            quizToBeDisplayed.add(new quizDisplayDto(question, option1, option2, option3, option4));     // Fetches data from "question" entity and stores it in a list of "quizDisplayDto" to be displayed in the frontend
        }
    return quizToBeDisplayed;
   }

   public static Integer fetchId(){
    CustomUserDetailService user =(CustomUserDetailService) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    Integer ID = user.getId();
    return ID;
   }

   public static Integer calculateScore(List<String> correctAnswer , List<String> userAnswer){    // Calculate score based on the user submitted answer and actual answer
    int count = 0;
    for (int idx = 0; idx < correctAnswer.size(); idx++) {       // Loops through the user submitted answer
        if(userAnswer.get(idx).equals(correctAnswer.get(idx))){   // Checks whether the user submitted answer matches the actual answer
            count+=1;
        } 
    }
    return count;
   }

public static List<String> findAnswer(List<questions> quiz){    // returns the list of correct answer for the given list of quiz
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

     @GetMapping("current")
     public List<quiz> current(){
    CustomUserDetailService curUser = (CustomUserDetailService) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
        Integer Id = curUser.getId();
        return repo2.findByUserId(Id);

        //return rssepo2.findUserByOptionalId(Id);
    //     return repo2.findByUserId(Id);

      //return repo2.findByUserId();
      //return repo3.findQuizByUsername(currentUser);
      //return repo2.createquiz("java", "programming");
     }

     @DeleteMapping("delete")
     public String del(){
      repo2.deleteAll();
      return "success";
     }

     }