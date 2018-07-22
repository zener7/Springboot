package pl.sda.javaldz6.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javaldz6.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long > {
}
