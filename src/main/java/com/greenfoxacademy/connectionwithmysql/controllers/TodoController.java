package com.greenfoxacademy.connectionwithmysql.controllers;

import com.greenfoxacademy.connectionwithmysql.models.Todo;
import com.greenfoxacademy.connectionwithmysql.repositories.AssigneeRepository;
import com.greenfoxacademy.connectionwithmysql.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

  @Autowired
  TodoRepository todoRepository;
  @Autowired
  AssigneeRepository assigneeRepository;

  @GetMapping(value = {"/todo", "todo/list", "/"})
  public String list(Model model, @RequestParam(name = "isActive", required = false) boolean isActive) {
    if (isActive) {
      model.addAttribute("todos", todoRepository.findAllByDone(!isActive));
    } else {
      model.addAttribute("todos", todoRepository.findAllByOrderByIdAsc());
    }
    return "todolist";
  }

  @GetMapping(value = {"/todo/add"})
  public String addPage() {
    return "add_todo";
  }

  @PostMapping(value = {"/todo/add"})
  public String addTodo(@ModelAttribute(name = "newTodoTitle") String newTodoTitle) {
    todoRepository.save(new Todo(newTodoTitle));
    return "redirect:/todo/";
  }

  @GetMapping(value = {"/{id}/delete"})
  public String deleteTodo(@PathVariable(name = "id") long id) {
    todoRepository.deleteById(id);
    return "redirect:/todo/";
  }

  @GetMapping(value = "/{id}/edit")
  public String editPage(@PathVariable(name = "id") long id, Model model) {
    Todo todo = todoRepository.findById(id).orElse(null);
    if (todo == null) {
      return "redirect:/todo/";
    } else {
      model.addAttribute("todo", todo);
      model.addAttribute("assignee", assigneeRepository.findAll());
    }
    return "edit_todo";
  }

  @PostMapping(value = "/{id}/edit")
  public String updateTodo(@ModelAttribute Todo modifiedTodo) {
    todoRepository.save(modifiedTodo);
    return "redirect:/todo/";
  }

  @PostMapping(value = "/todo/search")
  public String filteredTodo(@ModelAttribute(name = "searchContent") String searchContent, Model model) {
    model.addAttribute("todos", todoRepository.findSearchedTitle(searchContent));
    return "todolist";
  }
}