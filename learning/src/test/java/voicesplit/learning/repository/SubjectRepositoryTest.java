package voicesplit.learning.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Subject;
import voicesplit.learning.domain.WebSite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class SubjectRepositoryTest {
    @Autowired
    SubjectRepository subjectRepository;

    @Test
    void save() {
        //given
        Subject subject = new Subject("스프링", "김영한", 10);
        //when
        Long savedId = subjectRepository.save(subject);
        //then
        Subject result = subjectRepository.findById(savedId);
        assertThat(subject).isEqualTo(result);
    }

    @Test
    void findByName() {
        //given
        Subject subject = new Subject("스프링", "김영한", 10);
        subjectRepository.save(subject);
        //when
        Subject result = subjectRepository.findByName("스프링");
        //then
        assertThat(subject).isEqualTo(result);
    }

    @Test
    void findAll() {
        //given
        Subject subject1 = new Subject("스프링", "김영한",10);
        Subject subject2 = new Subject("스프링2", "김영한", 12);

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        //when
        List<Subject> resultList = subjectRepository.findAll();
        //then
        assertThat(resultList).contains(subject1, subject2);

    }

    @Test
    void removeById(){
        //given
        Subject subject1 = new Subject("스프링", "김영한",10);
        Long savedId = subjectRepository.save(subject1);
        Subject findSubject1 = subjectRepository.findById(savedId);

        //when
        subjectRepository.removeById(savedId);
        Subject findSubject2 = subjectRepository.findById(savedId);

        //then
        assertThat(findSubject1).isNotNull();
        assertThat(findSubject2).isNull();

    }
}