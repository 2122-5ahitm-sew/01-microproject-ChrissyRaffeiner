package at.htl.entity;

import javax.persistence.Entity;

@Entity
public class Teacher extends Person {
    public double salary;

    public Teacher() {
    }

    public Teacher(Long id, String firstname, String lastname, String email, int age, Double salary) {
        super(id, firstname, lastname, email, age);
        this.salary = salary;
    }
}
