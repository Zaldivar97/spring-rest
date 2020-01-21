package sv.edu.ues.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;
import sv.edu.ues.domain.User;
import sv.edu.ues.domain.UserData;

@Service
public class ApiServiceImpl implements ApiService {

	private RestTemplate template;
	private String apiUrl;

	public ApiServiceImpl(RestTemplate template, @Value("${api.url}") String apiUrl) {
		super();
		this.template = template;
		this.apiUrl = apiUrl;
	}

	@Override
	public List<User> getData(Integer limit) {
		
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(apiUrl)
				.queryParam("limit", limit);
		
		UserData userData = template.getForObject(
				builder.toUriString(), UserData.class);
		return userData.getData();
	}

	@Override
	public Flux<User> getDataReactive(Integer limit) {
		return WebClient
				.create(apiUrl)
				.get()
				.uri(uriBuilder -> uriBuilder.queryParam("limit", limit).build())
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMap(res -> res.bodyToMono(UserData.class))
				.flatMapIterable(UserData::getData);
	}

}















