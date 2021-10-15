package at.htl.control;

import at.htl.entity.*;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class BookingRepoTest {
    @Inject
    AgroalDataSource ds;

    @Inject
    BookingRepo bookingRepo;

    @Inject
    StudentRepo studentRepo;

    @Inject
    LessonRepo lessonRepo;

    @Test
    @Transactional
    @Deprecated
    void t01_insertBooking() {
        Student student = studentRepo.findById((long) 101);
        Lesson lesson = lessonRepo.findById((long) 100);

        System.out.println(lesson);
        System.out.println(student);

        Booking booking = new Booking(false, lesson, student);

        System.out.println(booking);
        bookingRepo.save(booking);

        Table table = new Table(ds, "booking");
        output(table).toConsole();

        org.assertj.db.api.Assertions.assertThat(table)
                .row(0)
                .value("lessonpayed").isEqualTo(booking.lessonpayed)
                .value("lesson_id").isEqualTo(lesson.id)
                .value("student_id").isEqualTo(student.id);
    }

    @Test
    @Transactional
    void t02_findByID() {
        Booking booking = bookingRepo.findById((long) 1);
        System.out.println(booking);

        assertThat(booking.id).isEqualTo(1);
        assertThat(booking.lessonpayed).isEqualTo(false);
        assertThat(booking.lesson.name).isEqualTo("Tanzkurs 1");
    }

    @Test
    @Transactional
    void t03_update() {
        Booking booking = bookingRepo.findById((long) 1);
        System.out.println(booking);

        booking.lessonpayed = true;
        bookingRepo.save(booking);
    }

    @Test
    @Transactional
    void t04_checkupdate() {
        Booking booking = bookingRepo.findById((long) 1);

        Table table = new Table(ds, "booking");
        output(table).toConsole();
        org.assertj.db.api.Assertions.assertThat(table)
                .row(0)
                .value("lessonpayed").isEqualTo(true);
    }

    @Test
    @Transactional
    void t05_delete() {
        Booking booking = bookingRepo.findById((long) 1);
        System.out.println(booking);

        bookingRepo.delete(booking);
    }

    @Test
    @Transactional
    void t06_checkdelete() {
        Table table = new Table(ds, "booking");
        output(table).toConsole();
    }

}