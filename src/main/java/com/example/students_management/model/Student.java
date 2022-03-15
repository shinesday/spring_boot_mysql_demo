package com.example.students_management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")

public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "university_class_id")
  private UniversityClass universityClass;

  public Student(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public UniversityClass getUniversityClass() {
    return universityClass;
  }

  public void setUniversityClass(UniversityClass universityClass) {
    this.universityClass = universityClass;
  }

  public Student() {}

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    String str = "";
    str += "Primary ID: " + getId();
    str += " Name: " + getName();
    return str;
  }

}
