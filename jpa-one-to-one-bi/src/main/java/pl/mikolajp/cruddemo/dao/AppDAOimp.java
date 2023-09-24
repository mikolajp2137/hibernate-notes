package pl.mikolajp.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mikolajp.cruddemo.entity.Instructor;
import pl.mikolajp.cruddemo.entity.InstructorDetail;

@Repository
public class AppDAOimp implements AppDAO{
    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOimp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        //because of CascadeType.ALL both objects will be saved
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        //because of default behavior of @OneToOne fetch type is eager i get both objects
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        //retrieve the instructor
        Instructor tmpInstructor = entityManager.find(Instructor.class, id);

        //delete the instructor
        entityManager.remove(tmpInstructor);
    }

    @Override
    public InstructorDetail findInstructionDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        //retrieve instructor detail
        InstructorDetail tmpInstructorDetail = entityManager.find(InstructorDetail.class, id);

        //remove the associated object reference
        //break bi-directional link
        tmpInstructorDetail.getInstructor().setInstructorDetail(null);

        //delete the instructor detail
        entityManager.remove(tmpInstructorDetail);
    }
}
