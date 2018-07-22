package pl.sda.javaldz6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sda.javaldz6.model.User;
import pl.sda.javaldz6.model.UserEntity;
import pl.sda.javaldz6.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Profile("jpa")
public class UserJpaService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(String name, int age) {
        userRepository.save(new UserEntity(name, age));
    }

    @Override
    public User getUserById(int index) {
        return userRepository.findById(new Long(index))
                .map(u -> new User(u.getName(), u.getAge()))
                .orElse(new User());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll().stream()
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
