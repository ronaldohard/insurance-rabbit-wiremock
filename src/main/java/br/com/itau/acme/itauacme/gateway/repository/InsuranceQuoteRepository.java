package br.com.itau.acme.itauacme.gateway.repository;

import br.com.itau.acme.itauacme.models.entities.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceQuoteRepository extends JpaRepository<QuoteEntity, Long> {

    List<QuoteEntity> findByProductId(String productId);

    List<QuoteEntity> findByOfferId(String offerId);

    List<QuoteEntity> findByCategory(String category);
}
