package com.erggroup.test;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.erggroup.test.client.CustomerRestClient;
import com.erggroup.test.domain.Customer;
import com.erggroup.test.processor.CSVProcessor;

@SpringBootApplication()
public class Application {
	@Value("${directory.path}")
	private String directoryPath;
	@Value("${data.load.url}")
	private String loadUrl;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner readFilesAndPopulateData() {
		return args -> {
			CSVProcessor processor = new CSVProcessor();
			CustomerRestClient client = new CustomerRestClient();
			List<Customer> customers;
			if (null!=directoryPath) {
				Set<String> files = processor.listFilesInDir(directoryPath);
				for (String file : files) {
					Stream<String> fileLines = processor.getFileLinesForFile(directoryPath + "/" + file);
					customers = processor.processFileLines(fileLines);
					for (Customer customer : customers) {
						client.processCustomer(customer, loadUrl);
					}
				}
			}
		};
	}
}
