package com.mlimavieira.buildit.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mlimavieira.buildit.crawler.repository.LinkRepository;
import com.mlimavieira.buildit.crawler.sitemap.Link;

@RestController
public class LinkController {

	@Autowired
	private LinkRepository repository;

	@RequestMapping(value = "/crawler/links", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getLinks(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "offset", defaultValue = "10") int offset) {

		Page<Link> findAll = repository.findAll(PageRequest.of(page, offset));

		return new Response(findAll.getNumber(), findAll.getTotalElements(), findAll.getContent());
	}
}
