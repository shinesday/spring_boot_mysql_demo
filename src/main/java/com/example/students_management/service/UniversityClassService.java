package com.example.students_management.service;

import com.example.students_management.dao.StudentDao;
import com.example.students_management.dao.UniversityClassDao;
import com.example.students_management.exceptions.InvalidUniversityClassException;
import com.example.students_management.exceptions.StudentEmptyNameException;
import com.example.students_management.exceptions.StudentNonExistException;
import com.example.students_management.model.Student;
import com.example.students_management.model.UniversityClass;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityClassService {
  UniversityClassDao universityClassDao;

  @Autowired
  public UniversityClassService(UniversityClassDao universityClassDao) {
    this.universityClassDao = universityClassDao;
  }

  public List<UniversityClass> getAllClasses() {
    return (List<UniversityClass>) universityClassDao.findAll();
  }

  public UniversityClass addClass(UniversityClass universityClass) {
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    if (universityClass.getYear() < currentYear) {
      throw new InvalidUniversityClassException("Cannot add class to the past");
    }
    if (universityClass.getYear() > currentYear + 1) {
      throw new InvalidUniversityClassException("Cannot add class in the far future");
    }
    return universityClassDao.save(universityClass);
  }

}
