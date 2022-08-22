package voicesplit.learning.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Subject;
import voicesplit.learning.form.SubjectUpdateForm;
import voicesplit.learning.repository.SubjectRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class SubjectServiceTest {

    @Autowired
    SubjectService subjectService;

    @Test
    void saveAndFindById() {
        //given
        Subject subject = new Subject("강의", "선생님", 1);

        //when
        Long savedId = subjectService.save(subject);
        Subject findSubject = subjectService.findById(savedId);

        //then
        assertThat(subject).isEqualTo(findSubject);
    }

    @Test
    void findAll() {
        //given
        Subject subject1 = new Subject("강의", "선생님", 1);
        Subject subject2 = new Subject("강의2", "선생님2", 2);
        subjectService.save(subject1);
        subjectService.save(subject2);

        //when
        List<Subject> subjectList = subjectService.findAll();

        //then
        assertThat(subjectList.size()).isEqualTo(2);
        assertThat(subjectList).contains(subject1, subject2);
    }

    @Test
    void removeById() {
        //given
        Subject subject = new Subject("강의", "선생님", 1);
        Long savedId = subjectService.save(subject);
        subjectService.findById(savedId);
        Subject findSubject1 = subjectService.findById(savedId);

        //when
        subjectService.removeById(savedId);
        Subject findSubject2 = subjectService.findById(savedId);

        //then
        assertThat(findSubject1).isEqualTo(subject);
        assertThat(findSubject2).isNull();
    }

    @Test
    @Rollback(value = false)
    void update() {
        //given
        Subject subject = new Subject("강의", "선생님", 1);
        Long savedId = subjectService.save(subject);
        SubjectUpdateForm updateForm = new SubjectUpdateForm(subject);
        updateForm.setName("수정후 강의");
        updateForm.setTutor("수정후 선생님");
        updateForm.setTotal(10);

        //when
        subjectService.update(savedId, updateForm);
        Subject findSubject = subjectService.findById(savedId);

        //then
        assertThat(findSubject.getName()).isEqualTo("수정후 강의");
        assertThat(findSubject.getTutor()).isEqualTo("수정후 선생님");
        assertThat(findSubject.getTotal()).isEqualTo(10);
    }
}