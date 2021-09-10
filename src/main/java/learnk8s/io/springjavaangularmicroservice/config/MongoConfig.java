package learnk8s.io.springjavaangularmicroservice.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@ComponentScan (basePackages = {"learnk8s.io.springjavaangularmicroservice"})
@EnableMongoRepositories(basePackages = {"learnk8s.io.springjavaangularmicroservice"})

public class MongoConfig {
    @Autowired
    private Environment env;


    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String dbName;


    public @Bean("mongoClient") MongoClient mongoClient() {
        MongoClient client = MongoClients.create(uri);

        return client;
    }

    @DependsOn("mongoClient")
    public @Bean MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "docsDb");
    }
}
