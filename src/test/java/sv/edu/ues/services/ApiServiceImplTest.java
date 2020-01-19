package sv.edu.ues.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sv.edu.ues.domain.User;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApiServiceImplTest {
	
	@Autowired
	ApiService apiService;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void test() {
		List<User> list = apiService.getData(1);
		assertEquals(1, list.size());
	}

}







