package at.htl.boundary;

import at.htl.control.LessonRepo;
import at.htl.control.StudentRepo;
import at.htl.control.TeacherRepo;
import at.htl.entity.Lesson;
import at.htl.entity.Student;
import at.htl.entity.Teacher;
import org.hibernate.exception.ConstraintViolationException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("student")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped

public class StudentResource {
    @Inject
    StudentRepo studentRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllStudents() {
        return Response.ok(studentRepo.listAll()).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentRepo.findById(id);
        System.out.println(student);
        if (student != null) {
            return Response.status(202).entity(student).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createTeacher(Student student, @Context UriInfo info) {
        student = studentRepo.save(student);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(student.id.toString());
        return Response.created(uriBuilder.build()).entity(student).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateLesson(@PathParam("id") long id, Student student) {
        Student old = studentRepo.findById(id);

        if (old != null) {
            old.age = student.age;
            old.email = student.email;
            old.firstname = student.firstname;
            old.lastname = student.lastname;
            studentRepo.save(old);
            return Response
                    .ok()
                    .entity(old)
                    .build();
        } else {
            return Response
                    .status(400)
                    .header("Reason", "Lesson with id " + id + " does not exist")
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteLesson(@PathParam("id") long id) {
        try {
            Student student = studentRepo.findById(id);
            studentRepo.delete(student);
            return Response
                    .ok()
                    .build();
        }catch (ConstraintViolationException e){
            return Response.ok(100)
                    .build();
        }


    }
}
