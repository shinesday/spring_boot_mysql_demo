package com.example.students_management.service;

import com.example.students_management.dao.StudentDao;
import com.example.students_management.dao.UniversityClassDao;
import com.example.students_management.exceptions.InvalidUniversityClassException;
import com.example.students_management.exceptions.StudentEmptyNameException;
import com.example.students_management.exceptions.StudentNonExistException;
import com.example.students_management.mapper.StudentMapper;
import com.example.students_management.model.Student;
import com.example.students_management.model.UniversityClass;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentDao studentDao;
  private UniversityClassDao universityClassDao;
  private StudentMapper studentMapper;

  @Autowired
  public StudentService(StudentDao studentDao, UniversityClassDao universityClassDao, StudentMapper studentMapper) {
    this.studentDao = studentDao;
    this.universityClassDao = universityClassDao;
    this.studentMapper = studentMapper;
  }

  public Student addStudent(Student student) {
    if (student.getName().isEmpty()) {
      throw new StudentEmptyNameException("Student name cannot be empty");
    }
    return studentDao.save(student);
  }

  public Student assignClass(Long studentId, Long classID) {
    if (!studentDao.existsById(studentId)) {
      throw new StudentNonExistException("Cannot find student ID");
    }
    if(!universityClassDao.existsById(classID)) {
      throw new InvalidUniversityClassException("Cannot find class ID");
    }
    Student student = getStudentById(studentId).get();
    UniversityClass universityClass = universityClassDao.findById(classID).get();
    student.setUniversityClass(universityClass);
    return studentDao.save(student);
  }

  public Student updateStudent(Student student) {
    if (student.getId() == null || !studentDao.existsById(student.getId())) {
      throw new StudentNonExistException("Cannot find student ID");
    }
    return studentDao.save(student);
  }

  public List<Student> getAllStudents() {
    return (List<Student>) studentDao.findAll();
  }

  public Optional<Student> getStudentById(Long id) {
    return studentDao.findById(id);
  }

  public List<Student> getStudentByName(String name) {
    return studentDao.findByName(name);
  }

  public List<Student> getStudentsContainName(String name) {
    return studentMapper.getStudentsContainStrInName("%" + name + "%");
  }

  public List<Student> getStudentsInClass(int year, int number) {
    return studentMapper.getStudentsInClass(year, number);
  }

}
