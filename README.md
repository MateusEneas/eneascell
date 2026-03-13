# EneasCell

![Java](https://img.shields.io/badge/Java-21-blue)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9.0-orange)
![Tests](https://img.shields.io/badge/Tests-JUnit%20%2B%20Mockito-blueviolet)

**EneasCell** é um projeto backend desenvolvido em Java com Spring Boot, criado como projeto pessoal para aprendizado e evolução profissional. O objetivo é aplicar conhecimentos de Clean Architecture, testes unitários e boas práticas de desenvolvimento, servindo como base para projetos corporativos futuros.

Atualmente, o projeto foca na gestão de estoque de produtos e categorias, com funcionalidades de CRUD, paginação e busca. Futuramente, pretende evoluir para um sistema completo de commerce com autenticação de usuários e controle de vendas.

---

## Funcionalidades

- CRUD de produtos e categorias  
- Listagem de produtos e categorias  
- Busca de produtos por ID, categoria e filtros avançados  
- Paginação de produtos  
- Edição de produtos e categorias  
- Exclusão de produtos e categorias  
- Testes unitários e de repository com JUnit e Mockito  
- Arquitetura baseada em UseCases, DTOs e Clean Architecture  

---

## Tecnologias

- **Backend:** Java 21, Spring Boot, JPA/Hibernate  
- **Banco de dados:** PostgreSQL (Docker)  
- **Testes:** JUnit, Mockito  
- **Gerenciamento de dependências:** Maven  
- **Controle de versão:** Git/GitHub  

---

## Arquitetura e Estrutura do Projeto

O projeto segue uma estrutura modular baseada em pacotes por domínio (produto e categoria) e camadas de Clean Architecture:

```

src/main/java
├─ com.eneas.eneascell
│  ├─ product
│  │  ├─ controller
│  │  ├─ domain
│  │  ├─ dto
│  │  ├─ mapper
│  │  ├─ repositories
│  │  └─ usecase
│  ├─ category
│  │  ├─ controller
│  │  ├─ domain
│  │  ├─ dto
│  │  ├─ mapper
│  │  ├─ repositories
│  │  └─ usecase
│  └─ exception
│     ├─ BusinessException
│     ├─ NotFoundException
│     └─ GlobalExceptionHandler

````

---

## Requisitos

- Java 21  
- Maven  
- PostgreSQL (ou H2 para testes)  
- IDE recomendada: IntelliJ Community  

---

## Como rodar o projeto localmente

1. Clone o repositório:  

```bash
git clone https://github.com/MateusEneas/eneascell.git
````

2. Configure o banco de dados PostgreSQL (Docker ou local).
3. Ajuste as propriedades de conexão no `application.properties` ou `application.yml`.
4. Abra o projeto na IDE.
5. Rode a aplicação:

```bash
mvn spring-boot:run
```

---

## Testes

* Contém testes **unitários** e testes de **repository**.
* Para rodar os testes com Maven:

```bash
mvn test
```

* Também é possível executar os testes diretamente pela IDE.

---

## Exemplos de JSON

### Criar produto

```json
POST /products
{
  "nome": "Capinha iPhone",
  "preco": 25.00,
  "quantidade": 10,
  "descricao": "Capinha transparente",
  "categoryIds": [
    "f94955e7-f017-4de0-8300-787733267758",
    "cf923494-7e72-4e2b-ba11-9b72a226d890"
  ]
}
```

### Resposta do produto criado

```json
{
  "nome": "Capinha iPhone",
  "preco": 25.00,
  "quantidade": 10,
  "descricao": "Capinha transparente",
  "category": [
    {
      "id": "cf923494-7e72-4e2b-ba11-9b72a226d890",
      "nome": "Promoção"
    },
    {
      "id": "f94955e7-f017-4de0-8300-787733267758",
      "nome": "Caixas de som"
    }
  ]
}
```

---

## Futuras melhorias

* Implementar autenticação e controle de usuários
* Adicionar funcionalidades de venda e gestão avançada de estoque
* Criar frontend em Angular
* Evoluir o sistema para um commerce completo

---

## Licença

Este projeto é pessoal e não possui licença definida.

---

```
