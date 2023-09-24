package pl.mikolajp.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review", schema = "hb-03-one-to-many")
public class Review {
    //annotate the class as an entity and map to db table
    //define the fields
    //annotate the fields with db column names
    //create constructors
    //generate getter/setter methods
    //generate toString() method

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    public Review(){}

    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
