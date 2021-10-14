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

    static Lesson lesson = new Lesson("Hiphop Gruppe 1", LocalDate.now(), 2.5, "12 Uhr", 20.5, new Teacher("Katerina", "Moser", "l.moser@gmail.com", 22, 1200.00), Lessontype.HIPHOP);


    /*
    @Test
    @Transactional
    @Deprecated
    void t02_insertBooking() {
        Student student = studentRepo.findById((long) 100);

        Booking booking = new Booking(false, lesson, student);

        bookingRepo.save(booking);

        Table table = new Table(ds, "booking");
        output(table).toConsole();

        org.assertj.db.api.Assertions.assertThat(table)
                .row(0)
                .value("s_id").isEqualTo(student.id);
    }
     */

}