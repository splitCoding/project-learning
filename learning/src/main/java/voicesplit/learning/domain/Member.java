package voicesplit.learning.domain;

import lombok.Getter;
import lombok.Setter;

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
    private Long Id;

    private String userName;

    private int age;

    @Enumerated(EnumType.STRING)
    private PositionEnum position;

    @OneToMany(mappedBy = "member")
    private List<Language> languages = new ArrayList<>();

    public Member(String userName, int age, PositionEnum position) {
        this.userName = userName;
        this.age = age;
        this.position = position;
    }
}
