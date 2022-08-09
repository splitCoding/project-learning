package voicesplit.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import voicesplit.learning.domain.Member;
import voicesplit.learning.service.MemberService;

import java.util.List;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private final MemberService memberService = new MemberService();

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "member/createMemberForm";
    }

    @PostMapping("/new")
    public String join(MemberForm form) {
        Member member = new Member(form.getUsername(), form.getAge(), form.getPosition(),
                form.getMainLang(), form.getSubLang());
        memberService.join(member);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String success(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "member/memberList";
    }

}
