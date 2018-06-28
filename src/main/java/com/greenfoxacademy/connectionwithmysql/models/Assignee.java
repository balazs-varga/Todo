package com.greenfoxacademy.connectionwithmysql.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Component
public class Assignee {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long assigneeId;
  private String name;
  private String email;

  @OneToMany(mappedBy = "assignee")
  private List<Todo> todoList;

  public Assignee(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public Assignee() {
  }

  public long getAssigneeId() {
    return assigneeId;
  }

  public void setAssigneeId(long assigneeId) {
    this.assigneeId = assigneeId;
  }

  public List<Todo> getTodoList() {
    return todoList;
  }

  public void setTodoList(List<Todo> todoList) {
    this.todoList = todoList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}