package com.demo.own_project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.own_project.Model.quiz;
@Repository
public interface quizrepo extends JpaRepository<quiz,Integer> {


    List<quiz> findByCategory(String category);

    @Query(value = "Select * from quiz q where q.title=:title and q.category=:category", nativeQuery=true)
    List<quiz> createquiz(String title,String category);

    public Optional<quiz> findByTitle(String title);
    
}
