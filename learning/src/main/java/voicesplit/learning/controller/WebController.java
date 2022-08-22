package voicesplit.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import voicesplit.learning.domain.Subject;
import voicesplit.learning.domain.WebSite;
import voicesplit.learning.form.WebSiteForm;
import voicesplit.learning.form.WebSiteUpdateForm;
import voicesplit.learning.service.SubjectService;
import voicesplit.learning.service.WebsiteService;

import java.util.List;

@Controller
@RequestMapping("site")
public class WebController {
    @Autowired
    private WebsiteService websiteService = new WebsiteService();

    @GetMapping("new")
    public String create(Model model) {
        model.addAttribute("siteForm", new WebSiteForm());
        return "site/createWebSiteForm";
    }

    @PostMapping("new")
    public String save(@ModelAttribute("siteForm") WebSiteForm form) {
        WebSite site = new WebSite(form.getSiteName(), form.getSiteURL());
        websiteService.save(site);
        return "redirect:/site/list";
    }

    @GetMapping("list")
    public String list(Model model) {
        List<WebSite> sites = websiteService.findAll();
        model.addAttribute("sites", sites);
        return "site/siteList";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        WebSite site = websiteService.findById(id);
        WebSiteUpdateForm updateForm = new WebSiteUpdateForm(site);
        model.addAttribute("updateForm", updateForm);
        return "site/siteEdit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id, @ModelAttribute("updateForm") WebSiteUpdateForm form) {
        websiteService.updateSite(id, form);
        return "redirect:/site/list";
    }

    @PostMapping("/{Id}/delete")
    public String delete(@PathVariable("Id") Long siteId) {
        websiteService.removeById(siteId);
        return "redirect:/site/list";
    }

}
