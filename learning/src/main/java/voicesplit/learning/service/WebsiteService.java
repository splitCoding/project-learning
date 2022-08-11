package voicesplit.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import voicesplit.learning.domain.WebSite;
import voicesplit.learning.repository.WebSiteRepository;

import java.util.List;

@Service
@Transactional
public class WebsiteService {
    @Autowired
    WebSiteRepository webSiteRepository;

    public Long save(WebSite webSite) {
        Long savedId = webSiteRepository.save(webSite);
        return savedId;
    }

    public WebSite findById(Long id) {
        return webSiteRepository.findById(id);
    }

    public List<WebSite> findAll() {
        return webSiteRepository.findAll();
    }

    public void removeById(Long id) {
        webSiteRepository.removeById(id);
    }
}
