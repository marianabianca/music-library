package br.edu.ufcg.labsi;

import br.edu.ufcg.labsi.tokenFilter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class LabsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabsiApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
            public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

	@Bean
	public FilterRegistrationBean filtroJWT() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		Collection<String> patterns = new ArrayList<>();
        patterns.add("/albuns/**");
        patterns.add("/artistas/**");
        patterns.add("/musicas/**");
        patterns.add("/nota/**");
		patterns.add("/playlists/**");
		patterns.add("/ultima-musica/**");
		frb.setUrlPatterns(patterns);
		return frb;
	}
}
