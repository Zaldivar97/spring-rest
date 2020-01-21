package sv.edu.ues.services;

import java.util.List;

import reactor.core.publisher.Flux;
import sv.edu.ues.domain.User;

public interface ApiService {
 List<User> getData(Integer limit);
 Flux<User> getDataReactive(Integer limit);
}
