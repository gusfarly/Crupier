package com.ggastudios.crupier;



import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableAdminServer
@SpringBootApplication
@Import(WebSecuriryConfig.class)
//@EnableEurekaServer
public class CrupierApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrupierApplication.class, args);
	}
}
