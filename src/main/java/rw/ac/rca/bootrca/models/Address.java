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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String village;
    String sector;
    String cell;
    String district;
    String province;

    public Address(String village, String sector, String cell, String district, String province) {
        this.village = village;
        this.sector = sector;
        this.cell = cell;
        this.district = district;
        this.province = province;
    }
}
