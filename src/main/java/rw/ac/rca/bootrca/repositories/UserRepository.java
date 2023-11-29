package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

}
