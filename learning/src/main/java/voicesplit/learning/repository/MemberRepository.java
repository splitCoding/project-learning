package voicesplit.learning.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public void removeById(Long id){
        em.remove(findById(id));
    }
}
