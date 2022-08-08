package voicesplit.learning.repository;

import org.springframework.stereotype.Repository;
import voicesplit.learning.domain.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SubjectRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Subject subject) {
        em.persist(subject);
        return subject.getId();
    }

    public Subject findById(Long id) {
        return em.find(Subject.class, id);
    }

    public Subject findByName(String name) {
        return em.createQuery("select m from Subject m where m.name=:name", Subject.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Subject> findAll() {
        return em.createQuery("select m from Subject m").getResultList();
    }
}
