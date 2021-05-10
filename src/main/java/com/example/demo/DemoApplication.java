package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> routes() {
		return nest(path("/"), route()
				.GET("/hello", processRequest())
				.build()
		);
	}

	private HandlerFunction<ServerResponse> processRequest() {
		return request -> serveRequest();
	}

	public Mono<ServerResponse> serveRequest() {
		return Mono.delay(Duration.ofSeconds(1))
				.doOnNext(it -> {
					try {
						Thread.sleep(10);
					}
					catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				})
				.map(aLong -> ok().build())
				.block();
	}

}
