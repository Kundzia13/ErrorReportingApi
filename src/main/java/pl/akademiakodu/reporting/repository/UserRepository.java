package pl.akademiakodu.reporting.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.reporting.model.entities.User;

@Primary
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Integer > {
    User findByEmail(String email);
}