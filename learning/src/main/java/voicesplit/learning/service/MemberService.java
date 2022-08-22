package voicesplit.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.Member;
import voicesplit.learning.domain.MemberAndWebSite;
import voicesplit.learning.domain.WebSite;
import voicesplit.learning.form.MemberUpdateForm;
import voicesplit.learning.form.WebSiteForm;
import voicesplit.learning.repository.MemberAndWebSiteRepository;
import voicesplit.learning.repository.MemberRepository;
import voicesplit.learning.repository.WebSiteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WebSiteRepository webSiteRepository;

    @Autowired
    MemberAndWebSiteRepository memberAndWebSiteRepository;

    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
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

    public void updateMember(Long id, MemberUpdateForm update) {
        System.out.println("update.getId() = " + update.getId());
        Member findMember = memberRepository.findById(id);
        findMember.setUsername(update.getUsername());
        findMember.setAge(update.getAge());
        findMember.setPosition(update.getPosition());
        findMember.setMainLang(update.getMainLang());
        findMember.setSubLang(update.getSubLang());
    }

    public void addSite(Long id, WebSiteForm website) {
        Member findMember = memberRepository.findById(id);
        WebSite webSite = new WebSite(website.getSiteName(), website.getSiteURL());
        MemberAndWebSite memberAndWebSite = new MemberAndWebSite();
        webSiteRepository.save(webSite);
        memberAndWebSiteRepository.save(memberAndWebSite);
        findMember.addSite(memberAndWebSite, webSite);
    }

    public List<String> returnSites(Long memberId) {
        List<String> ret = new ArrayList<>();
        memberAndWebSiteRepository.findByMemberId(memberId).forEach(mw -> ret.add(mw.getSite().getSiteName()));
        return ret;
    }
}
