package rw.ac.rca.bootrca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.ac.rca.bootrca.models.Address;


public interface AddressRepository extends JpaRepository<Address, Long> {
    Address searchAddressByVillage(String village);

}
