# Insurance Quote API

## Descrição

API REST que processa cotações de seguros com validação de produtos e ofertas, simulando comunicação com o RabbitMQ e
serviços externos.

## Pré-requisitos

- Docker e Docker Compose
- Java 17
- Maven

## Configuração

1. **Build do Projeto**
   ```bash
   mvn clean package


2. Subir o Ambiente

```
docker-compose up --build
```

3. Acesso

- API: http://localhost:8080
- RabbitMQ: http://localhost:15672

Endpoints
Criar Cotação

```
POST /api/v1/insurance/quote
```

Exemplo de Request

```
{
  "product_id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
  "offer_id": "adc56d77-348c-4bf0-908f-22d402ee715c",
  "category": "HOME",
  "total_monthly_premium_amount": 75.25,
  "total_coverage_amount": 825000.00,
  "coverages": {
    "Incêndio": 250000.00,
    "Desastres naturais": 500000.00,
    "Responsabilidade civil": 75000.00
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


```

Exemplo de Response

```
{
  "message": "Cotação de seguro processada com sucesso."
}

```

```

---

Essa estrutura inicial inclui todos os componentes necessários para rodar a aplicação, incluindo o tratamento de exceções, integração com RabbitMQ, e exemplos de requisição e resposta.
```

Banco de Dados H2
Console H2
Após iniciar a aplicação, acesse o console H2 em:
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: password

