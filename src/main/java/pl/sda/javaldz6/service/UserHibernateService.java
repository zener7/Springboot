package pl.sda.javaldz6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javaldz6.model.User;
import pl.sda.javaldz6.model.UserEntity;
import pl.sda.javaldz6.repository.UserHibernateRepository;
import pl.sda.javaldz6.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Profile("hibernate")
public class UserHibernateService implements UserService {

    @Autowired
    private UserHibernateRepository userRepository;

    @Override
    public void addUser(String name, int age) {
        userRepository.save(new UserEntity(name, age));
    }

    @Override
    public User getUserById(int index) {
        UserEntity userEntity =  userRepository.getById(new Long(index));
        return new User(userEntity.getName(), userEntity.getAge());
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll().stream()
                .map(userEntity -> new User(userEntity.getName(), userEntity.getAge()))
                .collect(Collectors.toList());
    }

    @Override
    public void init() {

    }

    @Override
    public void close() {

    }
}
