package br.com.itau.acme.itauacme.gateway.in.controller;

import br.com.itau.acme.itauacme.models.dto.OfferDTO;
import br.com.itau.acme.itauacme.models.dto.QuoteDTO;
import br.com.itau.acme.itauacme.usecase.QuoteUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance")
@Slf4j
public class InsuranceController {

    public final QuoteUseCase quoteUseCase;

    public InsuranceController(QuoteUseCase quoteUseCase) {
        this.quoteUseCase = quoteUseCase;
    }

    @GetMapping("/quotes")
    @Operation(summary = "List all quotes")
    public ResponseEntity<QuoteDTO> getById(String id) {
        return ResponseEntity.ok(QuoteDTO.builder().build());
    }

    @PostMapping("/quote")
    @Operation(summary = "Record a quote")
    public ResponseEntity<QuoteDTO> quote(
            @RequestBody
            @Schema(example = """
                    {
                      "product_id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
                      "offer_id": "adc56d77-348c-4bf0-908f-22d402ee715c",
                      "category": "HOME",
                      "total_monthly_premium_amount": 75.25,
                      "total_coverage_amount": 825000,
                      "coverages": {
                        "Incêndio": 250000,
                        "Desastres naturais": 500000,
                        "Responsabiliadade civil": 75000
                      },
                      "assistances": [
                        "Encanador",
                        "Eletricista",
                        "Chaveiro 24h"
                      ],
                      "customer": {
                        "document_number": "36205578900",
                        "name": "John Wick",
                        "type": "NATURAL",
                        "gender": "MALE",
                        "date_of_birth": "1973-05-02",
                        "email": "johnwick@gmail.com",
                        "phone_number": 11950503030
                      }
                    }
                    """
            ) QuoteDTO quote) throws JsonProcessingException {


        var result = quoteUseCase.execute(quote);

        log.info(new ObjectMapper().writeValueAsString(result));

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Test")
    @PostMapping("/offer")
    public ResponseEntity<OfferDTO> offer(
            @RequestBody
            @Schema(example = "{\n" +
                    "\"id\": \"adc56d77-348c-4bf0-908f-22d402ee715c\",\n" +
                    "\"product_id\": \"1b2da7cc-b367-4196-8a78-9cfeec21f587\",\n" +
                    "\"name\": \"Seguro de Vida Familiar\",\n" +
                    "\"created_at\": \"2021-07-01T00:00:00Z\",\n" +
                    "\"active\": true,\n" +
                    "\"coverages\": {\n" +
                    "\"Incêndio\": 500000.00,\n" +
                    "\"Desastres naturais\": 600000.00,\n" +
                    "\"Responsabiliadade civil\": 80000.00,\n" +
                    "\"Roubo\": 100000.00\n" +
                    "},\n" +
                    "\"assistances\": [\n" +
                    "\"Encanador\",\n" +
                    "\"Eletricista\",\n" +
                    "\"Chaveiro 24h\",\n" +
                    "\"Assistência Funerária\"\n" +
                    "],\n" +
                    "\"monthly_premium_amount\": {\n" +
                    "\"max_amount\": 100.74,\n" +
                    "\"min_amount\": 50.00,\n" +
                    "\"suggested_amount\": 60.25\n" +
                    "}\n" +
                    "}") OfferDTO offer) throws JsonProcessingException {
        log.info(new ObjectMapper().writeValueAsString(offer));
        return ResponseEntity.ok(offer);
    }

    /*
    @PostMapping("/quote")
    public ResponseEntity<QuoteDTO> createQuote(@RequestBody QuoteDTO requestDTO) {
        QuoteDTO savedQuote = insuranceService.saveInsuranceQuote(requestDTO);
        return ResponseEntity.ok(savedQuote);
    }

    @GetMapping("/quotes/product/{productId}")
    public ResponseEntity<List<InsuranceQuote>> getQuotesByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(insuranceService.getQuotesByProductId(productId));
    }

    @GetMapping("/quotes/offer/{offerId}")
    public ResponseEntity<List<InsuranceQuote>> getQuotesByOfferId(@PathVariable String offerId) {
        return ResponseEntity.ok(insuranceService.getQuotesByOfferId(offerId));
    }

    @GetMapping("/quotes/category/{category}")
    public ResponseEntity<List<InsuranceQuote>> getQuotesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(insuranceService.getQuotesByCategory(category));
    }
     */
}
