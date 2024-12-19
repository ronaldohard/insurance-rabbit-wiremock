package br.com.itau.acme.itauacme.gateway.consumer;

import br.com.itau.acme.itauacme.gateway.repository.InsuranceQuoteRepository;
import br.com.itau.acme.itauacme.models.dto.QuoteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InsurancePolicyConsumer {

    private final AmqpTemplate amqpTemplate;
    private final InsuranceQuoteRepository insuranceQuoteRepository;

    public void sendQuoteReceivedMessage(QuoteDTO quote) throws JsonProcessingException {
        //todo - trocar por keys no yml
        amqpTemplate.convertAndSend("insurance-policy-exchange", "insurance-policy-routing-key",
                new ObjectMapper().writeValueAsString(quote));

        log.info("Mensagem enviada para RabbitMQ: Cotação ID " + quote.getId());
    }

    //todo - por no yml as infos de fila etc
    @RabbitListener(queues = {"insurance.policy.created"})
    public void handlePolicyIssuedMessage(@Payload String message) {
        log.info(" ========> ");
        log.info(message);

        //    QuoteEntity quote = insuranceQuoteRepository.findById(null)
        //            .orElseThrow(() -> new RuntimeException("Cotação não encontrada"));


        //    System.out.println("Cotação atualizada com o número da apólice: " + null);
    }
}
