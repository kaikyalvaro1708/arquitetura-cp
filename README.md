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

## 📖 Exemplos de Uso

### **1. Criar Paciente**

```bash
POST /patients
Content-Type: application/json

{
  "name": "Fernanda Costa",
  "cpf": "55566677788",
  "email": "fernanda.costa@example.com"
}
```

### **2. Criar Médico**

```bash
POST /doctors
Content-Type: application/json

{
  "name": "Dr. Henrique Almeida",
  "crm": "CRM-RS-775599"
}
```

### **3. Agendar Consulta**

```bash
POST /appointments
Content-Type: application/json

{
  "patientId": 2,
  "doctorId": 1,
  "dateTime": "2025-10-03T16:45:00Z"
}
```