package voicesplit.learning.form;

import lombok.Getter;
import lombok.Setter;
import voicesplit.learning.domain.Member;

@Getter
@Setter
public class MemberUpdateForm {
    private Long id;
    private String username;
    private Integer age;
    private String position;
    private String mainLang;
    private String subLang;

    public MemberUpdateForm() {}

    public MemberUpdateForm(Member form) {
        id = form.getId();
        username = form.getUsername();
        age = form.getAge();
        position = form.getPosition();
        mainLang = form.getMainLang();
        subLang = form.getSubLang();
    }
}
