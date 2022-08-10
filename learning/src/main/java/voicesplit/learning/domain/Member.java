package voicesplit.learning.domain;

import lombok.Getter;
import lombok.Setter;
import voicesplit.learning.form.MemberUpdateForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    //회원명
    private String username;

    //회원나이
    private int age;

    //지원포지션
    private String position;

    //주 언어
    private String mainLang;

    //서브 언어
    private String subLang;

    //강의를 듣는 사이트
    @OneToMany(mappedBy = "member")
    private List<WebSite> sites = new ArrayList<>();

    //오류방지 생성자
    private Member() {
    }

    public Member(String username, int age, String position, String mainLang, String subLang) {
        this.username = username;
        this.age = age;
        this.position = position;
        this.mainLang = mainLang;
        this.subLang = subLang;
    }

    //웹사이트 추가세팅 메서드
    public void addWebSite(WebSite site) {
        sites.add(site);
        site.setMember(this);
    }
}


