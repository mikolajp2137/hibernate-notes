package pl.mikolajp.cruddemo.dao;

import pl.mikolajp.cruddemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
