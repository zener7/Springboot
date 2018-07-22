package pl.sda.javaldz6.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.sda.javaldz6.model.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceAnnotationSingleton implements UserService {

    private  Logger log = Logger.getLogger(UserServiceAnnotationSingleton.class);

    private List<User> users  = null;

    @Override
    public void addUser(String name, int age) {
        users.add(new User(name , age));
    }

    @Override
    public User getUserById(int index) {
        try{
            return users.get(index);
        } catch (Exception e){
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
        log.info("init UserServiceAnnotationSingleton");
        users = new ArrayList<>();
    }

    @PreDestroy
    @Override
    public void close() {
        log.info("destroy UserServiceAnnotationSingleton");
    }
}
