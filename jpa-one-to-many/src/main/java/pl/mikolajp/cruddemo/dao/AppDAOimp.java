package pl.mikolajp.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mikolajp.cruddemo.entity.Course;
import pl.mikolajp.cruddemo.entity.Instructor;
import pl.mikolajp.cruddemo.entity.InstructorDetail;

import java.util.List;

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

        //get the courses
        List<Course> courses = tmpInstructor.getCourses();

        //break association of all courses
        for(Course tmpCourse : courses){
            tmpCourse.setInstructor(null);
        }

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

    @Override
    public List<Course> findCourseByInstructorId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE instructor.id=:data", Course.class
        );
        query.setParameter("data", id);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
          "SELECT i FROM Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail WHERE i.id=:data", Instructor.class
        );
        query.setParameter("data", id);

        //execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course tmpCourse = entityManager.find(Course.class, id);

        entityManager.remove(tmpCourse);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
          "SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id=:data" , Course.class
        );
        query.setParameter("data", id);

        //execute query
        return query.getSingleResult();
    }
}
