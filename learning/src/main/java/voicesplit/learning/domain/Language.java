package voicesplit.learning.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Language {

    @Id
    @GeneratedValue
    @Column(name = "lang_id")
    private Long id;

    //언어명
    private String langName;

    //언어를 사용하는 회원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Language(String langName) {
        this.langName = langName;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
