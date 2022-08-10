package voicesplit.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import voicesplit.learning.domain.Member;
import voicesplit.learning.form.MemberForm;
import voicesplit.learning.form.MemberUpdateForm;
import voicesplit.learning.service.MemberService;

import java.util.List;

@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private final MemberService memberService = new MemberService();

    @GetMapping("/new")
    public String memberForm(Model model) {
        //모델에 템플렛에서 사용할 MemberForm 객체를 넣어서 템플릿을 불러온다.
        model.addAttribute("memberForm", new MemberForm());
        return "member/createMemberForm";
    }

    @PostMapping("/new")
    public String join(MemberForm form) {
        //템플릿에서 다시 받아온 MemberForm 객체안에 값들을 이용하여 Member 객체를 생성후 join 한다.
        Member member = new Member(form.getUsername(), form.getAge(), form.getPosition(),
                form.getMainLang(), form.getSubLang());
        memberService.join(member);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        //모든 회원을 저장한 members 리스트를 모델에 넣어 템플릿을 불러온다
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "member/memberList";
    }

    @GetMapping("/{userId}/edit")
    public String showUser(@PathVariable("userId") Long userId, Model model) {
        //localhost:8080/member/{userId}
        //userId에 해당되는 멤버를 조회하여 모델에 넣어 템플릿을 불러온다.
        Member findMember = memberService.findById(userId);
        MemberUpdateForm updateForm = new MemberUpdateForm(findMember);
        model.addAttribute("memberId", userId);
        model.addAttribute("memberForm", updateForm);
        return "member/memberEdit";
    }

    @PostMapping("/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long id, @ModelAttribute("memberForm") MemberUpdateForm update) {
        memberService.updateMember(id, update);
        return "redirect:/member/list";
    }

    @PostMapping("/{userId}/delete")
    public String removeUser(@PathVariable("userId") Long id, Model model){
        memberService.removeById(id);
        return "redirect:/member/list";
    }
}
