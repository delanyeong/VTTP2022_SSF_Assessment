package vttp2022.ssf.task1.Service;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.ssf.task1.Model.Article;
import vttp2022.ssf.task1.Repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepo;
    
    private String URL = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    @Value ("${API_KEY}")
    private String apiKey;

    public List<Article> getArticles() {

        String url = UriComponentsBuilder.fromUriString(URL)
        .queryParam("api_key", apiKey)
        .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        if (resp.getStatusCodeValue() != 200) {
            System.err.println("Error status code is not");
            return Collections.emptyList();
        }

        String payload = resp.getBody();
        // System.out.println("payload: " + payload);

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject jo = jsonReader.readObject();

        JsonArray newsDataArray = jo.getJsonArray("Data");

        List<Article> articleList = new LinkedList<>();

        for (int i = 0; i < newsDataArray.size(); i++) {
            JsonObject newsDataElements = newsDataArray.getJsonObject(i); // bc gifData is an Array
            articleList.add(Article.createArticle(newsDataElements));
        }
         
        return articleList;
    }

    public void saveArticles (List<Article> usArtList) {

        List<String> saveArtList = new LinkedList<>();

        for (int i = 0; i < usArtList.size(); i++) {
            saveArtList.add(usArtList.get(i).toJson().toString());
        }

        newsRepo.save(saveArtList);
    }

    public Optional<Article> getArticleById (String id) {
        
        String result = newsRepo.get(id);

        if (null == result)
            return Optional.empty();

        return Optional.of(Article.create(result));
    }


}
