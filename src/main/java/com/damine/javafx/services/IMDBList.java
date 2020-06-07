package com.damine.javafx.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IMDBList {

    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://www.imdb.com/chart/top/?ref_=nv_mv_250").get();

            Elements select = document.body().select("tbody.lister-list");

            int i=0;
            for (Element element:select.select("tr")) {
                String s="ratingColumn imdbRating";
                Element title = element.select("td.titleColumn").first();
                Element rating = element.select("td.ratingColumn imdbRating").first();
                i++;
                System.out.println("----------------------");
                System.out.println(i+" : "+title.getElementsByTag("a").first().text()+title.getElementsByTag("span").first().text());
                System.out.println(rating.getElementsByTag("strong").first().text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
