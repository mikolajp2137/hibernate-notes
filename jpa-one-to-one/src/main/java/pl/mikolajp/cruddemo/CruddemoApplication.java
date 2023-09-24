package pl.mikolajp.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mikolajp.cruddemo.dao.AppDAO;
import pl.mikolajp.cruddemo.entity.Instructor;
import pl.mikolajp.cruddemo.entity.InstructorDetail;

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

            deleteInstructor(appDAO);
        };
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 1;
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
