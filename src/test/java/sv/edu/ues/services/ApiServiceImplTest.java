package sv.edu.ues.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Flux;
import sv.edu.ues.domain.User;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApiServiceImplTest {
	
	final static String URL_BASE_SWAGGER = "https://api.predic8.de:443/shop";
	
	@Autowired
	ApiService apiService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	final void getCustomerExample() {
		RestTemplate tmp = new RestTemplate();
		JsonNode node = tmp.getForObject(
				URL_BASE_SWAGGER.concat("/customers/")
				, JsonNode.class);
		System.out.println("Get Salida");
		System.out.println(node.toPrettyString());
	}
	
	@Test
	final void createCustomerExample() {
		String endpoint = URL_BASE_SWAGGER.concat("/customers/");
		RestTemplate tmp = new RestTemplate();
		Map<String,String> postObject = Map.of(
				"firstname", "Steven",
				"lastname", "Zaldivar");
		
		JsonNode node = tmp.postForObject(endpoint, postObject, JsonNode.class);
		System.out.println("Post Salida");
		System.out.println(node.toPrettyString());
	}
	@Test
	final void updateCustomerExample() {
		String endpoint = URL_BASE_SWAGGER.concat("/customers/");
		RestTemplate tmp = new RestTemplate();
		Map<String,String> postObject = Map.of(
				"firstname", "Christian",
				"lastname", "Herrera");
		
		JsonNode node = tmp.postForObject(endpoint, postObject, JsonNode.class);	
		System.out.println("Post Salida");
		System.out.println(node.toPrettyString());
		
		String customerUrl = node.get("customer_url").textValue();
		//Customer url: /shop/customers/478
		String customerCreatedId = customerUrl.split("/")[3];
		String customerCreatedEndpoint = endpoint.concat(customerCreatedId);
		
		postObject = Map.of(
					"firstname", "Christian 3030",
					"lastname", "Herrera 3030");
		
		tmp.put(customerCreatedEndpoint, postObject);
	
		node = tmp.getForObject(customerCreatedEndpoint, JsonNode.class);
		
		System.out.println("put Salida");
		System.out.println(node.toPrettyString());
	}
	@Test
	final void deleteCustomerExample() {
		String endpoint = URL_BASE_SWAGGER.concat("/customers/");
		RestTemplate tmp = new RestTemplate();
		Map<String,String> postObject = Map.of(
				"firstname", "Christian",
				"lastname", "Herrera");
		
		JsonNode node = tmp.postForObject(endpoint, postObject, JsonNode.class);	
		
		System.out.println("Post Salida");
		System.out.println(node.toPrettyString());
		
		String customerUrl = node.get("customer_url").textValue();
		//Customer url: /shop/customers/478
		String customerCreatedId = customerUrl.split("/")[3];
		
		String customerCreatedEndpoint = endpoint.concat(customerCreatedId);
		tmp.delete(customerCreatedEndpoint);
		
		assertThrows(NotFound.class,()->{
			tmp.getForObject(customerCreatedEndpoint, JsonNode.class);
		});
	}
	
	@Disabled
	@Test
	final void test() {
		List<User> list = apiService.getData(1);
		assertEquals(1, list.size());
	}
	
	@Test
	final void testReactive() {
		Flux<User> data = apiService.getDataReactive(1);
		data.collectList().subscribe(
				list -> assertEquals(1, list.size()));
	}

}







