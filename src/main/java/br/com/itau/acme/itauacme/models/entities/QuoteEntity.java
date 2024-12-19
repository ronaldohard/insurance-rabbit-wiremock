package br.com.itau.acme.itauacme.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private String offerId;
    private String category;
    private double totalMonthlyPremiumAmount;
    private double totalCoverageAmount;

    @ElementCollection
    // @CollectionTable(name = "coverage", joinColumns = @JoinColumn(name = "quote_id"))
    @MapKeyColumn(name = "name")
    @Column(name = "coverages")
    private Map<String, BigDecimal> coverages;

    @ElementCollection
    @CollectionTable(name = "assistance", joinColumns = @JoinColumn(name = "quote_id"))
    @Column(name = "assistance_name")
    private List<String> assistances;

    @Embedded
    private CustomerEntity customerEntity;

    // Getters and Setters
}
