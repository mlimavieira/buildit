package com.mlimavieira.buildit.crawler.wippro;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mlimavieira.buildit.crawler.repository.LinkRepository;
import com.mlimavieira.buildit.crawler.sitemap.Link;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.TextParseData;
import edu.uci.ics.crawler4j.url.WebURL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WipprodigitalCallback {

	private WiprodigitalCallback callback;
	LinkRepository linkRepoMock = Mockito.mock(LinkRepository.class);

	@Before
	public void setup() {
		callback = new WiprodigitalCallback(linkRepoMock);
	}

	@Test
	public void handleNullPage() {
		callback.handle(null);
	}

	@Test
	public void handleWrongParseData() {

		final WebURL webURL = new WebURL();
		webURL.setURL("www.invali.com");

		Page page = new Page(webURL);

		page.setParseData(new TextParseData());

		callback.handle(page);

		ArgumentCaptor<Link> linkArgument = ArgumentCaptor.forClass(Link.class);
		verify(linkRepoMock, times(1)).save(linkArgument.capture());
	}

	@Test
	public void handleParseDataNullOutgoingUrls() {

		final WebURL webURL = new WebURL();
		webURL.setURL("www.invali.com");
		Page page = new Page(webURL);
		page.setParseData(new HtmlParseData());

		callback.handle(page);

		ArgumentCaptor<Link> linkArgument = ArgumentCaptor.forClass(Link.class);
		verify(linkRepoMock, times(1)).save(linkArgument.capture());
	}

	@Test
	public void handleParseDataNotEmptyOutgoingUrls() {

		final WebURL webURL = new WebURL();
		webURL.setURL("www.invali.com");
		Page page = new Page(webURL);
		HtmlParseData htmlParseData = new HtmlParseData();

		Set<WebURL> outgoingUrls = new HashSet<>();
		WebURL webUrl = new WebURL();
		webUrl.setURL("www.outgoing.url");
		outgoingUrls.add(webUrl);

		htmlParseData.setOutgoingUrls(outgoingUrls);

		page.setParseData(htmlParseData);

		callback.handle(page);

		ArgumentCaptor<Link> linkArgument = ArgumentCaptor.forClass(Link.class);
		verify(linkRepoMock, times(1)).save(linkArgument.capture());
	}
}
