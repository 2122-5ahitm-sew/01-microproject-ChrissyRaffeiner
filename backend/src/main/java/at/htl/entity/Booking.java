package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Booking extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Boolean lessonpayed;

    @ManyToOne
    public Lesson lesson;

    @ManyToOne
    public Student student;

    public Booking(Boolean lessonpayed, Lesson lesson, Student s) {
        this.lessonpayed = lessonpayed;
        this.lesson = lesson;
        this.student = s;
    }

    public Booking() {

    }

    @Override
    public String toString() {
        return "Booking{" +
                "lessonpayed=" + lessonpayed +
                ", lesson=" + lesson +
                ", s=" + student +
                '}';
    }
}
