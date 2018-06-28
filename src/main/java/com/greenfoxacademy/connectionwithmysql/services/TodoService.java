package com.greenfoxacademy.connectionwithmysql.services;

import com.greenfoxacademy.connectionwithmysql.models.Todo;
import com.greenfoxacademy.connectionwithmysql.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TodoService {

  @Autowired
  TodoRepository todoRepository;

  public List<Todo> getAllTodoByDone(boolean isDone) {
    return todoRepository.findAllByDone(isDone);
  }

  public List<Todo> getAllTodoByIdAsc() {
    return todoRepository.findAllByOrderByIdAsc();
  }

  public void saveTodo(Todo todo) {
    todoRepository.save(todo);
  }

  public void deleteTodoById(long id) {
    todoRepository.deleteById(id);
  }

  public Optional<Todo> getTodoById(long id) {
    return todoRepository.findById(id);
  }

  public List<Todo> searchTodo(String title) {
    return todoRepository.findSearchedTitle(title);
  }
}