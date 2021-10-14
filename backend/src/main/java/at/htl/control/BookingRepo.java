package at.htl.control;

import at.htl.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class BookingRepo implements PanacheRepository<Booking> {
    @Inject
    EntityManager entityManager;

    public Booking save(Booking booking) {
        return getEntityManager().merge(booking);
    }
}
