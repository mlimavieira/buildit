package com.mlimavieira.buildit.crawler.wippro;

import java.util.regex.Pattern;

import com.mlimavieira.buildit.crawler.CrawlerCallback;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

public class WiprodigitalCrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js))$");

	public CrawlerCallback callback;

	public WiprodigitalCrawler(CrawlerCallback callback) {
		this.callback = callback;
	}

	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();

		return !FILTERS.matcher(href).matches() && url.getDomain().contains("wiprodigital");
	}

	@Override
	public void visit(Page page) {

		callback.handle(page);

	}
}
