package rw.ac.rca.bootrca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    int countryCode;
    long number;

    public PhoneNumber(int countryCode, long number) {
        this.countryCode = countryCode;
        this.number = number;
    }
}
