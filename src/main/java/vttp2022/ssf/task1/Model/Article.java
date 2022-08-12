package vttp2022.ssf.task1.Model;

import jakarta.json.Json;
import jakarta.json.JsonObject;


public class Article {

    private String id;
    private String published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;


    public String getId() {return this.id;}
    public void setId(String id) {this.id = id;}

    public String getPublished_on() {return this.published_on;}
    public void setPublished_on(String published_on) {this.published_on = published_on;}

    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}

    public String getUrl() {return this.url;}
    public void setUrl(String url) {this.url = url;}

    public String getImageurl() {return this.imageurl;}
    public void setImageurl(String imageurl) {this.imageurl = imageurl;}

    public String getBody() {return this.body;}
    public void setBody(String body) {this.body = body;}

    public String getTags() {return this.tags;}
    public void setTags(String tags) {this.tags = tags;}

    public String getCategories() {return this.categories;}
    public void setCategories(String categories) {this.categories = categories;}


    public static Article createArticle (JsonObject jo) {
        
        Article ar = new Article();
        ar.setId(jo.getString("id"));
        ar.setPublished_on(Integer.toString(jo.getInt("published_on")));
        // ar.setPublished_on(jo.getString("published_on"));
        ar.setTitle(jo.getString("title"));
        ar.setUrl(jo.getString("url"));
        ar.setImageurl(jo.getString("imageurl"));
        ar.setBody(jo.getString("body"));
        ar.setTags(jo.getString("tags"));
        ar.setCategories(jo.getString("categories"));

        return ar;
    }

    public JsonObject toJson () {

        JsonObject jo = Json.createObjectBuilder()
        .add("id", id)
        .add("published_on", published_on)
        .add("title", title)
        .add("url", url)
        .add("imageurl", imageurl)
        .add("body", body)
        .add("tags", tags)
        .add("categories", categories)
        .build();

        return jo;
    }

    
}
