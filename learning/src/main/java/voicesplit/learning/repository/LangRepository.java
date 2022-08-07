package voicesplit.learning.repository;

import org.springframework.stereotype.Repository;
import voicesplit.learning.domain.Language;
import voicesplit.learning.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LangRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Language lang) {
        em.persist(lang);
        return lang.getId();
    }

    public Language findById(Long id) {
        return em.find(Language.class, id);
    }

    public List<Language> findAll() {
        return em.createQuery("select m from Language m", Language.class).getResultList();
    }

    public List<Language> findAllById(Long id) {
        List<Language> resultList = em.createQuery("select m from Language m where m.member.id = :member_id", Language.class)
                .setParameter("member_id", id)
                .getResultList();
        return resultList;
    }
}
