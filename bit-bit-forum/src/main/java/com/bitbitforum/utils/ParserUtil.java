package com.bitbitforum.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParserUtil {

    private static Document getPage() throws IOException {
        String url = "https://habr.com/ru/news/";
        return (Document) Jsoup.parse(new URL(url), 10000);
    }

    public static ArrayList<Map<String, String>> parse() throws IOException {
        ArrayList<Map<String, String>> resultList = new ArrayList<>();

        Document document = getPage();
        var elements = document.select("article > div.article-snippet");

        for (var element : elements) {
            Map<String, String> map = new HashMap<>();
            map.put("author", element.select("div.meta-container > div.meta > span > span > a.tm-user-info__username").text());
            map.put("timeLeft", element.select("div.meta-container > div.meta > span > span > a > time").text());
            map.put("title", element.select("h2 > a > span").text());
            map.put("link", "https://habr.com" + element.select("h2 > a").attr("href"));

            resultList.add(map);
        }

        return resultList;
    }

}
