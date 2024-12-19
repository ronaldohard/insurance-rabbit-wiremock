package br.com.itau.acme.itauacme.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CustomerDTO {

    private String id;

    @JsonProperty("document_number")
    private String documentNumber;

    private String name;

    private String type;

    private String gender;

    @JsonProperty("date_of_birth")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //@JsonDeserialize(using = LocalDateDeserializer.class, contentAs = LocalDate.class)
    private String dateOfBirth;

    private String email;

    @JsonProperty("phone_number")
    private Long phoneNumber;
}

