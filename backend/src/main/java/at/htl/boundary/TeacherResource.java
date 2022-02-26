package at.htl.boundary;

import at.htl.control.TeacherRepo;
import at.htl.entity.Teacher;
import org.eclipse.microprofile.graphql.*;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("teacher")
@Produces(MediaType.APPLICATION_JSON)
@GraphQLApi
public class TeacherResource {
    @Inject
    TeacherRepo teacherRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllTeachers() {
        return Response.ok(teacherRepo.listAll()).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacherById(@PathParam("id") Long id) {
        Teacher teacher = teacherRepo.findById(id);
        System.out.println(teacher);
        if (teacher != null) {
            return Response.status(202).entity(teacher).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createTeacher(Teacher teacher, @Context UriInfo info) {
        teacher = teacherRepo.save(teacher);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(teacher.id.toString());
        return Response.created(uriBuilder.build()).entity(teacher).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateLesson(@PathParam("id") long id, Teacher teacher) {
        Teacher old = teacherRepo.findById(id);

        if (old != null) {
            old.age = teacher.age;
            old.email = teacher.email;
            old.firstname = teacher.firstname;
            old.lastname = teacher.lastname;
            old.salary = teacher.salary;
            teacherRepo.save(old);
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
            Teacher teacher = teacherRepo.findById(id);
            teacherRepo.delete(teacher);
            return Response
                    .ok()
                    .build();
        }catch (ConstraintViolationException e){
            return Response.ok(100)
                    .build();
        }
    }

    @Query
    @Description("Get all Teachers by id")
    public Teacher getTeacherByIdGraphQl(@Name("id") Long id){
        return teacherRepo.findById(id);
    }

    @Query
    @Description("Get all Teachers ")
    public List<Teacher> alleLehrer(){
        return teacherRepo.listAll();
    }
}
