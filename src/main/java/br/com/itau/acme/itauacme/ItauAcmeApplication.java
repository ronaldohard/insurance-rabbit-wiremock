package br.com.itau.acme.itauacme;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableRabbit
@EnableFeignClients
public class ItauAcmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItauAcmeApplication.class, args);
    }

}
