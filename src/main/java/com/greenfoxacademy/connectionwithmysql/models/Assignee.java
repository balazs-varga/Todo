package com.greenfoxacademy.connectionwithmysql.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Assignee {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long Id;
  private String name;
  private String email;

  @OneToMany
  private List<Todo> todoList;

  public Assignee(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public Assignee() {
  }

  public long getId() {
    return Id;
  }

  public void setId(long id) {
    Id = id;
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
