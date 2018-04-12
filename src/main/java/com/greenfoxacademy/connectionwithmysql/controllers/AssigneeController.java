package com.greenfoxacademy.connectionwithmysql.controllers;

import com.greenfoxacademy.connectionwithmysql.repositories.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AssigneeController {

  @Autowired
  AssigneeRepository assigneeRepository;

  @GetMapping(value = "/assignees")
  public String assignees(Model model) {
    model.addAttribute("assignees", assigneeRepository.findAll());
    return "assignees";
  }
}
