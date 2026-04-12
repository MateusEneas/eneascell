# 🚀 EneasCell — Backend

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.5-brightgreen)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9-orange)
![Tests](https://img.shields.io/badge/Tests-JUnit%20%7C%20Mockito%20%7C%20MockMvc-blueviolet)
![Security](https://img.shields.io/badge/Security-JWT%20%7C%20SpringSecurity-red)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED)
![CI](https://github.com/MateusEneas/eneascell/actions/workflows/ci.yml/badge.svg)

---

## 📌 Sobre o Projeto

O **EneasCell** é um sistema completo de gestão de loja de celulares, desenvolvido com **Java e Spring Boot** no backend e **Angular 21** no frontend.

O backend é uma API REST que aplica conceitos modernos como Clean Architecture, SOLID, testes automatizados, segurança com JWT, containerização com Docker e integração contínua com GitHub Actions.

🔗 **Frontend (Painel Admin):** [github.com/MateusEneas/eneascell-admin](https://github.com/MateusEneas/eneascell-admin)

---

## ⚙️ Funcionalidades

### 📦 Produtos e Categorias
- CRUD completo com regras de negócio isoladas em Use Cases
- Paginação, ordenação e filtros dinâmicos
- Busca por ID e por categoria
- Relacionamento ManyToMany entre produto e categoria
- Tratamento global de exceções com respostas padronizadas

### 🔐 Autenticação e Segurança
- Login com geração de token JWT
- Filtro de autenticação (JwtFilter) interceptando todas as requisições
- Controle de acesso baseado em roles (ADMIN/USER)
- Senhas criptografadas com BCrypt
- Proteção de endpoints por perfil
- Rotas públicas para catálogo de produtos

### 👤 Gestão de Usuários (somente ADMIN)
- Criação de usuários com definição de role
- Listagem, busca por ID, edição e exclusão
- Endpoint de perfil para usuário logado
- Respostas sem exposição de dados sensíveis (UserResponseDTO)

### 🧪 Testes Automatizados
- Testes unitários de Use Cases com JUnit 5 e Mockito
- Testes de controller com MockMvc e @WebMvcTest
- Testes de segurança validando rotas protegidas (401/403)
- Perfil de teste isolado com banco H2 em memória

### 🐳 DevOps
- Containerização completa com Dockerfile e Docker Compose
- DataSeeder criando admin automaticamente na primeira execução
- Pipeline de CI com GitHub Actions — testes rodam automaticamente a cada push

---

## 🛠️ Tecnologias

| Categoria | Tecnologias |
|---|---|
| Backend | Java 21, Spring Boot 3.5 |
| Segurança | Spring Security, JWT, BCrypt |
| Persistência | JPA/Hibernate, PostgreSQL |
| Testes | JUnit 5, Mockito, MockMvc |
| DevOps | Docker, Docker Compose, GitHub Actions |
| Documentação | Swagger / OpenAPI |
| Ferramentas | Maven, Git, GitHub |

---

## 🧠 Arquitetura do Projeto

O projeto segue **Clean Architecture** com separação por domínio:

```
src/main/java/com/eneas/eneascell
├── product/
│   ├── controller/    → entrada HTTP
│   ├── usecase/       → regras de negócio
│   ├── domain/        → entidade pura
│   ├── repository/    → acesso ao banco
│   ├── dto/           → transferência de dados
│   └── mapper/        → conversão entre camadas
├── category/
│   └── (mesma estrutura)
├── auth/
│   ├── controller/    → login e gestão de usuários
│   ├── domain/        → User e UserRole
│   ├── dto/           → LoginDTO, CreateUserDTO, UserResponseDTO
│   ├── repository/    → UserRepository
│   ├── usecase/       → casos de uso de usuário
│   ├── JwtService     → geração e validação de tokens
│   ├── JwtFilter      → interceptador de requisições
│   └── SecurityConfig → configuração de segurança
├── config/
│   ├── SwaggerConfig  → documentação da API
│   └── CorsConfig     → configuração de CORS
└── exceptions/
    ├── BusinessException
    ├── NotFoundException
    └── GlobalExceptionHandler
```

---

## 🔐 Como funciona a autenticação (JWT)

```
POST /auth/login
        ↓
AuthenticationManager verifica email e senha
        ↓
JwtService gera o token com email do usuário
        ↓
Cliente recebe o token

--- próximas requisições ---

Requisição com Authorization: Bearer {token}
        ↓
JwtFilter intercepta e valida o token
        ↓
Extrai o email e busca o usuário no banco
        ↓
SecurityConfig verifica o role e libera ou bloqueia
```

---

## ▶️ Como rodar o projeto

### ✅ Opção 1 — Docker Compose (recomendado)

Pré-requisitos: **Docker** instalado.

```bash
git clone https://github.com/MateusEneas/eneascell.git
cd eneascell
docker compose up --build
```

A aplicação sobe em `http://localhost:8080`.

O primeiro admin é criado automaticamente:
```
email: admin@eneas.com
senha: admin123
```

### Opção 2 — Rodando localmente

Pré-requisitos: **Java 21**, **Maven**, **PostgreSQL**.

```bash
git clone https://github.com/MateusEneas/eneascell.git
cd eneascell
mvn spring-boot:run
```

---

## 📖 Documentação da API

Com a aplicação rodando, acesse o Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

### Como autenticar no Swagger
1. Faça `POST /auth/login` com as credenciais
2. Copie o token da resposta
3. Clique em **Authorize 🔒**
4. Cole o token e clique em Authorize

---

## 🧪 Testes

```bash
mvn test
```

O projeto usa perfil `test` com banco H2 em memória. O pipeline de CI roda os testes automaticamente a cada push.

---

## 📬 Exemplos de Requisição

### Login
```json
POST /auth/login
{
    "email": "admin@eneas.com",
    "senha": "admin123"
}
```

### Criar Produto
```json
POST /produto/
Authorization: Bearer {token}
{
    "nome": "Capinha iPhone 14",
    "preco": 25.90,
    "quantidade": 10,
    "descricao": "Capinha transparente",
    "categoryIds": ["uuid-da-categoria"]
}
```

### Criar Usuário (somente ADMIN)
```json
POST /user/
Authorization: Bearer {token}
{
    "nome": "Vendedor",
    "email": "vendedor@eneas.com",
    "senha": "123456",
    "role": "USER"
}
```

---

## 🚧 Próximos Passos

- [ ] Audit Log — relatório de ações por usuário
- [ ] Módulo de vendas
- [ ] Deploy em ambiente cloud

---

## 👨‍💻 Autor

**Mateus Enéas**
🔗 [GitHub](https://github.com/MateusEneas)
🔗 [LinkedIn](https://linkedin.com/in/mateus-eneas)

---

## 📄 Licença

Projeto de uso pessoal para fins de estudo e portfólio.