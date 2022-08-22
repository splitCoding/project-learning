package voicesplit.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Subject;
import voicesplit.learning.form.SubjectUpdateForm;
import voicesplit.learning.repository.SubjectRepository;
import voicesplit.learning.repository.WebSiteRepository;

import java.util.List;

@Service
@Transactional
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    WebSiteRepository webSiteRepository;

    public Long save(Subject subject) {
        Long savedId = subjectRepository.save(subject);
        return savedId;
    }

    public Subject findById(Long id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public void removeById(Long id){
        subjectRepository.removeById(id);
    }

    public void update(Long id, SubjectUpdateForm updateForm) {
        Subject findSubject = subjectRepository.findById(id);

        //사이트가 변경되었을 경우
        if (findSubject.getSite() != null && updateForm.getSiteId() != findSubject.getSite().getId()) {
            findSubject.updateSite(webSiteRepository.findById(updateForm.getSiteId()));
        }

        findSubject.setName(updateForm.getName());
        findSubject.setTotal(updateForm.getTotal());
        findSubject.setTutor(updateForm.getTutor());
    }
}
