package voicesplit.learning.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    //회원명
    private String userName;

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

    private Member(){
    }

    public Member(String userName, int age, String position, String mainLang, String subLang) {
        this.userName = userName;
        this.age = age;
        this.position = position;
        this.mainLang = mainLang;
        this.subLang = subLang;
    }

    public void addWebSite(WebSite site) {
        sites.add(site);
        site.setMember(this);
    }
}


