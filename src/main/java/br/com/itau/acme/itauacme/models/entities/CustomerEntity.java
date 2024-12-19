package br.com.itau.acme.itauacme.models.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
@Embeddable
public class CustomerEntity {

    private String documentNumber;
    private String name;
    private String type;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String phoneNumber;

    // Getters and Setters
}
