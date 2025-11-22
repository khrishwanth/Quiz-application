
package com.demo.own_project.Backend.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.own_project.Backend.Model.questions;

@Repository
public interface questionrepo extends JpaRepository<questions, Integer>{

    List<questions> findByCategory(String category);

 
 
    @Query(value ="select * from questions q where q.title=:ti and q.category=:cate order by rand()", nativeQuery=true)
    List<questions> createquiz(String ti,String cate);

    @Query(value = "select answer from questions q where q.title=:title and q.category=:category", nativeQuery=true)
    List<String> getans(String title,String category);
}
