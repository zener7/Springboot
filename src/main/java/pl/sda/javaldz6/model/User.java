package pl.sda.javaldz6.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Min;

public class User {

    @NotBlank(message = "Nazwa nie może być bez wartości")
    private String name;
    @Min(value = 0, message = "Wiek nie może być ujemny")
    private int age;

    public User(){
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
