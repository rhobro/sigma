package tech.neurobyte.dev.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public class Currency {
    static {
        try {
            // request currency list page
            Document doc = Jsoup.connect("https://thefactfile.org/countries-currencies-symbols/")
                    .userAgent("Mozilla")
                    .get();

            // CSS select rows in table
            list = new HashMap<>();
            for (Element row : doc.select("table#tablepress-181 > tbody > tr")) {
                var cols = row.select("td");
                var name = cols.get(2).text();
                var symbol = cols.get(4).text();

                // add to hashmap
                Currency.list.put(name, symbol);
            }
        } catch (IOException e) {
            System.exit(1); // TODO sentry
        }
    }
    public static HashMap<String, String> list;
}
