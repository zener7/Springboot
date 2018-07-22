package pl.sda.javaldz6.service;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.sda.javaldz6.model.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service
@Scope("prototype")
public class UserServiceAnnotationPrototype implements UserService {

    private Logger log = Logger.getLogger(UserServiceAnnotationPrototype.class);

    private List<User> users = null;

    @Override
    public void addUser(String name, int age) {
        users.add(new User(name, age));
    }

    @Override
    public User getUserById(int index) {
        try {
            return users.get(index);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @PostConstruct
    @Override
    public void init() {
        log.info("init UserServiceAnnotationPrototype");
        users = new ArrayList<>();
    }

    @PreDestroy
    @Override
    public void close() {
        log.info("destroy UserServiceAnnotationPrototype");
    }
}
