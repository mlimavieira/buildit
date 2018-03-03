package com.mlimavieira.buildit.crawler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChekController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getLinks() {

		return "OK!!";
	}
}
