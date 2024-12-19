# Insurance Quote Application

Este projeto é uma aplicação Spring Boot que gerencia cotações de seguros, incluindo validação de dados, persistência no
banco de dados e integração com RabbitMQ para troca de mensagens.

---

## **Tecnologias Utilizadas**

- **Java 17**
- **Spring Boot**
    - Spring Web
    - Spring Data JPA
    - Spring Validation
    - Spring AMQP
- **H2 Database** (Banco de dados em memória)
- **RabbitMQ**
- **WireMock** (Mock de APIs externas)
- **Docker Compose**
- **Swagger (OpenAPI)**

---

## **Pré-requisitos**

- **Docker** e **Docker Compose** instalados.
- **Java 17** instalado (caso queira rodar localmente sem Docker).

---

## **Configuração Inicial**

### 1. **Clonar o Repositório**

```bash
git clone https://github.com/seu-usuario/insurance-quote-app.git
```

```
cd insurance-quote-app
```

### 2. Configurar o Ambiente

Certifique-se de que as portas abaixo estão disponíveis:

- Aplicação: ```8080```
- RabbitMQ: ```5672``` (porta de comunicação) e ```15672``` (porta do painel de controle)
- WireMock: ```8081```

## Executando a Aplicação

### 1. Com Docker Compose

Execute o seguinte comando para iniciar a aplicação e suas dependências:

```
docker-compose up --build
```

Isso irá:

- Iniciar a aplicação Spring Boot.
- Configurar RabbitMQ com a exchange e fila necessárias.
- Configurar WireMock para simular as APIs de Produto e Oferta.

### 2. Acessar o Swagger

Após iniciar, o Swagger estará disponível em:
```
http://localhost:8080/api/v1/swagger-ui/index.html
```

### 3. APIs Mockadas

Os endpoints de produto e oferta estão mockados no WireMock:

- Produto: http://localhost:8081/products/{id}
- Oferta: http://localhost:8081/offers/{id}

## Endpoints Principais

### 1. Criar Cotação

POST ```/quotes```
Request Body:

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
    "Responsabiliadade civil": 75000.00
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

### 2. Consultar Cotação

- GET /quotes/{id}
- Response:

```
{
  "id": 22345,
  "insurance_policy_id": 756969,
  "product_id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
  "offer_id": "adc56d77-348c-4bf0-908f-22d402ee715c",
  "category": "HOME",
  "created_at": "2024-05-22T20:37:17.090098",
  "updated_at": "2024-05-22T21:05:02.090098",
  "total_monthly_premium_amount": 75.25,
  "total_coverage_amount": 825000.00,
  "coverages": {
    "Incêndio": 250000.00,
    "Desastres naturais": 500000.00,
    "Responsabiliadade civil": 75000.00
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

## Testes

### 1. Testes Unitários

- Para rodar os testes unitários:

```
./gradlew test
```

### 2. Testes de Integração

Os testes de integração garantem a comunicação entre a aplicação, RabbitMQ e WireMock.

Encerrando a Aplicação
Para encerrar a aplicação e remover os containers:

```
docker-compose down
```

