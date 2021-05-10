package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.blockhound.BlockHound;
import reactor.test.StepVerifier;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private DemoApplication demoApplication;

	static {
		BlockHound.install();
	}

	@Test
	void contextLoads() {
		StepVerifier.create(demoApplication.serveRequest())
				.expectComplete();
	}

}
