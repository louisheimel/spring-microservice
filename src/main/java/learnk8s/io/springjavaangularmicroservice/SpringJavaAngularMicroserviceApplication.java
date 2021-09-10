package learnk8s.io.springjavaangularmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

//@SpringBootApplication(exclude = MongoDataAutoConfiguration.class)
@SpringBootApplication
public class SpringJavaAngularMicroserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringJavaAngularMicroserviceApplication.class, args);
	}

}
