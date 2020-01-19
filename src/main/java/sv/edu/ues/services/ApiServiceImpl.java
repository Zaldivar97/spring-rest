package sv.edu.ues.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sv.edu.ues.domain.User;
import sv.edu.ues.domain.UserData;

@Service
public class ApiServiceImpl implements ApiService {

	private RestTemplate template;

	public ApiServiceImpl(RestTemplate template) {
		super();
		this.template = template;
	}

	@Override
	public List<User> getData(Integer limit) {
		UserData userData = template.getForObject(
				"http://private-anon-063072cc66-apifaketory.apiary-mock.com/api/user?limit="+limit, UserData.class);
		return userData.getData();
	}

}
