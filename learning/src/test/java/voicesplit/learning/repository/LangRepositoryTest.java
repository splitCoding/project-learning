package voicesplit.learning.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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
@Transactional
class LangRepositoryTest {
    @Autowired
    LangRepository langRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback
    void save() {
        //given
        Language lang = new Language(LanguageEnum.Java);
        Member member1 = new Member("kim1", 1, PositionEnum.BackEnd);
        memberRepository.save(member1);

        //when
        lang.setMember(member1);
        member1.getLanguages().add(lang);
        Long savedId = langRepository.save(lang);
        Language result = langRepository.findById(savedId);

        //then
        assertThat(lang).isEqualTo(result);
    }

    @Test
    @Rollback
    void findAll() {
        //given
        Language lang1 = new Language(LanguageEnum.Java);
        Language lang2 = new Language(LanguageEnum.Java);
        langRepository.save(lang1);
        langRepository.save(lang2);

        //when
        List<Language> resultList = langRepository.findAll();

        //then
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    @Rollback(false)
    void findAllById() {
        //given
        Language lang1 = new Language(LanguageEnum.Java);
        Language lang2 = new Language(LanguageEnum.Java);
        Member member = new Member("kim1", 1, PositionEnum.BackEnd);
        memberRepository.save(member);
        langRepository.save(lang1);
        langRepository.save(lang2);
        member.getLanguages().add(lang1);
        member.getLanguages().add(lang2);
        lang1.setMember(member);
        lang2.setMember(member);

        //when
        List<Language> resultList = langRepository.findAllById(member.getId());

        //then
        assertThat(resultList).contains(lang1, lang2);
    }

}