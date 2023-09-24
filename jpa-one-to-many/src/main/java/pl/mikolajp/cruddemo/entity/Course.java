package pl.mikolajp.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course", schema = "hb-03-one-to-many")
public class Course {
    //define fields
    //define constructors
    //define getter/setter methods
    //define toString method
    //annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.PERSIST,
                            CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    public Course(){}

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    //add a convenience method
    public void addReview(Review review){
        if (reviews == null){
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }
}
