package voicesplit.learning.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Subject;
import voicesplit.learning.domain.WebSite;
import voicesplit.learning.form.WebSiteUpdateForm;
import voicesplit.learning.repository.SubjectRepository;
import voicesplit.learning.repository.WebSiteRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WebsiteServiceTest {

    @Autowired
    WebsiteService websiteService;

    @Autowired
    SubjectRepository subjectRepository;

    @Test
    void saveAndFindById() {
        //given
        WebSite webSite = new WebSite("인프런", "https://inflearn.com");

        //when
        Long savedId = websiteService.save(webSite);
        WebSite findWebSite = websiteService.findById(savedId);

        //then
        assertThat(webSite).isEqualTo(findWebSite);
    }

    @Test
    void findAll() {
        //given
        WebSite webSite1 = new WebSite("인프런", "https://inflearn.com");
        WebSite webSite2 = new WebSite("인프런2", "https://inflearn2.com");
        websiteService.save(webSite1);
        websiteService.save(webSite2);

        //when
        List<WebSite> webSiteList = websiteService.findAll();

        //then
        assertThat(webSiteList.size()).isEqualTo(2);
        assertThat(webSiteList).contains(webSite1, webSite2);
    }

    @Test
    void updateSite() {
        //given
        WebSite webSite1 = new WebSite("인프런", "https://inflearn.com");
        Long savedId = websiteService.save(webSite1);
        WebSiteUpdateForm webSiteUpdateForm = new WebSiteUpdateForm(webSite1);
        webSiteUpdateForm.setSiteName("이름수정");
        webSiteUpdateForm.setSiteURL("URL수정");

        //when
        websiteService.updateSite(savedId, webSiteUpdateForm);
        WebSite findSite = websiteService.findById(savedId);

        //then
        assertThat(findSite.getSiteName()).isEqualTo("이름수정");
        assertThat(findSite.getSiteURL()).isEqualTo("URL수정");
    }

    @Test
    void removeById() {
        //given
        WebSite webSite1 = new WebSite("인프런", "https://inflearn.com");
        Long savedId = websiteService.save(webSite1);
        WebSite findSiteBefore = websiteService.findById(savedId);
        assertThat(findSiteBefore).isNotNull();

        //when
        websiteService.removeById(savedId);
        WebSite findSiteAfter = websiteService.findById(savedId);

        //then
        assertThat(findSiteAfter).isNull();
    }

    @Test
    void addSubject() {
        //given
        WebSite webSite = new WebSite("인프런", "https://inflearn.com");
        Long savedWebsiteId = websiteService.save(webSite);
        Subject subject = new Subject("강의1", "선생1", 30);
        Long savedSubjectId = subjectRepository.save(subject);

        //when
        WebSite findWebSite = websiteService.findById(savedWebsiteId);
        webSite.addSubject(subject);

        //then
        assertThat(webSite.getSubjects().size()).isEqualTo(1);
        assertThat(subject.getSite()).isEqualTo(findWebSite);
    }
}