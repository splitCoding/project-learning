package voicesplit.learning.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class WebSite {
    @Id
    @GeneratedValue
    private Long id;

    //사이트명
    private String siteName;

    //사이트 이용 회원
    @OneToMany(mappedBy = "member")
    private List<MemberAndWebSite> members = new ArrayList<>();

    //사이트에 있는 강의들
    @OneToMany(mappedBy = "site")
    private List<Subject> subjects = new ArrayList<>();

    public WebSite(String siteName) {
        this.siteName = siteName;
    }

    //사이트에 강의를 추가하는 메서드
    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.setSite(this);
    }

    public void addMember(MemberAndWebSite webSiteMember, Member member) {
        members.add(webSiteMember);
        webSiteMember.setSite(this);
        member.getSites().add(webSiteMember);
        webSiteMember.setMember(member);
    }
}
