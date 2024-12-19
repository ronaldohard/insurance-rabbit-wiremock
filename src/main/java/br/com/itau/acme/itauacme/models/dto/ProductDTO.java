package br.com.itau.acme.itauacme.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class ProductDTO {

    private String id;
    private String name;
    private String createdAt;
    private boolean active;

    private List<OfferDTO> offers;

}
