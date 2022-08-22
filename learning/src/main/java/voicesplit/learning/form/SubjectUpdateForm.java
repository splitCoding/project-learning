package voicesplit.learning.form;

import lombok.Getter;
import lombok.Setter;
import voicesplit.learning.domain.Subject;

@Getter
@Setter
public class SubjectUpdateForm {
    private Long id;
    private Long siteId;
    private String name;
    private String tutor;
    private Integer total;

    public SubjectUpdateForm() {
    }

    public SubjectUpdateForm(Subject subject) {
        id = subject.getId();
        name = subject.getName();
        tutor = subject.getTutor();
        total = subject.getTotal();
    }
}
