package voicesplit.learning.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Language;
import voicesplit.learning.domain.LanguageEnum;
import voicesplit.learning.domain.Member;
import voicesplit.learning.domain.PositionEnum;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LangRepository langRepository;

    @Test
    @Transactional
    @Rollback
    void save() {
        //given
        Member member = new Member("kim", 1, PositionEnum.BackEnd);
        Language lang1 = new Language(LanguageEnum.Java);
        Language lang2 = new Language(LanguageEnum.Python);

        //when
        Long savedId = memberRepository.save(member);
        Member result = memberRepository.findById(savedId);

        langRepository.save(lang1);
        langRepository.save(lang2);
        lang1.setMember(member);
        lang2.setMember(member);
        member.getLanguages().add(lang1);
        member.getLanguages().add(lang2);

        //then
        assertThat(member).isEqualTo(result);
        assertThat(member.getLanguages().size()).isEqualTo(2);
        assertThat(member.getLanguages()).contains(lang1, lang2);
    }

    @Test
    @Transactional
    @Rollback
    void findAll() {
        //given
        Member member1 = new Member("kim1", 1, PositionEnum.BackEnd);
        Member member2 = new Member("kim2", 2, PositionEnum.FrontEnd);
        Member member3 = new Member("kim3", 3, PositionEnum.FullStack);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        //when
        List<Member> resultList = memberRepository.findAll();
        //then
        assertThat(resultList).contains(member1, member2, member3);
    }
}