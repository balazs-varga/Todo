package com.greenfoxacademy.connectionwithmysql.repositories;

import com.greenfoxacademy.connectionwithmysql.models.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
  List<Todo> findAllByDone(boolean done);
  List<Todo> findAllByOrderByIdAsc();

  @Query("select lower(t) from Todo t where t.title like %:searchedTitle%")
  List<Todo> findSearchedTitle(@Param("searchedTitle") String searchedTitle);
}

