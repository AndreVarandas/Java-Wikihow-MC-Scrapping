
import java.awt.List;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Helpers.SQLIteConnector;
import source.Article;

public class jsoupRun {

	private static final String url = "http://www.wikihow.com/wikiHowTo?search=minecraft";

	public static void main(String[] args) throws IOException {

		Document d = Jsoup.connect(url).timeout(12000).get();
		Elements elements = d.select("div#searchresults_list");

		SQLIteConnector connector = new SQLIteConnector();
		connector.init();

		ArrayList<Article> articles = new ArrayList<>();

		for (Element element : elements.select("div.result")) {
			String img_url = element.select("div.result_thumb img").attr("src");
			String title = element.select("div.result_data a").text();
			String url = element.select("div.result_data a").attr("href");

			Article article = new Article(title, url, img_url);
			articles.add(article);
		}

		for (Iterator<Article> iterator = articles.iterator(); iterator.hasNext();) {
			Article article = (Article) iterator.next();
			System.out.printf("Article: %s, %s, %s\n", article.getmTitle(), article.getmUrl(), article.getmImgUrl());
			connector.InsertArticle(article);
		}
	}
}
