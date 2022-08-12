package vttp2022.ssf.task1.Controller;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.ssf.task1.Model.Article;
import vttp2022.ssf.task1.Service.NewsService;

@Controller
@RequestMapping
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsSvc;
    
    @GetMapping
    public String getArticles (Model model) {

        List<Article> articleList = newsSvc.getArticles();
        
        model.addAttribute("articleList", articleList);

        return "articleResult";

    }

    @PostMapping (path="articles")
    public String postArt (Article[] usArtArray, Model model) {
        logger.info(">>>>>>>>>>>>>>>>>>>> raw request: " + usArtArray);
        // logger.info(">>>>>>>>>>>>>>>>>>>> raw request: " + article);

        List<Article> usArtList = new LinkedList<>();

            for (int i = 0; i < usArtArray.length; i++) {
                usArtList.add(usArtArray[i]);
            }

        newsSvc.saveArticles(usArtList);

        model.addAttribute("usArtList", usArtList);
        
        return "saveResult";
    }


}
