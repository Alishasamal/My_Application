package com.example.myapplication;

import org.json.JSONObject;

public class Article {
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;
    public String content;

    public Source source;

    public static Article parseJSONObject(JSONObject jsonObject){
        Article item = new Article();

        item.author = jsonObject.optString("author");
        item.content = jsonObject.optString("content");
        item.description = jsonObject.optString("description");
        item.title = jsonObject.optString("title");
        item.publishedAt = jsonObject.optString("publishedAt");
        item.url = jsonObject.optString("url");
        item.urlToImage = jsonObject.optString("urlToImage");

        JSONObject sourceObject = jsonObject.optJSONObject("source");
        item.source = Source.parseSourceJSON(sourceObject);

        return item;
    }
}
