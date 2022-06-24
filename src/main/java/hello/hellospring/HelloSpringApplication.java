package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
// 톰캣 웹서버 내장
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
