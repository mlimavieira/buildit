package com.mlimavieira.buildit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mlimavieira.buildit.crawler.CrawlerCallback;
import com.mlimavieira.buildit.crawler.repository.LinkRepository;
import com.mlimavieira.buildit.crawler.wippro.WiprodigitalCallback;
import com.mlimavieira.buildit.crawler.wippro.WiprodigitalCrawlerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@SpringBootApplication
public class BuilditApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(BuilditApplication.class);

	private static final int NUMBER_OF_CRAWLERS = 5;
	private static final String CRAWLER_STORAGE_FOLDER = "crawler";

	private static final int DELAY_BETWEEN_REQUESTS = 100;

	private static final int MAX_DEPTH_OF_CRAWLING = 2;

	private static final int MAX_PAGES_TO_FETCH = 1000;

	@Autowired
	private LinkRepository linkRepository;

	public static void main(String[] args) {
		System.setProperty("https.protocols", "TLSv1.1");
		SpringApplication.run(BuilditApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LOGGER.info("Starting Crawler ");

		try {
			CrawlConfig config = new CrawlConfig();
			config.setCrawlStorageFolder(CRAWLER_STORAGE_FOLDER);

			config.setPolitenessDelay(DELAY_BETWEEN_REQUESTS);
			config.setMaxDepthOfCrawling(MAX_DEPTH_OF_CRAWLING);
			config.setMaxPagesToFetch(MAX_PAGES_TO_FETCH);

			config.setIncludeHttpsPages(true);
			config.setIncludeBinaryContentInCrawling(false);
			config.setResumableCrawling(false);

			PageFetcher pageFetcher = new PageFetcher(config);
			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
			RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
			CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

			controller.addSeed("https://wiprodigital.com");

			CrawlerCallback callback = new WiprodigitalCallback(linkRepository);
			controller.startNonBlocking(new WiprodigitalCrawlerFactory(callback), NUMBER_OF_CRAWLERS);

			while (!controller.isFinished()) {
				Thread.sleep(5000);
			}

		} catch (Exception e) {
			LOGGER.error("Something went wrong :(", e);
		} finally {
			LOGGER.info("Resuming Crawler ");
		}
	}

}
