package com.greenfoxacademy.connectionwithmysql.controllers;

import com.greenfoxacademy.connectionwithmysql.models.Todo;
import com.greenfoxacademy.connectionwithmysql.services.AssigneeService;
import com.greenfoxacademy.connectionwithmysql.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

  @Autowired
  AssigneeService assigneeService;
  @Autowired
  TodoService todoService;

  @GetMapping(value = {"/todo", "todo/list", "/"})
  public String list(Model model, @RequestParam(name = "isActive", required = false) boolean isActive) {
    if (isActive) {
      model.addAttribute("todos", todoService.getAllTodoByDone(!isActive));
    } else {
      model.addAttribute("todos", todoService.getAllTodoByIdAsc());
    }
    return "todolist";
  }

  @GetMapping(value = {"/todo/add"})
  public String addPage() {
    return "add_todo";
  }

  @PostMapping(value = {"/todo/add"})
  public String addTodo(@ModelAttribute(name = "newTodoTitle") String newTodoTitle) {
    todoService.saveTodo(new Todo(newTodoTitle));
    return "redirect:/todo/";
  }

  @GetMapping(value = {"/{id}/delete"})
  public String deleteTodo(@PathVariable(name = "id") long id) {
    todoService.deleteTodoById(id);
    return "redirect:/todo/";
  }

  @GetMapping(value = "/{id}/edit")
  public String editPage(@PathVariable(name = "id") long id, Model model) {
    Todo todo = todoService.getTodoById(id).orElse(null);
    if (todo == null) {
      return "redirect:/todo/";
    } else {
      model.addAttribute("todo", todo);
      model.addAttribute("assignee", assigneeService.getAllAssignee());
    }
    return "edit_todo";
  }

  @PostMapping(value = "/{id}/edit")
  public String updateTodo(@ModelAttribute Todo modifiedTodo) {
    todoService.saveTodo(modifiedTodo);
    return "redirect:/todo/";
  }

  @PostMapping(value = "/todo/search")
  public String filteredTodo(@ModelAttribute(name = "searchContent") String searchContent, Model model) {
    model.addAttribute("todos", todoService.searchTodo(searchContent));
    return "todolist";
  }
}