package br.com.itau.acme.itauacme.service;

import br.com.itau.acme.itauacme.gateway.repository.InsuranceQuoteRepository;
import br.com.itau.acme.itauacme.models.dto.QuoteDTO;
import br.com.itau.acme.itauacme.models.entities.QuoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceQuoteRepository insuranceQuoteRepository;

    public QuoteDTO saveInsuranceQuote(QuoteDTO requestDTO) {
        QuoteEntity quote = new QuoteEntity();
     /*   quote.setProductId(requestDTO.getProduct_id());
        quote.setOfferId(requestDTO.getOffer_id());
        quote.setCategory(requestDTO.getCategory());
        quote.setTotalMonthlyPremiumAmount(requestDTO.getTotal_monthly_premium_amount());
        quote.setTotalCoverageAmount(requestDTO.getTotal_coverage_amount());
        quote.setCoverages(requestDTO.getCoverages());
        quote.setAssistances(requestDTO.getAssistances());

        InsuranceQuote.Customer customer = new InsuranceQuote.Customer();
        customer.setDocumentNumber(requestDTO.getCustomer().getDocument_number());
        customer.setName(requestDTO.getCustomer().getName());
        customer.setType(requestDTO.getCustomer().getType());
        customer.setGender(requestDTO.getCustomer().getGender());
        customer.setDateOfBirth(requestDTO.getCustomer().getDate_of_birth());
        customer.setEmail(requestDTO.getCustomer().getEmail());
        customer.setPhoneNumber(requestDTO.getCustomer().getPhone_number());

        quote.setCustomer(customer);
        */

        var saved = insuranceQuoteRepository.save(quote);
        System.out.printf("saved.... " + saved);
        return null;
    }

    public List<QuoteEntity> getQuotesByProductId(String productId) {
        return insuranceQuoteRepository.findByProductId(productId);
    }

    public List<QuoteEntity> getQuotesByOfferId(String offerId) {
        return insuranceQuoteRepository.findByOfferId(offerId);
    }

    public List<QuoteEntity> getQuotesByCategory(String category) {
        return insuranceQuoteRepository.findByCategory(category);
    }
}
