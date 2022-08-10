package voicesplit.learning.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private Long id;
    private String username;
    private Integer age;
    private String position;
    private String mainLang;
    private String subLang;
}
