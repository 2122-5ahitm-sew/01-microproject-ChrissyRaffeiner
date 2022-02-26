package at.htl.entity;

import javax.persistence.Entity;


@Entity
public class Student extends Person {

    public Student() {
    }

    public Student(String firstname, String lastname, String email, int age) {
        super((long) 0, firstname, lastname, email, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
