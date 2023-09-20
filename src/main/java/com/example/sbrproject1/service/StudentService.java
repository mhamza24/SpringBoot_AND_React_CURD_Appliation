package com.example.sbrproject1.service;

import com.example.sbrproject1.exception.StudentAlreadyExistException;
import com.example.sbrproject1.exception.StudentNotFoundException;
import com.example.sbrproject1.model.Student;
import com.example.sbrproject1.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService
{

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
//        if(studentAlreadyExists(student.getEmail()))
//        {
//            throw new StudentAlreadyExistException("Student already Exist, "+student.getEmail()+" with this email");
//        }
        if(studentRepository.findByEmail(student.getEmail()).isPresent())
        {
            throw new StudentAlreadyExistException("Student already Exist, "+student.getEmail()+" with this email");
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudents(Student student, Long id) {

        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(()-> new StudentNotFoundException("Sorry,this user does not Exist"));
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Sorry,this user does not Exist") );
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id))
        {
            throw new StudentNotFoundException("Sorry,Student not found");
        }
        studentRepository.deleteById(id);
    }

//    private boolean studentAlreadyExists(String email)
//    {
//        return studentRepository.findByEmail(email).isPresent();
//    }
}
