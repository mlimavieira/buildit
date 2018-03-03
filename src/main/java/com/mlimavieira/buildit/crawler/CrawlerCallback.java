package com.mlimavieira.buildit.crawler;

import edu.uci.ics.crawler4j.crawler.Page;

public interface CrawlerCallback {

	void handle(Page page);
}
