package vttp2022.ssf.task1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.ssf.task1.Model.Article;
import vttp2022.ssf.task1.Service.NewsService;

import java.util.Optional;


@RestController
@RequestMapping(path="news", produces=MediaType.APPLICATION_JSON_VALUE)
public class NewsRestController {

    @Autowired
    private NewsService newsSvc;
    
    @GetMapping(value="{id}")
    public ResponseEntity<String> getArticle (@PathVariable String id) {

        Optional<Article> opt = newsSvc.getArticleById(id);

        if (opt.isEmpty()) {
            JsonObject err = Json.createObjectBuilder()
                .add("error", "Cannot find news article %s".formatted(id))
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(err.toString());
        }

        Article article = opt.get();
        return ResponseEntity.ok(article.toJson().toString());
    }
}
