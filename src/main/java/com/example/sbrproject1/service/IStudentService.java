package com.example.sbrproject1.service;

import com.example.sbrproject1.model.Student;

import java.util.List;

public interface IStudentService
{
        Student addStudent(Student student);
        List<Student> getStudents();
        Student updateStudents(Student student,Long id);

        Student getStudent(Long id);
        void deleteStudent(Long id);

}
