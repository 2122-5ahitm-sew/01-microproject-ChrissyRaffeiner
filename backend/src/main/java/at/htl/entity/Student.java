package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student extends Person {

    public Student() {
    }

    public Student(Long id, String firstname, String lastname, String email, int age) {
        super(id, firstname, lastname, email, age);
    }
}
