package voicesplit.learning.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Member;
import voicesplit.learning.domain.MemberAndWebSite;
import voicesplit.learning.domain.WebSite;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(false)
class WebSiteRepositoryTest {

    @Autowired
    WebSiteRepository webSiteRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberAndWebSiteRepository memberAndWebSiteRepository;

    @Test
    void saveAndFindById() {
        //given
        WebSite webSite = new WebSite("인프런", "https://inflearn.com");

        //when
        Long savedId = webSiteRepository.save(webSite);
        WebSite result = webSiteRepository.findById(savedId);

        //then
        assertThat(webSite).isEqualTo(result);
    }

    @Test
    void saveWithMembers() {
        //given
        WebSite webSite = new WebSite("인프런", "https://inflearn.com");
        Member member = new Member("kim", 1, "BACK_END", "Java", "Python");
        MemberAndWebSite memberAndWebSite = new MemberAndWebSite();

        memberRepository.save(member);
        memberAndWebSiteRepository.save(memberAndWebSite);

        //when
        Long savedSiteId = webSiteRepository.save(webSite);
        webSite.addMember(memberAndWebSite, member);
        WebSite findWebSite = webSiteRepository.findById(savedSiteId);

        //then
        assertThat(findWebSite).isEqualTo(webSite); //저장된 사이트가 생성한 사이트가 맞는지
        assertThat(findWebSite.getMembers()).contains(memberAndWebSite); //사이트의 members 리스트에 memberAndWebSite 가 있는지
        assertThat(memberAndWebSite.getMember()).isEqualTo(member); //memberAndWebSite 의 멤버에 member 가 있는지
    }

    @Test
    void findByName() {
        //given
        WebSite webSite = new WebSite("인프런", "https://inflearn.com");
        webSiteRepository.save(webSite);

        //when
        WebSite result = webSiteRepository.findByName("인프런");

        //then
        assertThat(webSite).isEqualTo(result);
    }

    @Test
    void findAll() {
        //given
        WebSite webSite1 = new WebSite("패스트캠퍼스", "https://fastcampus.co.kr");
        WebSite webSite2 = new WebSite("인프런", "https://inflearn.com");


        webSiteRepository.save(webSite1);
        webSiteRepository.save(webSite2);

        //when
        List<WebSite> resultList = webSiteRepository.findAll();

        //then
        assertThat(resultList).contains(webSite1, webSite2);
    }

    @Test
    void removeById() {
        //given
        WebSite webSite = new WebSite("인프런", "https://inflearn.com");
        Long savedId = webSiteRepository.save(webSite);

        //when
        webSiteRepository.removeById(savedId);
        WebSite result = webSiteRepository.findById(savedId);

        //then
        assertThat(result).isNull();
    }
}