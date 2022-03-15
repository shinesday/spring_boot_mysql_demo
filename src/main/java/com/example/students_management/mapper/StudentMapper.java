package com.example.students_management.mapper;

import com.example.students_management.model.Student;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

  // SELECT + FROM student where name LIKE %T%;

  @Select("SELECT + FROM student where name LIKE #{name}")
  List<Student> getStudentsContainStrInName(String name);

  // SELECT + FROM student where university_class_id IN
  // (SELECT id FROM university_class where year = 2021 AND number = 1);

  @Select("SELECT + FROM student where university_class_id IN " +
          "(SELECT id FROM university_class where year = #{year} AND number = #{number})")
  List<Student> getStudentsInClass(@Param("year") int year, @Param("number") int number);

}
