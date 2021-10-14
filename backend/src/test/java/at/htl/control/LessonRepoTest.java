package at.htl.control;

import at.htl.entity.Lesson;
import at.htl.entity.Lessontype;
import at.htl.entity.Teacher;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.LocalDate;
import org.assertj.db.type.Table;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class LessonRepoTest {
    @Inject
    AgroalDataSource ds;

    @Inject
    LessonRepo lessonRepo;

    @Inject
    TeacherRepo teacherRepo;

    @Test
    @Transactional
    void t01_insertLesson() {
        Teacher teacher = teacherRepo.findById((long) 100);
        Lesson lesson = new Lesson("Balettkurs Gruppe 1", LocalDate.now(), 2.5, "12 Uhr", 20.5, teacher, Lessontype.BALLET);

        lessonRepo.save(lesson);

       Table table = new Table(ds, "lesson");
       output(table).toConsole();

        org.assertj.db.api.Assertions.assertThat(table)
                .row(0)
                .value("name").isEqualTo("Balettkurs Gruppe 1")
                .value("durationUnits").isEqualTo(2.5)
                .value("type").isEqualTo(0);//0 -> Ballet
    }

    @Test
    @Transactional
    void t02_findByID() {
        Lesson lesson = lessonRepo.findById((long) 1);
        System.out.println(lesson);

        assertThat(lesson.name).isEqualTo("Balettkurs Gruppe 1");
        assertThat(lesson.durationUnits).isEqualTo(2.5);
    }

    @Test
    @Transactional
    void t03_update() {
        Lesson lesson = lessonRepo.findById((long) 1);
        System.out.println(lesson);

        lesson.name = "New Ballet Kurs";
        lessonRepo.save(lesson);
    }

    @Test
    @Transactional
    void t04_checkupdate() {
        Table table = new Table(ds, "lesson");
        output(table).toConsole();
        org.assertj.db.api.Assertions.assertThat(table)
                .row(0)
                .value("name").isEqualTo("New Ballet Kurs");
    }

    @Test
    @Transactional
    void t05_delete() {
        Lesson lesson = lessonRepo.findById((long) 1);
        System.out.println(lesson);

        lessonRepo.delete(lesson);
    }

    @Test
    @Transactional
    void t06_checkdelete() {
        Table table = new Table(ds, "lesson");
        output(table).toConsole();
    }
}