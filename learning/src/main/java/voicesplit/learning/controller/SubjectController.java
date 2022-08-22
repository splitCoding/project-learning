package voicesplit.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import voicesplit.learning.domain.Subject;
import voicesplit.learning.domain.WebSite;
import voicesplit.learning.form.SubjectForm;
import voicesplit.learning.form.SubjectUpdateForm;
import voicesplit.learning.service.SubjectService;
import voicesplit.learning.service.WebsiteService;

import java.util.List;

@Controller
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService = new SubjectService();

    @Autowired
    WebsiteService websiteService = new WebsiteService();

    @GetMapping("new")
    public String form(Model model) {
        SubjectForm subjectForm = new SubjectForm();
        model.addAttribute("siteList", websiteService.findAll());
        model.addAttribute("subjectForm", subjectForm);
        return "subject/createSubjectForm";
    }

    @PostMapping("new")
    public String save(@ModelAttribute SubjectForm subjectForm) {
        Subject subject = new Subject(subjectForm);
        subjectService.save(subject);
        WebSite findSite = websiteService.findById(subjectForm.getSiteId());
        websiteService.addSubject(findSite, subject);
        return "redirect:/subject/list";
    }

    @GetMapping("list")
    public String subjectList(Model model) {
        List<Subject> subjectList = subjectService.findAll();
        model.addAttribute("subjects", subjectList);
        return "subject/subjectList";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Subject findSubject = subjectService.findById(id);
        SubjectUpdateForm subjectUpdateForm = new SubjectUpdateForm(findSubject);
        subjectUpdateForm.setSiteId(findSubject.getSite().getId());
        model.addAttribute("subjectUpdateForm", subjectUpdateForm);
        model.addAttribute("siteList", websiteService.findAll());
        return "subject/subjectEdit";
    }

    @PostMapping("{subjectId}/edit")
    public String post_edit(@PathVariable("subjectId") Long id, @ModelAttribute SubjectUpdateForm updateForm) {
        subjectService.update(id, updateForm);
        return "redirect:/subject/list";
    }

    @PostMapping("{subjectId}/delete")
    public String delete(@PathVariable("subjectId") Long id){
        subjectService.removeById(id);
        return "redirect:/subject/list";
    }
}

