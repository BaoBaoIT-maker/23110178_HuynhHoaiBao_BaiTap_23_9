package vn.iot.star;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import vn.iot.star.config.StorageProperties;
import vn.iot.star.service.IStorageService;

@SpringBootApplication(scanBasePackages = {"vn.iot.star.controller"})
@EnableConfigurationProperties(StorageProperties.class) // thêm cấu hình storage
@Configuration
@ComponentScan
public class Project16920251Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Project16920251Application.class, args);
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Project16920251Application.class);
    }
	@Bean
	CommandLineRunner init(IStorageService storageService) {
	return (args -> {
	storageService.init();
	});
	}
	
}
