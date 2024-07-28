package dev.vanderloureiro.springboot_cache_std;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringbootCacheStdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCacheStdApplication.class, args);
	}

}
