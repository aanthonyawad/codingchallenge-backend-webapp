package de.iplytics.codingchallenge_backend_webapp;

import org.apache.solr.client.solrj.SolrClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;

@SpringBootApplication
public class CodingchallengeBackendWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingchallengeBackendWebappApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



}
