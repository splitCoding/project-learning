package voicesplit.learning.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Subject;
import voicesplit.learning.domain.WebSite;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class WebSiteRepositoryTest {

    @Autowired
    WebSiteRepository webSiteRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Test
    void save() {
        //given
        WebSite webSite = new WebSite("Inflearn");
        Subject subject1 = new Subject("스프링1", "김영한", 10);
        Subject subject2 = new Subject("스프링2", "김영한", 12);

        //when
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        Long savedId = webSiteRepository.save(webSite);
        webSite.addSubject(subject1);
        webSite.addSubject(subject2);
        WebSite result = webSiteRepository.findById(savedId);

        //then
        assertThat(webSite).isEqualTo(result);
        assertThat(result.getSubjects()).contains(subject1, subject2);
    }

    @Test
    void findByName() {
        //given
        WebSite webSite = new WebSite("Inflearn");
        webSiteRepository.save(webSite);
        //when
        WebSite result = webSiteRepository.findByName("Inflearn");
        //then
        assertThat(webSite).isEqualTo(result);
    }

    @Test
    void findAll() {
        //given
        WebSite webSite1 = new WebSite("FastCampus");
        WebSite webSite2 = new WebSite("Inflearn");
        webSiteRepository.save(webSite1);
        webSiteRepository.save(webSite2);
        //when
        List<WebSite> resultList = webSiteRepository.findAll();
        //then
        assertThat(resultList).contains(webSite1, webSite2);

    }
}