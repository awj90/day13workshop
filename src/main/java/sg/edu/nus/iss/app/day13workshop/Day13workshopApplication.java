package sg.edu.nus.iss.app.day13workshop;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day13workshopApplication {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(Day13workshopApplication.class);

		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);

		if (cliOpts.containsOption("dataDir")) {
			String path = cliOpts.getOptionValues("dataDir").get(0);
			File directory = new File(path);
			System.out.println(directory.mkdir()); // make directory if it does not exist
		} else {
			System.out.println("Usage: java myapp.jar --dataDir <path>");
			System.exit(0);
		}
		app.run(args);
	}

}
