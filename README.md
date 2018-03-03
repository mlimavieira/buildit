# Project Title

BuildIt Web Crawler

## Getting Started

In order to see the crawler content execute in browser 

http://localhost:8080/crawler/links?page={page}&offset={offset}

	* page - Current page it starts in ZERO (0)
	* offset - Total of results displayed 

## Build and Run

#### Using maven to build and run
	i - mvn spring-boot:run

#### Build and Run using java command
	i - mvn clean install
	ii - java -jar buildit/target/buildit-0.0.1-SNAPSHOT.jar
 
## Trade offs
	* The application does not support multiple nodes once the pages crawled are not externalized.
	* Embedded MongoDB
	* Hard coded configuration

## Improvements
	* Externalize configurations
	* Dedicated MongoDB instead embedded 
	