package voicesplit.learning.domain;


import lombok.Getter;
import lombok.Setter;
import voicesplit.learning.repository.WebSiteRepository;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "videoClass_id")
    private Long id;

    //강의명
    private String name;

    //강사
    private String tutor;

    //사이트
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private WebSite site;

    //총강의 수
    private int total;

    public Subject(String name, String tutor, int total) {
        this.name = name;
        this.tutor = tutor;
        this.total = total;
    }
}
