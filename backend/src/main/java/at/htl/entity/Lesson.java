package at.htl.entity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Lesson extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public LocalDate date;
    public double durationUnits;
    public String starttime;
    public double costs;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    public Teacher teacher;

    public Lessontype type;

    public Lesson(String name, LocalDate date, double durationUnits, String starttime, double costs, Teacher teacher, Lessontype type) {
        this.name = name;
        this.date = date;
        this.durationUnits = durationUnits;
        this.starttime = starttime;
        this.costs = costs;
        this.teacher = teacher;
        this.type = type;
    }

    public Lesson() {

    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", durationUnits=" + durationUnits +
                ", starttime='" + starttime + '\'' +
                ", costs=" + costs +
                ", teacher=" + teacher +
                ", type=" + type +
                '}';
    }
}
