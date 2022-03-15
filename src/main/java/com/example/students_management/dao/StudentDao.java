package com.example.students_management.dao;

import com.example.students_management.model.Student;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<Student, Long> {
  List<Student> findByName(String name);

}
