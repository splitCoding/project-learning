package voicesplit.learning.form;

import lombok.Getter;
import lombok.Setter;
import voicesplit.learning.domain.WebSite;

@Getter
@Setter
public class WebSiteUpdateForm {
    private Long id;
    private String siteName;
    private String siteURL;

    public WebSiteUpdateForm(){}

    public WebSiteUpdateForm(WebSite site) {
        id = site.getId();
        siteName = site.getSiteName();
        siteURL = site.getSiteURL();
    }
}
