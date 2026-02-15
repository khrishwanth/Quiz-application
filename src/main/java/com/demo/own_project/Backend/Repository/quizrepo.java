
package com.demo.own_project.Backend.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.own_project.Backend.Model.quiz;
import com.demo.own_project.Backend.Model.users;

@Repository
public interface quizrepo extends JpaRepository<quiz,Integer> {

    @Query(value = "Select q from quiz q where q.user.id=:ID and q.title=:title and q.category=:category")
    quiz createQuiz(String title,String category,Integer ID);

    @Query(value = "Select q from quiz q where q.user.id=:ID")
    public List<quiz> findByUserId(Integer ID);

    @Query(value = "Select q.user from quiz q where q.title=:title and q.category=:category")
    List<users> findUsersByTitleAndCategory(@Param("title") String title , @Param("category") String category);

}
