package voicesplit.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Member;
import voicesplit.learning.form.MemberUpdateForm;
import voicesplit.learning.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public void updateMember(Long id, MemberUpdateForm update) {
        Member findMember = memberRepository.findById(id);
        findMember.setUsername(update.getUsername());
        findMember.setAge(update.getAge());
        findMember.setPosition(update.getPosition());
        findMember.setMainLang(update.getMainLang());
        findMember.setSubLang(update.getSubLang());
    }

    public Member findById(Long id) {
        return memberRepository.findById(id);
    }

    public void removeById(Long id) {
        memberRepository.removeById(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
