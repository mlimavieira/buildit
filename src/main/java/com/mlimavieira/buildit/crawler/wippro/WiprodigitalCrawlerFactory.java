package com.mlimavieira.buildit.crawler.wippro;

import com.mlimavieira.buildit.crawler.CrawlerCallback;

import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory;

public class WiprodigitalCrawlerFactory implements WebCrawlerFactory<WiprodigitalCrawler> {

	private CrawlerCallback callback;

	public WiprodigitalCrawlerFactory(CrawlerCallback callback) {
		this.callback = callback;
	}

	@Override
	public WiprodigitalCrawler newInstance() throws Exception {
		return new WiprodigitalCrawler(callback);
	}

}
