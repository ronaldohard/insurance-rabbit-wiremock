package br.com.itau.acme.itauacme.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class OfferDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("coverages")
    private Map<String, BigDecimal> coverages;

    @JsonProperty("assistances")
    private List<String> assistances;

    @JsonProperty("monthly_premium_amount")
    private MonthlyPremiumAmountDTO monthlyPremiumAmount;

    public BigDecimal getMinPremium() {
        //todo - impl
        return monthlyPremiumAmount.minAmount;
    }

    public BigDecimal getMaxPremium() {
        //todo - impl
        return monthlyPremiumAmount.maxAmount;
    }

    public static class MonthlyPremiumAmountDTO {

        @JsonProperty("max_amount")
        private BigDecimal maxAmount;

        @JsonProperty("min_amount")
        private BigDecimal minAmount;

        @JsonProperty("suggested_amount")
        private BigDecimal suggestedAmount;
    }
}
