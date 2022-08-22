package voicesplit.learning.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Member;
import voicesplit.learning.domain.WebSite;
import voicesplit.learning.domain.MemberAndWebSite;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    WebSiteRepository webSiteRepository;
    @Autowired
    MemberAndWebSiteRepository webSiteMemberRepository;

    @Test
    void saveAndFindById() {
        //given
        Member member = new Member("kim", 1, "BACK_END", "Java", "Python");

        //when
        Long savedId = memberRepository.save(member);
        Member result = memberRepository.findById(savedId);

        //then
        assertThat(member).isEqualTo(result);
    }

    @Test
    void saveWithWebSite() {
        //given
        Member member = new Member("kim", 1, "BACK_END", "Java", "Python");
        WebSite webSite = new WebSite("인프런", "https://inflearn.com");
        MemberAndWebSite memberAndWebSite = new MemberAndWebSite();

        webSiteRepository.save(webSite);
        webSiteMemberRepository.save(memberAndWebSite);

        //when
        Long savedMemberId = memberRepository.save(member);
        member.addSite(memberAndWebSite, webSite);
        Member findMember = memberRepository.findById(savedMemberId);

        //then
        assertThat(findMember).isEqualTo(member); //저장된 멤버가 생성한 멤버가 맞는지
        assertThat(findMember.getSites()).contains(memberAndWebSite); //멤버의 sites 리스트에 memberAndWebSite 가 있는지
        assertThat(memberAndWebSite.getSite()).isEqualTo(webSite); //memberAndWebSite 의 사이트에 webSite 가 있는지
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("kim1", 1, "BACK_END", "Java", "Python");
        Member member2 = new Member("kim2", 2, "FRONT_END", "Java", "Python");
        Member member3 = new Member("kim3", 3, "FULL_STACK", "Java", "Python");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        //when
        List<Member> resultList = memberRepository.findAll();
        //then
        assertThat(resultList).contains(member1, member2, member3);
    }

    @Test
    void removeById() {
        //given
        Member member1 = new Member("kim1", 1, "BACK_END", "Java", "Python");
        Long savedId = memberRepository.save(member1);
        //when
        memberRepository.removeById(savedId);
        Member result = memberRepository.findById(savedId);
        //then
        assertThat(result).isNull();
    }
}