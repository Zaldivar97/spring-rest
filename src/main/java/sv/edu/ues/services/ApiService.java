package sv.edu.ues.services;

import java.util.List;

import sv.edu.ues.domain.User;

public interface ApiService {
 List<User> getData(Integer limit);
}
