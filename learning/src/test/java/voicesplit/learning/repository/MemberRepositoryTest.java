package voicesplit.learning.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Member;
import voicesplit.learning.domain.WebSite;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    WebSiteRepository webSiteRepository;

    @Test
    void save() {
        //given
        Member member = new Member("kim", 1, "BACK_END","Java", "Python");

        //when
        Long savedId = memberRepository.save(member);
        Member result = memberRepository.findById(savedId);

        //then
        assertThat(member).isEqualTo(result);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("kim1", 1, "BACK_END","Java", "Python");
        Member member2 = new Member("kim2", 2, "FRONT_END","Java", "Python");
        Member member3 = new Member("kim3", 3, "FULL_STACK","Java", "Python");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        //when
        List<Member> resultList = memberRepository.findAll();
        //then
        assertThat(resultList).contains(member1, member2, member3);
    }
}