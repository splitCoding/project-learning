package voicesplit.learning.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Language;
import voicesplit.learning.domain.Member;
import voicesplit.learning.domain.WebSite;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LangRepository langRepository;
    @Autowired
    WebSiteRepository webSiteRepository;

    @Test
    @Transactional
    @Rollback
    void save() {
        //given
        Member member = new Member("kim", 1, "BACK_END");
        Language lang1 = new Language("JAVA");
        Language lang2 = new Language("PYTHON");
        WebSite site1 = new WebSite("Inflearn");
        WebSite site2 = new WebSite("FastCampus");

        //when
        Long savedId = memberRepository.save(member);
        Member result = memberRepository.findById(savedId);

        langRepository.save(lang1);
        langRepository.save(lang2);
        webSiteRepository.save(site1);
        webSiteRepository.save(site2);

        member.addLang(lang1);
        member.addLang(lang2);
        member.addWebSite(site1);
        member.addWebSite(site2);

        //then
        assertThat(member).isEqualTo(result);
        assertThat(member.getLanguages()).contains(lang1, lang2);
        assertThat(member.getSites()).contains(site1, site2);
    }

    @Test
    @Transactional
    @Rollback
    void findAll() {
        //given
        Member member1 = new Member("kim1", 1, "BACK_END");
        Member member2 = new Member("kim2", 2, "FRONT_END");
        Member member3 = new Member("kim3", 3, "FULL_STACK");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        //when
        List<Member> resultList = memberRepository.findAll();
        //then
        assertThat(resultList).contains(member1, member2, member3);
    }
}