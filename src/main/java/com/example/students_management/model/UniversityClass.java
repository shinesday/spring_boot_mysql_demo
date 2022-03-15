package com.example.students_management.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "university_class")
public class UniversityClass {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  int year;

  @Column(nullable = false)
  int number;

  @OneToMany(mappedBy = "universityClass")
  List<Student> students;

  public UniversityClass(Long id, int year, int number) {
    this.id = id;
    this.year = year;
    this.number = number;
  }

  public UniversityClass() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public String toString() {
    String str = "";
    str += "Primary ID: " + getId();
    str += " Year: " + getYear();
    str += " Number: " + getNumber();
    return str;
  }

}
