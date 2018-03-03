package com.mlimavieira.buildit.crawler.wippro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mlimavieira.buildit.crawler.CrawlerCallback;
import com.mlimavieira.buildit.crawler.repository.LinkRepository;
import com.mlimavieira.buildit.crawler.sitemap.Link;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;

public class WiprodigitalCallback implements CrawlerCallback {

	private static final Logger LOGGER = LoggerFactory.getLogger(WiprodigitalCallback.class);

	private LinkRepository linkRepository;

	public WiprodigitalCallback(LinkRepository repo) {
		this.linkRepository = repo;
	}

	public void handle(Page page) {

		if (page == null) {
			LOGGER.warn("Page is null");
			return;
		}

		LOGGER.debug("Crawling: {}", page.getWebURL().getURL());
		Link link = new Link();

		link.setUrl(page.getWebURL().getURL());
		link.setHttpStatus(page.getStatusCode());
		link.setContentType(page.getContentType());

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

			link.setTitle(htmlParseData.getTitle());

			if (htmlParseData.getOutgoingUrls() != null) {
				link.setOutgoingSites(htmlParseData.getOutgoingUrls().size());
			} else {
				link.setOutgoingSites(0);
			}

			link.setMetaTags(htmlParseData.getMetaTags());
		}

		linkRepository.save(link);
	}

}
