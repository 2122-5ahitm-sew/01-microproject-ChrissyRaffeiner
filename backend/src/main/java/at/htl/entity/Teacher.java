package at.htl.entity;

import javax.persistence.Entity;

@Entity
public class Teacher extends Person {
    public double salary;

    public Teacher() {
    }

    public Teacher(String firstname, String lastname, String email, int age, Double salary) {
        super((long) 0, firstname, lastname, email, age);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "salary=" + salary +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
