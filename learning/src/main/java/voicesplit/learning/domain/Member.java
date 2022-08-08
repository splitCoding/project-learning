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

    //사용하는 언어
    @OneToMany(mappedBy = "member")
    private List<Language> languages = new ArrayList<>();

    //강의를 듣는 사이트
    @OneToMany(mappedBy = "member")
    private List<WebSite> sites = new ArrayList<>();

    public Member(String userName, int age, String position) {
        this.userName = userName;
        this.age = age;
        this.position = position;
    }

    public void addLang(Language lang) {
        languages.add(lang);
        lang.setMember(this);
    }

    public void addWebSite(WebSite site) {
        sites.add(site);
        site.setMember(this);
    }
}


