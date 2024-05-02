package com.erggroup.test.processor;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erggroup.test.domain.Customer;

@ExtendWith(MockitoExtension.class)
public class CSVProcessorTest {
	CSVProcessor processor = new CSVProcessor();

	@Test
	void testThatProcessorReturns1Row() {
		String[] fileLines = {
				"Customer Ref, Customer Name, Address Line 1, Address Line 2, Town, County, Country, Postcode" + "\n",
				"1, Terry, FiedofSpiders, 1 Some Street, Some District, Sometown, Somecounty, Somecountry, AA1 2BC" };
		List<Customer> customerList = processor.processFileLines(Stream.of(fileLines));
		assertEquals(1, customerList.size());
	}

	@Test
	void testThatProcessorReturns0Rows() {
		String[] fileLines = {
				"Customer Ref, Customer Name, Address Line 1, Address Line 2, Town, County, Country, Postcode" };
		List<Customer> customerList = processor.processFileLines(Stream.of(fileLines));
		assertEquals(0, customerList.size());
	}
	
	@Test
	void testThatProcessorReturnsMultipleRows() {
		String[] fileLines = {
				"Customer Ref, Customer Name, Address Line 1, Address Line 2, Town, County, Country, Postcode" + "\n",
				"1, Terry, FiedofSpiders, 1 Some Street, Some District, Sometown, Somecounty, Somecountry, AA1 2BC" + "\n",
				"2, Petra, FiedofSpiders, 2 Some Street, Some District, Sometown, Somecounty, Somecountry, AA1 2BC"};
		List<Customer> customerList = processor.processFileLines(Stream.of(fileLines));
		assertThatList(customerList).hasSizeGreaterThan(1);
	}

}
