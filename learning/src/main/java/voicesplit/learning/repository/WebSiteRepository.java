package voicesplit.learning.repository;

import org.springframework.stereotype.Repository;
import voicesplit.learning.domain.WebSite;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WebSiteRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(WebSite webSite) {
        em.persist(webSite);
        return webSite.getId();
    }

    public WebSite findById(Long id) {
        return em.find(WebSite.class, id);
    }

    public WebSite findByName(String webName) {
        return em.createQuery("select m from WebSite m where m.siteName=:webName", WebSite.class)
                .setParameter("webName", webName)
                .getSingleResult();
    }

    public List<WebSite> findAll() {
        return em.createQuery("select m from WebSite m", WebSite.class).getResultList();
    }

    public void removeById(Long id){
        em.remove(findById(id));
    }

}
