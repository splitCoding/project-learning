package voicesplit.learning.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MemberAndWebSite {
    @Id
    @GeneratedValue
    @Column(name = "memberAndWebSite_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "website_id")
    private WebSite site;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
