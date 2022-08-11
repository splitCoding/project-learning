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
class MemberAndWebSiteRepositoryTest {
    @Autowired
    MemberAndWebSiteRepository webSiteMemberRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WebSiteRepository webSiteRepository;

    @Test
    void saveAndFindById() {
        //given
        MemberAndWebSite webSiteMember = new MemberAndWebSite();
        //when
        Long saveId = webSiteMemberRepository.save(webSiteMember);
        MemberAndWebSite result = webSiteMemberRepository.findById(saveId);
        //then
        assertThat(result).isEqualTo(webSiteMember);
    }

    @Test
    void findAll() {
        //given
        MemberAndWebSite webSiteMember1 = new MemberAndWebSite();
        MemberAndWebSite webSiteMember2 = new MemberAndWebSite();
        MemberAndWebSite webSiteMember3 = new MemberAndWebSite();
        webSiteMemberRepository.save(webSiteMember1);
        webSiteMemberRepository.save(webSiteMember2);
        webSiteMemberRepository.save(webSiteMember3);

        //when
        List<MemberAndWebSite> resultList = webSiteMemberRepository.findAll();

        //then
        assertThat(resultList).contains(webSiteMember1, webSiteMember2, webSiteMember3);
    }

    @Test
    void findByMemberId(){
        //given
        MemberAndWebSite webSiteMember = new MemberAndWebSite();
        Member member = new Member("kim1",23,"front", "Java", "Python");
        WebSite webSite = new WebSite("인프런");

        webSiteMemberRepository.save(webSiteMember);
        webSiteRepository.save(webSite);
        Long savedMemberId = memberRepository.save(member);

        member.addSite(webSiteMember, webSite);

        //when
        List<MemberAndWebSite> resultList = webSiteMemberRepository.findByMemberId(savedMemberId);

        //then
        assertThat(resultList).contains(webSiteMember);
    }

    @Test
    void findBySiteId(){
        //given
        MemberAndWebSite memberAndWebSite = new MemberAndWebSite();
        Member member = new Member("kim2",233,"front", "Java", "Python");
        WebSite webSite = new WebSite("인프런");

        webSiteMemberRepository.save(memberAndWebSite);
        memberRepository.save(member);
        Long savedSiteId = webSiteRepository.save(webSite);

        member.addSite(memberAndWebSite, webSite);

        //when
        List<MemberAndWebSite> resultList = webSiteMemberRepository.findBySiteId(savedSiteId);

        //then
        assertThat(resultList).contains(memberAndWebSite);
    }
}