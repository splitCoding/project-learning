package voicesplit.learning.repository;

import org.springframework.stereotype.Repository;
import voicesplit.learning.domain.MemberAndWebSite;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberAndWebSiteRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(MemberAndWebSite webSiteMember) {
        em.persist(webSiteMember);
        return webSiteMember.getId();
    }

    public MemberAndWebSite findById(Long id) {
        return em.find(MemberAndWebSite.class, id);
    }

    public List<MemberAndWebSite> findAll() {
        return em.createQuery("select m from MemberAndWebSite m", MemberAndWebSite.class).getResultList();
    }

    public List<MemberAndWebSite> findByMemberId(Long memberId){
        return em.createQuery("select m from MemberAndWebSite m where m.member.id=:memberId", MemberAndWebSite.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<MemberAndWebSite> findBySiteId(Long siteId){
        return em.createQuery("select m from MemberAndWebSite m where m.site.id=:siteId", MemberAndWebSite.class)
                .setParameter("siteId", siteId)
                .getResultList();
    }
}
