package com.mlimavieira.buildit.crawler.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mlimavieira.buildit.crawler.sitemap.Link;

public interface LinkRepository extends PagingAndSortingRepository<Link, String> {

}
