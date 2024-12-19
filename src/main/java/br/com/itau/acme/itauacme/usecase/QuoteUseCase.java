package br.com.itau.acme.itauacme.usecase;

import br.com.itau.acme.itauacme.gateway.consumer.InsurancePolicyConsumer;
import br.com.itau.acme.itauacme.gateway.out.category.CatalogRestClient;
import br.com.itau.acme.itauacme.gateway.repository.InsuranceQuoteRepository;
import br.com.itau.acme.itauacme.models.dto.OfferDTO;
import br.com.itau.acme.itauacme.models.dto.ProductDTO;
import br.com.itau.acme.itauacme.models.dto.QuoteDTO;
import br.com.itau.acme.itauacme.models.mapper.QuoteMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuoteUseCase {

    private final CatalogRestClient catalogRestClient;
    private final InsuranceQuoteRepository insuranceQuoteRepository;
    private final InsurancePolicyConsumer insurancePolicyConsumer;
    private final QuoteMapper quoteMapper;

    public QuoteDTO execute(QuoteDTO quoteDTO) throws JsonProcessingException {

        //todo -
        var product = catalogRestClient.getProductById(quoteDTO.getProductId());
        var offer = catalogRestClient.getOfferById(quoteDTO.getOfferId());

        //todo - 2. Validar a oferta e produto informado consultando a API catalogo
        validate(quoteDTO, product.get(), offer.get());

        //todo -  O produto e oferta são existentes e estão ativos

        //todo -  As coberturas informadas estão dentro da lista de coberturas da oferta e abaixo dos valores máximos permitidos.

        //todo -  As assistências informadas estão dentro da lista de assistências da oferta.

        //todo -  O valor total do prêmio mensal está entre o máximo e mínimo definido para a oferta.

        //todo -  O valor total das coberturas corresponde a somatória das coberturas informadas correspondentes

        //todo - Caso a solicitação seja válida é necessário persistir a cotação em um banco de
        //dados de sua preferência gerando um identificador único em formato numérico e
        var quote = insuranceQuoteRepository.save(quoteMapper.toMap(quoteDTO));

        //enviar uma mensagem de cotação recebida no broker de sua preferência.
        insurancePolicyConsumer.sendQuoteReceivedMessage(quoteDTO);

        //        Se a solicitação for inválida é necessário retornar um erro na chamada da API
        //para que cliente corrija os dados e tente novamente.
        //todo        Após enviar a mensagem da cotação recebida o serviço de apólices irá emitir a
        //apólice desta cotação e enviar uma mensagem com as informações da apólice emitida.

        //todo        O serviço de cotação deverá então receber esta mensagem e atualizar a
        //cotação com o número da apólice gerada, portanto é necessário também que exista um
        //endpoint para consulta de cotações que retornem dados


        return null;
    }

    private void validate(QuoteDTO quoteDTO, ProductDTO productDTO, OfferDTO offerDTO) {
        log.info("Starting validate {} {}... ", productDTO.getName(), offerDTO.getName());
        this.validateProductAndOffer(productDTO, offerDTO);
        this.validateCoverages(quoteDTO, offerDTO);
        this.validateAssistances(offerDTO);
        this.validatePremiumAmount(quoteDTO, offerDTO);
        this.validateCoverageSum(quoteDTO, offerDTO);
    }

    private void validateProductAndOffer(ProductDTO productDTO, OfferDTO offerDTO) {
        log.info("validating product and offer...");

        if (productDTO == null || !productDTO.isActive()) {
            throw new IllegalStateException("Produto não encontrado ou inativo.");
        }

        if (offerDTO == null || !offerDTO.isActive() || !offerDTO.getProductId().equals(productDTO.getId())) {
            throw new IllegalStateException("Oferta não encontrada, inativa ou não pertence ao produto informado.");
        }
    }

    private void validateCoverages(QuoteDTO quoteDTO, OfferDTO offer) {
        log.info("validating Coverages...");

        for (Map.Entry<String, BigDecimal> coverage : quoteDTO.getCoverages().entrySet()) {

            if (!offer.getCoverages().containsKey(coverage.getKey())) {
                throw new IllegalStateException("Cobertura '" + coverage.getKey() + "' não permitida.");
            }

            //todo - bigdecimal
            if (coverage.getValue().doubleValue() > offer.getCoverages().get(coverage.getKey()).doubleValue()) {
                throw new IllegalStateException("Cobertura '" + coverage.getKey() + "' excede o valor máximo permitido.");
            }
        }
    }

    private void validateAssistances(OfferDTO offerDTO) {
        log.info("validating assistances...");
        for (String assistance : offerDTO.getAssistances()) {
            if (!offerDTO.getAssistances().contains(assistance)) {
                throw new IllegalStateException("Assistência '" + assistance + "' não permitida.");
            }
        }
    }

    private void validatePremiumAmount(QuoteDTO quoteDTO, OfferDTO offerDTO) {
        log.info("validating premium amount...");

        double premium = quoteDTO.getTotalMonthlyPremiumAmount();
        var isPremiumBetweenMinMax = premium > offerDTO.getMaxPremium().doubleValue() || premium < offerDTO.getMinPremium().doubleValue();

        //todo - alterar para compareTo BigDecimal
        if (isPremiumBetweenMinMax) {
            throw new IllegalStateException("O valor do prêmio mensal está fora do intervalo permitido.");
        }
    }

    private void validateCoverageSum(QuoteDTO quoteDTO, OfferDTO offerDTO) {
        log.info("validating coverage sum...");

        double sum = offerDTO.getCoverages().values().stream().mapToDouble(BigDecimal::doubleValue).sum();
        if (sum != quoteDTO.getTotalCoverageAmount()) {
            throw new IllegalStateException("A soma das coberturas não corresponde ao valor total informado.");
        }
    }
}
