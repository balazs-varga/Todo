package com.greenfoxacademy.connectionwithmysql.controllers;

import com.greenfoxacademy.connectionwithmysql.models.Assignee;
import com.greenfoxacademy.connectionwithmysql.services.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AssigneeController {

  @Autowired
  AssigneeService assigneeService;

  @GetMapping(value = {"/assignees/add"})
  public String addAssigneePage(Model model) {
    model.addAttribute("assignee", new Assignee());
    return "add_assignee";
  }

  @PostMapping(value = {"/assignees/add"})
  public String addAssignee(
          @ModelAttribute(name = "newAssigneeName") String newAssigneeName,
          @ModelAttribute(name = "newAssigneeEmail") String newAssigneeEmail) {
    assigneeService.addNewAssignee(new Assignee(newAssigneeName, newAssigneeEmail));
    return "redirect:/assignees/";
  }

  @GetMapping(value = "/assignees")
  public String assignees(Model model) {
    model.addAttribute("assignees", assigneeService.getAllAssignee());
    model.addAttribute("emptyList", "There is no Assignee in the list");
    return "assigneelist";
  }

  @GetMapping(value = "/assignees/{assigneeId}/delete")
  public String deleteAssignee(@PathVariable(name = "assigneeId") long assigneeId) {
    assigneeService.deleteAssigneeById(assigneeId);
    return "redirect:/assignees/";
  }

  @GetMapping(value = "/assignees/{assigneeId}/edit")
  public String editAssigneePage(@PathVariable(name = "assigneeId") long assigneeId, Model model) {
    Assignee assignee = assigneeService.getAssigneeById(assigneeId).orElse(null);
    if (assignee == null) {
      return "redirect:/assignees/";
    } else {
      model.addAttribute("assignee", assignee);
    }
    return "edit_assignee";
  }

  @PostMapping(value = "/assignees/{assigneeId}/edit")
  public String updateAssignee(@ModelAttribute Assignee modifiedAssignee) {
    assigneeService.save(modifiedAssignee);
    return "redirect:/assignees/";
  }

  @GetMapping(value = "/assignees/{assigneeId}/todolist")
  public String assigneeTodolistPAge(@PathVariable(name = "assigneeId") long assigneeId, Model model) {
    model.addAttribute("assignee", assigneeService.getAssigneeById(assigneeId).get());
    model.addAttribute("todo", assigneeService.getAssigneeById(assigneeId).get().getTodoList());
    return "assignee_todolist";
  }
}