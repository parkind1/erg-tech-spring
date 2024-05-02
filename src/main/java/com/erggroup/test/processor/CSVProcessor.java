package com.erggroup.test.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.erggroup.test.domain.Customer;

public class CSVProcessor {
	
	public Set<String> listFilesInDir(String dir) throws IOException {
	    try (Stream<Path> stream = Files.list(Paths.get(dir))) {
	        return stream
	          .filter(file -> !Files.isDirectory(file))
	          .map(Path::getFileName)
	          .map(Path::toString)
	          .collect(Collectors.toSet());
	    }
	}
	
	public Stream<String> getFileLinesForFile(String filePath) throws IOException {
		File file = new File(filePath);
		return Files.lines(file.toPath());
	}
	
	public List<Customer> processFileLines(Stream<String> fileLines) {
	    var customerEntries = new ArrayList<Customer>();
    	fileLines
	    	.skip(1)
	    	.forEach(fileLine -> {
	    		String[] customerLine = fileLine.split(",");
	    		customerEntries.add(new Customer(customerLine[0], customerLine[1], customerLine[2], customerLine[3], customerLine[4], customerLine[5], customerLine[6], customerLine[7]));
	    	});

	    return customerEntries;
	}
}
