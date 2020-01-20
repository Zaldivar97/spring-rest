package sv.edu.ues.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

}















