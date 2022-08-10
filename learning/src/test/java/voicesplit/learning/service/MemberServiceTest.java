package voicesplit.learning.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Member;
import voicesplit.learning.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void join() {
        //given
        Member member = new Member("kim", 1, "Front", "Java", "Python");
        //when
        Long savedId = memberService.join(member);
        //then
        Member result = memberRepository.findById(savedId);
        assertThat(member).isEqualTo(result);
    }

    @Test
    void findById() {
        //given
        Member member = new Member("kim", 1, "Front", "Java", "Python");
        Long savedId = memberRepository.save(member);
        //when
        Member result = memberService.findById(savedId);
        //then
        assertThat(member).isEqualTo(result);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("kim1", 1, "Front","Java", "Python");
        Member member2 = new Member("kim2", 2, "Back","Java", "Python");
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> resultList = memberService.findAll();
        //then
        assertThat(resultList).contains(member1, member2);
    }

    @Test
    void removeById(){
        //given
        Member member1 = new Member("kim1", 1, "BACK_END", "Java", "Python");
        Long savedId = memberRepository.save(member1);
        //when
        memberService.removeById(savedId);
        Member result = memberRepository.findById(savedId);
        //then
        assertThat(result).isNull();
    }
}