# 🚀 EneasCell

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9.0-orange)
![Tests](https://img.shields.io/badge/Tests-JUnit%20%7C%20Mockito%20%7C%20MockMvc-blueviolet)
![Security](https://img.shields.io/badge/Security-JWT%20%7C%20SpringSecurity-red)

---

## 📌 Sobre o Projeto

O **EneasCell** é uma API REST desenvolvida em **Java com Spring Boot**, criada como projeto prático para simular um ambiente corporativo real de backend.

O projeto aplica conceitos modernos como:
- Clean Architecture
- Boas práticas de desenvolvimento
- Testes automatizados
- Segurança com autenticação JWT

O sistema atualmente realiza a gestão de **produtos e categorias**, e já conta com **autenticação e autorização de usuários**, evoluindo continuamente para um cenário completo de e-commerce.

---

## ⚙️ Funcionalidades

### 📦 Produtos e Categorias
- CRUD completo de produtos e categorias  
- Paginação e ordenação  
- Filtros dinâmicos  
- Busca por ID e categoria  
- Tratamento de exceções global  

### 🔐 Autenticação e Segurança
- Registro de usuários  
- Login com geração de token JWT  
- Validação de requisições via filtro de segurança  
- Proteção de endpoints  
- Controle de acesso baseado em roles (USER / ADMIN)  

### 🧪 Testes Automatizados
- Testes unitários (JUnit 5 + Mockito)  
- Testes de integração  
- Testes web de controllers com MockMvc  
- Validação de regras de negócio e comportamento da API  

---

## 🛠️ Tecnologias Utilizadas

### Backend
- Java 21  
- Spring Boot  
- Spring Data JPA  
- Hibernate  

### Segurança
- Spring Security  
- JWT (JSON Web Token)  

### Banco de Dados
- PostgreSQL  

### Testes
- JUnit 5  
- Mockito  
- MockMvc  

### Ferramentas
- Maven  
- Git / GitHub  

---

## 🧠 Arquitetura do Projeto

O projeto segue os princípios de **Clean Architecture**, com separação clara de responsabilidades:

```

src/main/java
├─ com.eneas.eneascell
│  ├─ product
│  ├─ category
│  ├─ auth
│  │  ├─ controller
│  │  ├─ domain
│  │  ├─ dto
│  │  ├─ repository
│  │  └─ usecase
│  └─ exception

````

### 🔹 Camadas principais:
- **Controller:** entrada da API (HTTP)
- **UseCase:** regras de negócio
- **Domain:** entidades do sistema
- **Repository:** acesso a dados
- **DTO:** comunicação entre camadas

---

## 🔐 Como funciona a autenticação (JWT)

O sistema utiliza **JWT (JSON Web Token)** para autenticação.

### 🔑 Fluxo:

1. Usuário realiza login:
```http
POST /auth/login
````

2. Recebe um token JWT:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

3. Envia o token nas próximas requisições:

```http
Authorization: Bearer SEU_TOKEN
```

4. O sistema valida o token através de um filtro (`JwtFilter`) e libera o acesso conforme a role.

---

## ▶️ Como rodar o projeto

### Pré-requisitos:

* Java 21
* Maven
* PostgreSQL

---

### 1. Clone o repositório

```bash
git clone https://github.com/MateusEneas/eneascell.git
```

### 2. Configure o banco de dados

Altere o `application.properties` ou `application.yml` com suas credenciais.

---

### 3. Execute o projeto

```bash
mvn spring-boot:run
```

---

## 🧪 Testes

O projeto possui cobertura de testes em diferentes níveis:

* Testes unitários
* Testes de integração
* Testes web de controllers

### Executar testes:

```bash
mvn test
```

---

## 📬 Exemplos de Requisição

### Criar Produto

```json
POST /products
{
  "nome": "Capinha iPhone",
  "preco": 25.00,
  "quantidade": 10,
  "descricao": "Capinha transparente",
  "categoryIds": [
    "id-categoria-1",
    "id-categoria-2"
  ]
}
```

---

### Login

```json
POST /auth/login
{
  "email": "user@email.com",
  "password": "123456"
}
```

---

## 🚧 Próximos Passos

* Implementar autorização mais granular por roles
* Melhorar cobertura de testes de segurança
* Adicionar módulo de vendas
* Integração com frontend
* Deploy em ambiente cloud

---

## 📌 Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

* Consolidar conhecimentos em backend Java
* Simular cenários reais de desenvolvimento
* Evoluir continuamente como desenvolvedor

---

## 👨‍💻 Autor

**Mateus Enéas**
🔗 [https://github.com/MateusEneas](https://github.com/MateusEneas)
🔗 [https://linkedin.com/in/mateus-eneas](https://linkedin.com/in/mateus-eneas)

---

## 📄 Licença

Projeto de uso pessoal para fins de estudo e portfólio.

```
