package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Parent;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {
}
