package com.erggroup.test.client;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import com.erggroup.test.domain.Customer;

public class CustomerRestClient {
	public boolean processCustomer(Customer customer, String loadUrl) {
		RestClient restClient = RestClient.builder().baseUrl(loadUrl).build();

		ResponseEntity<Void> response = restClient.post().accept(MediaType.APPLICATION_JSON).body(customer).retrieve()
				.toBodilessEntity();

		return response.getStatusCode().is2xxSuccessful();
	}

	public Customer getCustomer(String customerRef, String fetchUrl) {
		RestClient restClient = RestClient.builder().baseUrl(fetchUrl).build();

		return restClient.get().uri("/{id}", customerRef).accept(MediaType.APPLICATION_JSON).retrieve().body(Customer.class);
	}
}
