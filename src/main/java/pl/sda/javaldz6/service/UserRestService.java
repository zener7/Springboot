package pl.sda.javaldz6.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.javaldz6.model.User;

@Service
public class UserRestService {

    private static Logger log = Logger.getLogger(UserRestService.class);

    @Autowired
    private RestTemplate restTemplate;

    public User getRestUser() {
        return restTemplate.getForObject("http://localhost:8888/user/1", User.class);
    }

    public User addUser() {
        return restTemplate.postForObject("http://localhost:8888/user", new User("Waldek", 12), User.class);
    }

    public ResponseEntity<User> modifyUser() {
        //         restTemplate.put("http://localhost:8888/user/1", new User("Janusz", 22)); alternatywa dla poniższego wywołania
        HttpEntity<User> request = new HttpEntity<>(new User("Janusz", 28));
        return restTemplate.exchange("http://localhost:8888/user/1", HttpMethod.PUT, request, User.class);
    }
}
