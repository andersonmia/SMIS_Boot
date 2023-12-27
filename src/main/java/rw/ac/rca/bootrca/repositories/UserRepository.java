package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
