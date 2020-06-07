package com.damine.javafx.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class JsoupService {


    public void getURLInformation(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
            String title = document.title();
            String location = document.location();
            document.nodeName();

            DocumentType documentType = document.documentType();
            Charset charset = document.charset();
            String outerHtml = document.outerHtml();

            Element body = document.body();
            Element head = document.head();
            Parser parser = document.parser();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Elements getLinks(Document document) {
        return document.select("a[href]");
    }

    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("http://fmi.univ-tiaret.dz/").userAgent("Chrome/83.0.4103.96").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
