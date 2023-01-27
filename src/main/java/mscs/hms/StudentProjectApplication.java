package mscs.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"mscs.hms"})
public class StudentProjectApplication /* extends SpringBootServletInitializer */{

	public static void main(String[] args) {
		SpringApplication.run(StudentProjectApplication.class, args);
	}

}
