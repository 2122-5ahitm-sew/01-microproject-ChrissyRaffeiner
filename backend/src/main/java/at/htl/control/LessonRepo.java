package at.htl.control;

import at.htl.entity.Lesson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class LessonRepo implements PanacheRepository<Lesson> {
    @Inject
    EntityManager entityManager;
    public Lesson save(Lesson lesson){return getEntityManager().merge(lesson);}
}
