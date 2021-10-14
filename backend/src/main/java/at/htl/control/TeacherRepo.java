package at.htl.control;

import at.htl.entity.Teacher;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class TeacherRepo implements PanacheRepository<Teacher> {
    @Inject
    EntityManager entityManager;

    public Teacher save(Teacher teacher){return getEntityManager().merge(teacher);}
}
