import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://thefactfile.org/countries-currencies-symbols/")
                    .userAgent("Mozilla")
                    .get();
            for (Element row : doc.select("table#tablepress-181 > tbody > tr")) {
                var cols = row.select("td");
                var name = cols.get(2).text();
                var symbol = cols.get(4).text();
            }
        } catch (IOException e) {
            System.exit(1);
        }
    }
}
