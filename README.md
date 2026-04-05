# 🚀 EneasCell

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

O **EneasCell** é uma API REST desenvolvida em **Java com Spring Boot**, criada como projeto prático para simular um ambiente corporativo real de backend.

O projeto aplica conceitos modernos como Clean Architecture, SOLID, testes automatizados, segurança com JWT, containerização com Docker e integração contínua com GitHub Actions.

O sistema realiza a gestão de **produtos, categorias e usuários**, com controle de acesso baseado em perfis (**ADMIN / USER**), evoluindo continuamente para um cenário completo de e-commerce.

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
- Filtro de autenticação (`JwtFilter`) interceptando todas as requisições
- Controle de acesso baseado em roles (ADMIN / USER)
- Senhas criptografadas com BCrypt
- Proteção de endpoints por perfil

### 👤 Gestão de Usuários (somente ADMIN)
- Criação de usuários com definição de role
- Listagem, busca por ID, edição e exclusão
- Respostas sem exposição de dados sensíveis (UserResponseDTO)

### 🧪 Testes Automatizados
- Testes unitários de Use Cases (JUnit 5 + Mockito)
- Testes de controllers com MockMvc e @WebMvcTest
- Testes de segurança validando rotas protegidas (401 / 403)
- Perfil de teste isolado com banco H2 em memória

### 🐳 DevOps
- Containerização completa com Dockerfile e Docker Compose
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

O projeto segue **Clean Architecture** com separação por domínio — cada domínio tem suas próprias camadas independentes:

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
│   └── SwaggerConfig  → documentação da API
└── exceptions/
    ├── BusinessException
    ├── NotFoundException
    └── GlobalExceptionHandler
```

### Princípio de cada camada

- **Controller** — recebe a requisição HTTP e delega para o Use Case
- **Use Case** — executa a regra de negócio, chama o Repository
- **Domain** — entidade pura, sem dependência de framework
- **Repository** — acesso ao banco de dados
- **DTO** — transporta dados entre camadas sem expor a entidade

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
Registra no SecurityContextHolder
        ↓
SecurityConfig verifica o role e libera ou bloqueia
```

---

## ▶️ Como rodar o projeto

### ✅ Opção 1 — Docker Compose (recomendado)

Pré-requisitos: **Docker** instalado.

```bash
# Clone o repositório
git clone https://github.com/MateusEneas/eneascell.git
cd eneascell

# Sobe a aplicação + banco de dados
docker compose up --build
```

A aplicação sobe em `http://localhost:8080`.

O primeiro admin é criado automaticamente pelo DataSeeder:

```
email: admin@eneas.com
senha: admin123
```

---

### Opção 2 — Rodando localmente

Pré-requisitos: **Java 21**, **Maven**, **PostgreSQL**.

```bash
# Clone o repositório
git clone https://github.com/MateusEneas/eneascell.git
cd eneascell

# Configure o banco no application-dev.properties
# Rode a aplicação
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
3. Clique em **Authorize 🔒** no topo da página
4. Cole o token e clique em Authorize
5. Todos os endpoints passam a funcionar autenticados

---

## 🧪 Testes

```bash
mvn test
```

O projeto usa perfil `test` com banco H2 em memória — não precisa de PostgreSQL para rodar os testes.

O pipeline de CI roda os testes automaticamente a cada push no GitHub.

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
    "nome": "Capinha iPhone",
    "preco": 25.00,
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

- [ ] Frontend em Angular com painel administrativo
- [ ] Módulo de vendas
- [ ] Refresh Token
- [ ] Deploy em ambiente cloud

---

## 👨‍💻 Autor

**Mateus Enéas**

🔗 [GitHub](https://github.com/MateusEneas)
🔗 [LinkedIn](https://linkedin.com/in/mateus-eneas)

---

## 📄 Licença

Projeto de uso pessoal para fins de estudo e portfólio.