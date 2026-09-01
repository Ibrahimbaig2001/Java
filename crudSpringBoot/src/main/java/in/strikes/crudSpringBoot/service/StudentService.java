package in.strikes.crudSpringBoot.service;

import in.strikes.crudSpringBoot.entity.Student;
import in.strikes.crudSpringBoot.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student studentReq){
        studentReq.setDeleted(false);
        Student studentRes = studentRepository.save(studentReq);
        return studentRes;
        // store

    }
    public Student getStudent(Long id){
        Optional<Student> studentRes = studentRepository.findByIdAndDeletedIsFalse(id);
        if(studentRes.isPresent()){
            return studentRes.get();
        }
        else{
            return null;
        }
    }
    public List<Student> getAllStudent(){
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList;
    }
    public Student updateStudent(Long id, Student studentReq){
        Optional<Student> findStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(findStudent.isEmpty()){
            return null;
        }
        Student studentToSave = findStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        return studentRepository.save(studentToSave);

    }
    public Boolean deleteStudent(Long id){
        Boolean findStudent = studentRepository.existsById(id);
        if(!findStudent) return false;
        studentRepository.deleteById(id);
        return true;
    }
    public Boolean deleteStudentSoftly(Long id){
        Optional<Student> findStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(findStudent.isEmpty()) return false;
        Student studentToSave = findStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);
        return true;
    }

}
