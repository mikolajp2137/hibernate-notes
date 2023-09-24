package pl.mikolajp.cruddemo.dao;

import pl.mikolajp.cruddemo.entity.Instructor;
import pl.mikolajp.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructionDetailById(int id);

    void deleteInstructorDetailById(int id);
}
