package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent searchParentByFirstNameAndLastName(String firstname, String lastname);
}
