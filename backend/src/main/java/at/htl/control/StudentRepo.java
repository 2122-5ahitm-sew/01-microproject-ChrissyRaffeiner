package at.htl.control;

import at.htl.entity.Student;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class StudentRepo implements PanacheRepository<Student> {
    @Inject
    EntityManager entityManager;

    public Student save(Student student){return getEntityManager().merge(student);}
}
