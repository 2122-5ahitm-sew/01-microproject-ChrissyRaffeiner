package at.htl.control;

import at.htl.entity.Student;
import at.htl.entity.Teacher;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.aesh.readline.terminal.formatting.TerminalString;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class StudentRepoTest {
    @Inject
    AgroalDataSource ds;

    @Inject
    StudentRepo studentRepo;

    @Test
    @Transactional
    void t01_insertStudent() {
        Student student = new Student("Isabel", "Turner", "turner@gmail.com", 18);
        studentRepo.save(student);

        Table table = new Table(ds, "student");
        output(table).toConsole();
        System.out.println("Problem: inserted Student does not show yet. It is in the database! as shown by t02");
    }

    @Test
    @Transactional
    void t02_checkinsertStudent() {
        Table table = new Table(ds, "student");
        output(table).toConsole();

        org.assertj.db.api.Assertions.assertThat(table)
                .row(0)
                .value("firstname").isEqualTo("Isabel")
                .value("lastname").isEqualTo("Turner");
    }
    

    @Test
    @Transactional
    @Deprecated
    void t03_findByID() {
        Student student = studentRepo.findById((long) 100);
        System.out.println(student);

        assertThat(student.firstname).isEqualTo("Christine");
        assertThat(student.lastname).isEqualTo("Raffeiner");
    }

    @Test
    @Transactional
    @Deprecated
    void t04_update() {
        Student student = studentRepo.findById((long) 100);
        System.out.println(student);

        student.email = "newemail@gmail.com";
        studentRepo.save(student);
    }

    @Test
    @Transactional
    @Deprecated
    void t05_checkupdate() {
        Student student = studentRepo.findById((long) 100);

        Table table = new Table(ds, "student");
        output(table).toConsole();
        org.assertj.db.api.Assertions.assertThat(table)
                .row(1)
                .value("email").isEqualTo("newemail@gmail.com");

    }

    @Test
    @Transactional
    void t06_delete() {
        Student student = studentRepo.findById((long) 100);
        System.out.println(student);

        studentRepo.delete(student);
    }

    @Test
    @Transactional
    void t07_checkdelete() {
        Table table = new Table(ds, "student");
        output(table).toConsole();
    }
}