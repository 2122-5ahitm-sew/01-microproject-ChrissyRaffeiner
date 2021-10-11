package at.htl.entity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Lesson extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    public String name;
    public LocalDate date;
    public double durationUnits;
    public String starttime;
    public double costs;

    @ManyToOne
    public Teacher teacher;

    public Lessontype type;
}
