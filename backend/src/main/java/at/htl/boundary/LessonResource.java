package at.htl.boundary;

import at.htl.control.LessonRepo;
import at.htl.entity.Lesson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("lesson")
@Produces(MediaType.APPLICATION_JSON)
public class LessonResource {
    @Inject
    LessonRepo lessonRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllLessons() {
        return Response.ok(lessonRepo.listAll()).build();
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLessonById(@PathParam("id") Long id) {
        Lesson lesson = lessonRepo.findById(id);
        System.out.println(lesson);
        if (lesson != null) {
            return Response.status(201).entity(lesson).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createLesson(Lesson lesson, @Context UriInfo info) {
        lesson = lessonRepo.save(lesson);
        UriBuilder uriBuilder = info.getAbsolutePathBuilder();
        uriBuilder.path(lesson.id.toString());
        return Response.created(uriBuilder.build()).entity(lesson).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateLesson(@PathParam("id") long id, Lesson lesson) {
        Lesson old = lessonRepo.findById(id);

        if (old != null) {
            old.name = lesson.name;
            old.durationUnits = lesson.durationUnits;
            old.costs = lesson.costs;
            old.date = lesson.date;
            old.type = lesson.type;
            old.starttime = lesson.starttime;
            old.teacher = lesson.teacher;
            lessonRepo.save(old);
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
        Lesson lesson = lessonRepo.findById(id);
        lessonRepo.delete(lesson);
        return Response
                .ok()
                .build();

    }
}
