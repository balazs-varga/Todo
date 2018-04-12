package com.greenfoxacademy.connectionwithmysql.controllers;

import com.greenfoxacademy.connectionwithmysql.models.Assignee;
import com.greenfoxacademy.connectionwithmysql.repositories.AssigneeRepository;
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
  AssigneeRepository assigneeRepository;

  @GetMapping(value = {"/assignees/add"})
  public String addAssigneePage() {
    return "add_assignee";
  }

  @PostMapping(value = {"/assignees/add"})
  public String addAssignee(
          @ModelAttribute(name = "newAssigneeName") String newAssigneeName,
          @ModelAttribute(name = "newAssigneeEmail") String newAssigneeEmail) {
    assigneeRepository.save(new Assignee(newAssigneeName, newAssigneeEmail));
    return "redirect:/assignees/";
  }

  @GetMapping(value = "/assignees")
  public String assignees(Model model) {
    model.addAttribute("assignees", assigneeRepository.findAll());
    model.addAttribute("emptyList", "There is no Assignee in the list");
    return "assigneelist";
  }

  @GetMapping(value = "/assignees/{id}/delete")
  public String deleteAssignee(@PathVariable(name = "id") long id) {
    assigneeRepository.deleteById(id);
    return "redirect:/assignees/";
  }

  @GetMapping(value = "/assignees/{id}/edit")
  public String editAssigneePage(@PathVariable(name = "id") long id, Model model) {
    Assignee assignee = assigneeRepository.findById(id).orElse(null);
    if (assignee == null) {
      return "redirect:/assignees/";
    } else {
      model.addAttribute("assignee", assignee);
    }
    return "edit_assignee";
  }

  @PostMapping(value = "/assignees/{id}/edit")
  public String updateAssignee(@ModelAttribute Assignee modifiedAssignee) {
    assigneeRepository.save(modifiedAssignee);
    return "redirect:/assignees/";
  }
}