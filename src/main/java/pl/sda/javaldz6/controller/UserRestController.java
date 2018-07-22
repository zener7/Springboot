package pl.sda.javaldz6.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.javaldz6.model.User;
import pl.sda.javaldz6.service.UserRestService;
import pl.sda.javaldz6.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class UserRestController {

    private static Logger log = Logger.getLogger(UserRestController.class);

    @Autowired
    @Qualifier("userHibernateService")
    private UserService userServiceAnnotationSingleton;

    @Autowired
    private UserRestService userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        User user = userServiceAnnotationSingleton.getUserById(Integer.valueOf(id));
        return  ResponseEntity.ok(user);
    }



    @GetMapping(value = "/test/rest/user")
    public ResponseEntity<User> getUser(){
        User user = userService.getRestUser();
        log.info(user);
        return  ResponseEntity.ok(user);
    }

    @PostMapping(value = "/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userServiceAnnotationSingleton.addUser(user.getName(), user.getAge());
        log.info(user);
        return  ResponseEntity.ok(user);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers(){
        return  ResponseEntity.ok(userServiceAnnotationSingleton.getAll());
    }

}
