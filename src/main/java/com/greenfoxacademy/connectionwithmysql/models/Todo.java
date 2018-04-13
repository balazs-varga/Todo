package com.greenfoxacademy.connectionwithmysql.models;

import javax.persistence.*;

@Entity
public class Todo {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private long id;
  private String title;
  private boolean urgent;
  private boolean done;

  @ManyToOne
  @JoinColumn(name = "assigneeId")
  private Assignee assignee;

  public Todo(String title) {
    this.title = title;
    this.urgent = false;
    this.done = false;
  }

  public Todo() {
  }

  public String getTitle() {
    return title;
  }

  public long getId() {
    return id;
  }

  public boolean isUrgent() {
    return urgent;
  }

  public boolean isDone() {
    return done;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setUrgent(boolean urgent) {
    this.urgent = urgent;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public Assignee getAssignee() {
    return assignee;
  }

  public void setAssignee(Assignee assignee) {
    this.assignee = assignee;
  }
}