package com.greenfoxacademy.connectionwithmysql.services;

import com.greenfoxacademy.connectionwithmysql.models.Assignee;
import com.greenfoxacademy.connectionwithmysql.repositories.AssigneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AssigneeService {

  @Autowired
  AssigneeRepository assigneeRepository;

  public void addNewAssignee(Assignee newAssignee) {
    assigneeRepository.save(newAssignee);
  }

  public List<Assignee> getAllAssignee() {
    List<Assignee> assigneeList = new ArrayList<>();
    assigneeRepository.findAll()
            .forEach(assigneeList::add);
    return assigneeList;
  }

  public void deleteAssigneeById(long id) {
    assigneeRepository.deleteById(id);
  }

  public Optional<Assignee> getAssigneeById(long id) {
    return assigneeRepository.findById(id);
  }

  public void save(Assignee assignee) {
    assigneeRepository.save(assignee);
  }
}
