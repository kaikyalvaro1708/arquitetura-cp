# Clínica API — Spring Boot

### Disciplina: SOA — Prof. Salatiel Luz Marinho

### Turma: 3ESPW

### Aluno: Kaiky Alvaro de Miranda

### RM: 98118

---

## 📋 Descrição do Projeto

API REST desenvolvida em Spring Boot para gerenciamento de uma clínica médica, permitindo o controle de pacientes, médicos e consultas. O sistema oferece funcionalidades completas de CRUD e operações específicas para agendamento e controle de consultas.

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.1**
- **Spring Data JPA**
- **Spring Boot Validation**
- **Oracle Database** (OJDBC11)
- **Flyway** (Migração de banco de dados)
- **Lombok** (Redução de boilerplate)
- **SpringDoc OpenAPI 3** (Swagger UI)
- **Maven** (Gerenciador de dependências)
- **Testcontainers** (Testes de integração)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
src/main/java/com/example/clinica/
├── controller/          # Camada de apresentação (REST Controllers)
├── dto/                 # Data Transfer Objects
│   ├── request/         # DTOs para requisições
│   └── response/        # DTOs para respostas
├── entity/              # Entidades JPA
├── repository/          # Camada de acesso a dados
├── service/             # Camada de negócio
└── exception/           # Tratamento de exceções
```

## 📊 Modelo de Dados

### Entidades Principais:

- **Patient** (Paciente): Informações básicas do paciente
- **Doctor** (Médico): Dados do médico e especialização
- **Appointment** (Consulta): Agendamentos entre pacientes e médicos

## 🚀 Funcionalidades

### 👥 Pacientes (`/patients`)

- ✅ **POST** `/patients` - Criar novo paciente
- ✅ **GET** `/patients` - Listar pacientes com paginação

### 👨‍⚕️ Médicos (`/doctors`)

- ✅ **POST** `/doctors` - Cadastrar novo médico
- ✅ **GET** `/doctors` - Listar médicos com paginação
- ✅ **GET** `/doctors/{id}` - Buscar médico por ID

### 📅 Consultas (`/appointments`)

- ✅ **POST** `/appointments` - Agendar nova consulta
- ✅ **GET** `/appointments` - Listar consultas com paginação
- ✅ **PUT** `/appointments/{id}/cancel` - Cancelar consulta
- ✅ **PUT** `/appointments/{id}/confirm` - Confirmar consulta

## 📋 Requisitos

- **Java 21**
- **Maven 3.9+**
- **Oracle Database** (configurado conforme application.yml)
- **Docker** (para testes com Testcontainers)

## ⚙️ Configuração

### Banco de Dados

O projeto está configurado para usar Oracle Database:

```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=oracle.fiap.com.br)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=ORCL)))
    username: rm98118
    password: 170804
    driver-class-name: oracle.jdbc.OracleDriver
```

### Flyway

As migrações de banco são gerenciadas pelo Flyway:

- Localização: `src/main/resources/db/migration/`
- Baseline automático habilitado
- Validação do esquema no startup

## 🚀 Como Executar

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
cd clinica-api
```

### 2. Instalar dependências

```bash
mvn clean install
```

### 3. Executar a aplicação

```bash
mvn spring-boot:run
```

### 4. Acessar a documentação

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Docs**: http://localhost:8080/v3/api-docs

## 🧪 Executar Testes

```bash
# Testes unitários
mvn test

# Testes de integração (requer Docker)
mvn verify
```

## 📖 Exemplos de Uso

### **1. Criar Paciente**

```bash
POST /patients
Content-Type: application/json

{
  "name": "Ana Silva",
  "cpf": "12345678901",
  "email": "ana.silva@example.com"
}
```

### **2. Criar Médico**

```bash
POST /doctors
Content-Type: application/json

{
  "name": "Dr. Carlos Souza",
  "crm": "CRM-SP-123456"
}
```

### **3. Agendar Consulta**

```bash
POST /appointments
Content-Type: application/json

{
  "patientId": 1,
  "doctorId": 1,
  "dateTime": "2025-09-25T14:30:00Z"
}
```

### **4. Listar com Paginação**

```bash
GET /patients?page=0&size=10&sort=name,asc
GET /doctors?page=0&size=5&sort=crm
GET /appointments?page=0&size=20&sort=dateTime,desc
```

## 📚 Documentação da API

A documentação completa da API está disponível através do Swagger UI em:
**http://localhost:8080/swagger-ui.html**

A documentação inclui:

- Descrição de todos os endpoints
- Esquemas de request/response
- Códigos de status HTTP
- Exemplos de uso
- Interface interativa para testes

## 🔍 Validações

O projeto implementa validações robustas:

- **Bean Validation** com anotações `@Valid`
- **Validação de CPF** único para pacientes
- **Validação de CRM** único para médicos
- **Validação de formato** para emails e datas

## 🐛 Tratamento de Erros

Respostas padronizadas para:

- **400** - Bad Request (dados inválidos)
- **404** - Not Found (recurso não encontrado)
- **409** - Conflict (dados duplicados)
- **500** - Internal Server Error

## 📁 Estrutura do Projeto

```
clinica-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/clinica/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/migration/
│   └── test/
├── pom.xml
└── README.md
```
