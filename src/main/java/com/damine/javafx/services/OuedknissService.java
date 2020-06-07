package com.damine.javafx.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class OuedknissService {

    public static void save(JSONArray List,String name){
        //Write JSON file
        try (FileWriter file = new FileWriter(name+".json")) {
            file.write(List.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getList(String url){
        try {
            JSONArray List = new JSONArray();
            Document document = Jsoup.connect(url).userAgent("Chrome/83.0.4103.96").get();
            Elements select = document.body().select("ul");
            int i=0;
            for (Element s : select) {
                JSONObject Details = new JSONObject();
                i++;
                Details.put("id",i);
                Details.put("titre",s.select("li.annonce_titre").select("a").select("h2").text());
                try {
                    Details.put("image",s.select("li.annonce_image").select("div.annonce_image_img").attr("style").split(" ")[0].substring(15,s.select("li.annonce_image").select("div.annonce_image_img").attr("style").split(" ")[0].length()-1));
                }catch (StringIndexOutOfBoundsException e){

                }
                Details.put("description",s.select("li.annonce_text").select("span.annonce_get_description").text());
                Details.put("prix",s.select("li.annonce_text").select("span.annonce_prix").text());
                Details.put("numero",s.select("li.annonce_text").select("span.annonce_numero").text());
                List.put(Details);
            }
            return List;
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.print(getList("https://howtodoinjava.com/library/json-simple-read-write-json-examples/").toString());

    }
}
