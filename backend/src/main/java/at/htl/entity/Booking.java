package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Booking extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public Boolean lessonpayed;

    @ManyToOne
    public Lesson lesson;

    @ManyToOne
    public Student s;
}
