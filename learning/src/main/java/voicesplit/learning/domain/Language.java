package voicesplit.learning.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Language {

    @Id
    @GeneratedValue
    @Column(name = "lang_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private LanguageEnum langName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Language(LanguageEnum langName) {
        this.langName = langName;
    }
}
