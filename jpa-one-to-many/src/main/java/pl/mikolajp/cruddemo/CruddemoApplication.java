package pl.mikolajp.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mikolajp.cruddemo.dao.AppDAO;
import pl.mikolajp.cruddemo.entity.Course;
import pl.mikolajp.cruddemo.entity.Instructor;
import pl.mikolajp.cruddemo.entity.InstructorDetail;
import pl.mikolajp.cruddemo.entity.Review;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            //createInstructor(appDAO);
            //findInstructor(appDAO);
            //deleteInstructor(appDAO);
            //findInstructorDetail(appDAO);
            //deleteInstructorDetail(appDAO);
            //createInstructorWithCourses(appDAO);
            //findInstructorWithCourses(appDAO);
            //findCoursesForInstructor(appDAO);
            //findInstructorWithCoursesJoinFetch(appDAO);
            //updateInstructor(appDAO);
            //updateCourse(appDAO);
            //deleteCourse(appDAO);
            //createCourseAndReviews(appDAO);
            //retrieveCourseAndReviews(appDAO);
            //deleteCourseAndReviews(appDAO);


        };
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int id = 5;
        System.out.println("Deleting course with id: " + id);

        //because of CascadeType.ALL it will also delete reviews, no need for separate methods or anything
        appDAO.deleteCourseById(id);

        System.out.println("Done.");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int id = 5;
        //get the course and reviews
        Course course = appDAO.findCourseAndReviewsByCourseId(id);

        //print the course
        System.out.println(course);

        //print the reviews
        System.out.println(course.getReviews());
        System.out.println("Done.");
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        //create a course
        Course tmpCourse = new Course("Pacman - How to score one million points.");

        //add some reviews
        tmpCourse.addReview(new Review("Great course, loved it!"));
        tmpCourse.addReview(new Review("cool course job well done"));
        tmpCourse.addReview(new Review("GET A LIFE WHAT A STUPID COURSE1!!!"));

        //save the course
        System.out.println("Saving the course");
        System.out.println(tmpCourse);
        System.out.println(tmpCourse.getReviews());
        appDAO.save(tmpCourse);
        System.out.println("Done.");
    }

    private void deleteCourse(AppDAO appDAO) {
        int id = 2;
        System.out.println("Deleting course with id: " + id);

        appDAO.deleteCourseById(id);

        System.out.println("Done.");
    }

    private void updateCourse(AppDAO appDAO) {
        int id = 1;

        //find the course
        System.out.println("Finding course with id: " + id);
        Course tmpCourse = appDAO.findCourseById(id);

        //update the course
        System.out.println("Updating course with id: " + id);
        tmpCourse.setTitle("Cooking - quick and simple");

        appDAO.update(tmpCourse);

        System.out.println("Done.");
    }

    private void updateInstructor(AppDAO appDAO) {
        int id = 2;

        //find the instructor
        System.out.println("Finding instructor with id: " +id);
        Instructor tmpInstructor = appDAO.findInstructorById(id);

        //update the instructor
        System.out.println("Updating instructor with id: " + id);
        tmpInstructor.setLastName("TEST");

        appDAO.update(tmpInstructor);

        System.out.println("Done.");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        //find the instructor
        int id = 2;
        System.out.println("Finding instructor with id: " + id);
        Instructor tmpInstructor = appDAO.findInstructorByIdJoinFetch(id);

        System.out.println("tmpInstructor: " + tmpInstructor);
        System.out.println("the associated courses: " + tmpInstructor.getCourses());
        System.out.println("Done.");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Finding instructor with id: " + id);

        Instructor tmpInstructor = appDAO.findInstructorById(id);

        System.out.println("tmpInstructor: " + tmpInstructor);

        //find courses for instructor
        System.out.println("Finding courses for instructor id: " + id);
        List<Course> courses = appDAO.findCourseByInstructorId(id);

        //associate the objects
        tmpInstructor.setCourses(courses);

        System.out.println("the associated courses: " + tmpInstructor.getCourses());

        System.out.println("Done.");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 2;
        System.out.println("Finding instructor with id: " + id);

        Instructor tmpInstructor = appDAO.findInstructorById(id);

        System.out.println("tmpInstructor: " + tmpInstructor);
        System.out.println("the associated courses: " + tmpInstructor.getCourses());
        System.out.println("Done.");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        //create the instructor
        Instructor tmpInstructor = new Instructor("Johny", "Johnson", "johny@example.com");
        //create the instructor detail
        InstructorDetail tmpInstructorDetail = new InstructorDetail("http://www.example.com/youtube", "gaming");
        //associate the objects
        tmpInstructor.setInstructorDetail(tmpInstructorDetail);

        //create some courses
        Course tmpCourse1 = new Course("Harmonica - l2play harmonica");
        Course tmpCourse2 = new Course("Poker - how to win");

        //add courses to instructor
        tmpInstructor.add(tmpCourse1);
        tmpInstructor.add(tmpCourse2);

        //save the instructor
        //NOTE: this will also save the courses because of CascadeType.PERSIST
        System.out.println("Saving instructor: " + tmpInstructor);
        System.out.println("The courses: " + tmpInstructor.getCourses());
        appDAO.save(tmpInstructor);

        System.out.println("Done.");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 3;
        System.out.println("Deleting instructor detail with id: " + id);

        appDAO.deleteInstructorDetailById(id);

        System.out.println("Done");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        //get the instructor detail object
        int id = 2;
        System.out.println("Finding instructor detail with id: " + id);
        InstructorDetail tmpInstructorDetail = appDAO.findInstructionDetailById(id);

        //print the instructor detail
        System.out.println(tmpInstructorDetail);

        //print the associated instructor
        System.out.println("Associated instructor: " + tmpInstructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Deleting instructor with id: " + id);

        appDAO.deleteInstructorById(id);

        System.out.println("Done.");
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 2;
        System.out.println("Finding instructor with id: " + id);

        Instructor tmpInstructor = appDAO.findInstructorById(id);

        System.out.println(tmpInstructor);
        System.out.println("the associate instructorDetail only: " + tmpInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        //create the instructor
        Instructor tmpInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

        //create the instructor detail
        InstructorDetail tmpInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "luv 2 code");

        //associate the objects
        tmpInstructor.setInstructorDetail(tmpInstructorDetail);

        //save the instructor
        //NOTE: this will also save the details object because of CascadeType.ALL
        System.out.println("Saving instructor: " + tmpInstructor);
        appDAO.save(tmpInstructor);

        System.out.println("Done.");
    }

}
