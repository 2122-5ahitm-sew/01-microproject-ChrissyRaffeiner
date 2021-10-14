package at.htl.control;

import at.htl.entity.Student;
import at.htl.entity.Teacher;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.assertj.db.type.Table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TeacherRepoTest {
    @Inject
    AgroalDataSource ds;

    @Inject
    TeacherRepo teacherRepo;

    @Test
    @Transactional
    void t01_insertTeacher() {
        Teacher teacher = new Teacher("Hanna", "Moser", "h.moser@gmail.com", 22, 1200.00);
        teacherRepo.save(teacher);

       Table table = new Table(ds, "teacher");
       output(table).toConsole();
        System.out.println("Problem: inserted Teacher does not show yet. It is in the database! as shown by checkinsertTeacher");
    }

    @Test
    @Transactional
    void t02_checkinsertTeacher() {
        Table table = new Table(ds, "teacher");
        output(table).toConsole();

        org.assertj.db.api.Assertions.assertThat(table)
                .row(0)
                .value("firstname").isEqualTo("Hanna")
                .value("lastname").isEqualTo("Moser");
    }

    @Test
    @Transactional
    void t03_findByID() {
        Teacher teacher = teacherRepo.findById((long) 100);
        System.out.println(teacher);

        assertThat(teacher.firstname).isEqualTo("Hans");
        assertThat(teacher.lastname).isEqualTo("Moser");
    }

    @Test
    @Transactional
    void t06_delete() {
        Teacher teacher = teacherRepo.findById((long) 101);
        System.out.println(teacher);

        teacherRepo.delete(teacher);
    }

    @Test
    @Transactional
    void t07_checkdelete() {
        Table table = new Table(ds, "teacher");
        output(table).toConsole();
    }

    @Test
    @Transactional
    void t04_update() {
        Teacher teacher = teacherRepo.findById((long) 100);
        System.out.println(teacher);

        teacher.email = "newemail@gmail.com";
        teacherRepo.save(teacher);
    }

    @Test
    @Transactional
    void t05_checkupdate() {
        Teacher teacher = teacherRepo.findById((long) 100);

        Table table = new Table(ds, "teacher");
        output(table).toConsole();
        org.assertj.db.api.Assertions.assertThat(table)
                .row(1)
                .value("email").isEqualTo("newemail@gmail.com");

    }
}