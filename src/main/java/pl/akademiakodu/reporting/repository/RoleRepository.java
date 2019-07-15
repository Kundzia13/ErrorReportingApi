package pl.akademiakodu.reporting.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiakodu.reporting.model.entities.Role;


@Primary
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRole(String role);
}

