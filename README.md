# 🏢 Staff Sync API

> **A Spring Boot REST API for managing office seat allocations and employee-project assignments with automated DTO mapping and null-safe data handling.**

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen?style=flat-square)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue?style=flat-square)](https://maven.apache.org/)
[![H2 Database](https://img.shields.io/badge/H2%20Database-In--Memory-lightblue?style=flat-square)](https://www.h2database.com/)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Architecture](#project-architecture)
- [Quick Start](#quick-start)
- [API Endpoints](#api-endpoints)
- [Database Models](#database-models)
- [Usage Examples](#usage-examples)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Key Concepts](#key-concepts)
- [Error Handling](#error-handling)
- [Dependencies](#dependencies)
- [Testing](#testing)
- [Support](#support)

---

## 🎯 Overview

Staff Sync API is a robust REST API designed to manage office resources and employee assignments. It provides seamless integration for:

- 🪑 **Seat Management**: Track and allocate office seats
- 👥 **Employee Management**: Manage employee information and assignments
- 📊 **Project Assignment**: Assign employees to multiple projects
- 🔄 **Associations**: Link employees to seats and projects

Built with Spring Boot 4.0.5 and Java 21, this API follows clean architecture principles with clear separation of concerns.

---

## ✨ Features

✅ **Full CRUD Operations** - Create, Read, Update, Delete for all entities  
✅ **Automated DTO Mapping** - Seamless conversion between entities and DTOs  
✅ **Null-Safe Data Handling** - Smart null checks and validation  
✅ **RESTful API Design** - Clean HTTP conventions  
✅ **Layered Architecture** - API → Application → Domain → Infrastructure  
✅ **H2 Database** - In-memory database for development and testing  
✅ **Input Validation** - Comprehensive validation using Jakarta Validation  
✅ **Lombok Integration** - Reduced boilerplate code  
✅ **Transaction Management** - Proper transaction handling for complex operations  

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 21 | Programming Language |
| **Spring Boot** | 4.0.5 | Framework |
| **Spring Data JPA** | Latest | ORM & Database Access |
| **H2 Database** | Latest | In-Memory Database |
| **Lombok** | Latest | Boilerplate Reduction |
| **Jakarta Validation** | Latest | Input Validation |
| **Maven** | 3.6+ | Build Tool |

---

## 🏗 Project Architecture

```
📦 staff-sync-api
├── 🎯 API Layer (api/)
│   ├── controller/       # REST Controllers
│   │   ├── AssentoController
│   │   ├── FuncionarioController
│   │   └── ProjetoController
│   └── dto/
│       ├── request/      # Request DTOs
│       └── response/     # Response DTOs
│
├── ⚙️ Application Layer (application/)
│   ├── service/          # Business Logic
│   │   ├── AssentoService
│   │   ├── FuncionarioService
│   │   └── ProjetoService
│   └── mapper/           # Entity ↔ DTO Mapping
│       ├── AssentoMapper
│       ├── FuncionarioMapper
│       └── ProjetoMapper
│
├── 🎲 Domain Layer (domain/)
│   ├── entity/           # JPA Entities
│   │   ├── Assento
│   │   ├── Funcionario
│   │   └── Projeto
│   └── exception/        # Custom Exceptions
│       ├── AssentoNotFound
│       ├── FuncionarioNotFound
│       └── ProjetoNotFound
│
└── 💾 Infrastructure Layer (infrastructure/)
    └── persistence/      # Repository Interfaces
        ├── AssentoRepository
        ├── FuncionarioRepository
        └── ProjetoRepository
```

---

## 🚀 Quick Start

### Prerequisites

- ☕ Java 21 or higher
- 🔨 Maven 3.6+
- 📝 Git

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/danielsismer/staff-sync-api.git
   cd staff-sync-api/09-04
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   # or on Windows
   mvnw.cmd clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   # or on Windows
   mvnw.cmd spring-boot:run
   ```

4. **Access the API**
   - 🌐 API Base URL: `http://localhost:8082`
   - 📊 H2 Console: `http://localhost:8082/h2-console`
   - 🔗 JDBC URL (H2): `jdbc:h2:mem:testdb`

---

## 📡 API Endpoints

### 🪑 Assentos (Seats)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| **POST** | `/assentos` | Create a new seat | 201 |
| **GET** | `/assentos` | List all seats | 200 |
| **GET** | `/assentos/{id}` | Get seat by ID | 200 |
| **PUT** | `/assentos/{id}` | Update seat | 200 |
| **DELETE** | `/assentos/{id}` | Delete seat | 204 |

### 👥 Funcionarios (Employees)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| **POST** | `/funcionarios` | Create a new employee | 201 |
| **GET** | `/funcionarios` | List all employees | 200 |
| **GET** | `/funcionarios/{id}` | Get employee by ID | 200 |
| **PUT** | `/funcionarios/{id}` | Update employee | 200 |
| **DELETE** | `/funcionarios/{id}` | Delete employee | 204 |
| **POST** | `/funcionarios/assento/{funcionarioId}/{assentoId}` | Assign seat to employee | 200 |
| **POST** | `/funcionarios/projeto/{funcionarioId}` | Assign projects to employee | 200 |

### 📊 Projetos (Projects)

| Method | Endpoint | Description | Status |
|--------|----------|-------------|--------|
| **POST** | `/projetos` | Create a new project | 201 |
| **GET** | `/projetos` | List all projects | 200 |
| **GET** | `/projetos/{id}` | Get project by ID | 200 |
| **PUT** | `/projetos/{id}` | Update project | 200 |
| **DELETE** | `/projetos/{id}` | Delete project | 204 |

---

## 💾 Database Models

### 👤 Funcionario Entity

**Table: funcionario**

| Field | Type | Constraints |
|-------|------|-------------|
| `id` | BIGINT | PK, Auto-increment |
| `nome` | VARCHAR(255) | NOT NULL |
| `assento_id` | BIGINT | FK (OneToOne) |

**Relationships:**
- ✅ OneToOne with `Assento` (Employee has one seat)
- ✅ ManyToMany with `Projeto` (Employee can work on multiple projects)

```java
@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Assento assento;
    
    @ManyToMany
    private List<Projeto> projetos = new ArrayList<>();
}
```

### 🪑 Assento Entity

**Table: assento**

| Field | Type | Constraints |
|-------|------|-------------|
| `id` | BIGINT | PK, Auto-increment |
| `codigo` | VARCHAR(255) | NOT NULL |

**Relationships:**
- ✅ OneToOne with `Funcionario` (Seat assigned to one employee)

```java
@Entity
@Table(name = "assento")
public class Assento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    
    @OneToOne(mappedBy = "assento")
    private Funcionario funcionario;
}
```

### 📊 Projeto Entity

**Table: projeto**

| Field | Type | Constraints |
|-------|------|-------------|
| `id` | BIGINT | PK, Auto-increment |
| `nome` | VARCHAR(255) | NOT NULL |

**Relationships:**
- ✅ ManyToMany with `Funcionario` (Project has multiple employees)

```java
@Entity
@Table(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    @ManyToMany(mappedBy = "projetos")
    @JsonIgnore
    private List<Funcionario> funcionarios = new ArrayList<>();
}
```

### 📊 Entity Relationship Diagram (ERD)

```
┌─────────────────┐         ┌──────────────┐
│   Funcionario   │         │   Assento    │
├─────────────────┤         ├──────────────┤
│ id (PK)         │◄────┐   │ id (PK)      │
│ nome            │     │   │ codigo       │
│ assento_id (FK) ├─────┘   └──────────────┘
│                 │
└────────┬────────┘
         │
    ┌────▼─────────────────────────┐
    │   ManyToMany Join Table       │
    │  (funcionario_projetos)       │
    ├──────────────────────────────┤
    │ funcionario_id (FK)           │
    │ projetos_id (FK)              │
    └────┬──────────────────────┬───┘
         │                      │
         │                      │
    ┌────▼───────────────────────▼───┐
    │        Projeto                  │
    ├─────────────────────────────────┤
    │ id (PK)                         │
    │ nome                            │
    └─────────────────────────────────┘
```

---

## 📝 Usage Examples

### 1️⃣ Create a Seat

**Request:**
```bash
curl -X POST http://localhost:8082/assentos \
  -H "Content-Type: application/json" \
  -d '{
    "codigo": "A-001"
  }'
```

**Response (201 Created):**
```json
{
  "id": 1,
  "codigo": "A-001"
}
```

---

### 2️⃣ Create an Employee

**Request:**
```bash
curl -X POST http://localhost:8082/funcionarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva"
  }'
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "assentoId": null,
  "projetos": []
}
```

---

### 3️⃣ Create a Project

**Request:**
```bash
curl -X POST http://localhost:8082/projetos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "E-commerce Platform"
  }'
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nome": "E-commerce Platform"
}
```

---

### 4️⃣ Assign Seat to Employee

**Request:**
```bash
curl -X POST http://localhost:8082/funcionarios/assento/1/1 \
  -H "Content-Type: application/json"
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "assentoId": 1,
  "projetos": []
}
```

---

### 5️⃣ Assign Projects to Employee

**Request:**
```bash
curl -X POST http://localhost:8082/funcionarios/projeto/1 \
  -H "Content-Type: application/json" \
  -d '[1, 2, 3]'
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "assentoId": 1,
  "projetos": [
    {
      "id": 1,
      "nome": "E-commerce Platform"
    },
    {
      "id": 2,
      "nome": "Mobile App"
    },
    {
      "id": 3,
      "nome": "Analytics Dashboard"
    }
  ]
}
```

---

### 6️⃣ Get All Employees

**Request:**
```bash
curl -X GET http://localhost:8082/funcionarios
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "assentoId": 1,
    "projetos": [...]
  },
  {
    "id": 2,
    "nome": "Maria Santos",
    "assentoId": 2,
    "projetos": [...]
  }
]
```

---

### 7️⃣ Get Employee by ID

**Request:**
```bash
curl -X GET http://localhost:8082/funcionarios/1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "assentoId": 1,
  "projetos": [
    {
      "id": 1,
      "nome": "E-commerce Platform"
    }
  ]
}
```

---

### 8️⃣ Update Employee

**Request:**
```bash
curl -X PUT http://localhost:8082/funcionarios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva Junior"
  }'
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva Junior",
  "assentoId": 1,
  "projetos": [...]
}
```

---

### 9️⃣ Delete Employee

**Request:**
```bash
curl -X DELETE http://localhost:8082/funcionarios/1
```

**Response (204 No Content)**

---

### 🔟 Get All Seats

**Request:**
```bash
curl -X GET http://localhost:8082/assentos
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "codigo": "A-001"
  },
  {
    "id": 2,
    "codigo": "A-002"
  }
]
```

---

### 1️⃣1️⃣ Get All Projects

**Request:**
```bash
curl -X GET http://localhost:8082/projetos
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "E-commerce Platform"
  },
  {
    "id": 2,
    "nome": "Mobile App"
  },
  {
    "id": 3,
    "nome": "Analytics Dashboard"
  }
]
```

---

## 📂 Project Structure

```
staff-sync-api/
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── .mvn/
│   └── wrapper/
└── 09-04/
    ├── pom.xml
    ├── mvnw
    ├── mvnw.cmd
    ├── .gitignore
    ├── .gitattributes
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── com/aula/__04/
        │   │       ├── Application.java
        │   │       ├── api/
        │   │       │   ├── controller/
        │   │       │   │   ├── AssentoController.java
        │   │       │   │   ├── FuncionarioController.java
        │   │       │   │   └── ProjetoController.java
        │   │       │   └── dto/
        │   │       │       ├── request/
        │   │       │       │   ├── AssentoRequestDTO.java
        │   │       │       │   ├── FuncionarioRequestDTO.java
        │   │       │       │   └── ProjetoRequestDTO.java
        │   │       │       └── response/
        │   │       │           ├── AssentoResponseDTO.java
        │   │       │           ├── FuncionarioResponseDTO.java
        │   │       │           └── ProjetoResponseDTO.java
        │   │       ├── application/
        │   │       │   ├── service/
        │   │       │   │   ├── AssentoService.java
        │   │       │   │   ├── FuncionarioService.java
        │   │       │   │   └── ProjetoService.java
        │   │       │   └── mapper/
        │   │       │       ├── AssentoMapper.java
        │   │       │       ├── FuncionarioMapper.java
        │   │       │       └── ProjetoMapper.java
        │   │       ├── domain/
        │   │       │   ├── entity/
        │   │       │   │   ├── Assento.java
        │   │       │   │   ├── Funcionario.java
        │   │       │   │   └── Projeto.java
        │   │       │   └── exception/
        │   │       │       ├── AssentoNotFound.java
        │   │       │       ├── FuncionarioNotFound.java
        │   │       │       └── ProjetoNotFound.java
        │   │       └── infrastructure/
        │   │           └── persistence/
        │   │               ├── AssentoRepository.java
        │   │               ├── FuncionarioRepository.java
        │   │               └── ProjetoRepository.java
        │   └── resources/
        │       └── application.properties
        └── test/
```

---

## ⚙️ Configuration

### application.properties

Located at: `09-04/src/main/resources/application.properties`

```properties
# Application Configuration
spring.application.name=09-04
server.port=8082

# H2 Database Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# DataSource Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
```

### Configuration Details

| Property | Value | Description |
|----------|-------|-------------|
| `spring.application.name` | 09-04 | Application name |
| `server.port` | 8082 | Server port |
| `spring.h2.console.enabled` | true | H2 console enabled |
| `spring.h2.console.path` | /h2-console | H2 console path |
| `spring.datasource.url` | jdbc:h2:mem:testdb | In-memory H2 database |
| `spring.datasource.username` | sa | Database username |
| `spring.jpa.hibernate.ddl-auto` | update | Auto-update schema |

---

## 🎓 Key Concepts

### Layered Architecture Pattern

The application follows a clean layered architecture with clear separation of concerns:

```
Request Flow:
┌─────────────┐
│ HTTP Client │
└──────┬──────┘
       │
       ▼
┌──────────────────────────┐
│  API Layer (Controller)  │ ◄─── Handles HTTP requests/responses
├──────────────────────────┤
│ Application Layer        │ ◄─── Business logic & orchestration
│ (Service + Mapper)       │
├──────────────────────────┤
│  Domain Layer            │ ◄─── Core business entities & rules
│  (Entity + Exception)    │
├──────────────────────────┤
│ Infrastructure Layer     │ ◄─── Data access & persistence
│ (Repository)             │
└──────┬──────────────────���┘
       │
       ▼
   ┌────────┐
   │ H2 DB  │
   └────────┘
```

### Benefits of Layered Architecture

- **Separation of Concerns**: Each layer has a specific responsibility
- **Testability**: Layers can be tested independently
- **Maintainability**: Easy to locate and modify code
- **Scalability**: New features can be added without affecting other layers
- **Reusability**: Services and mappers can be reused across controllers

### DTO Pattern (Data Transfer Object)

**Purpose:** Separate API contracts from internal domain models

**Request DTO:**
```java
public record FuncionarioRequestDTO(String nome) {}
```

**Response DTO:**
```java
public record FuncionarioResponseDTO(
    Long id,
    String nome,
    Long assentoId,
    List<Projeto> projetos
) {}
```

**Benefits:**
- ✅ Input validation at API boundary
- ✅ Flexible response formatting
- ✅ Protection of domain entities
- ✅ API versioning support

### Mapper Pattern

Automated entity-to-DTO conversion:

```java
@Component
public class FuncionarioMapper {
    
    public Funcionario toEntity(FuncionarioRequestDTO dto) {
        return new Funcionario(dto.nome());
    }
    
    public FuncionarioResponseDTO toResponse(Funcionario entity) {
        return new FuncionarioResponseDTO(
            entity.getId(),
            entity.getNome(),
            entity.getAssento() != null ? entity.getAssento().getId() : null,
            entity.getProjetos()
        );
    }
}
```

**Features:**
- ✅ Null-safe conversions
- ✅ Automatic field mapping
- ✅ Type safety
- ✅ Reusable across services

### Repository Pattern

Abstract data access layer:

```java
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    // Spring Data JPA auto-implements CRUD operations
}
```

**Spring Data JPA Provides:**
- `save()` - Create/Update
- `findById()` - Read
- `findAll()` - List all
- `delete()` - Delete
- `deleteById()` - Delete by ID

---

## 🔍 Error Handling

### Custom Exceptions

The API includes custom exceptions for better error handling:

```java
public class AssentoNotFound extends RuntimeException {
    public AssentoNotFound() {
        super("Assento não encontrado");
    }
}

public class FuncionarioNotFound extends RuntimeException {
    public FuncionarioNotFound() {
        super("Funcionário não encontrado");
    }
}

public class ProjetoNotFound extends RuntimeException {
    public ProjetoNotFound() {
        super("Projeto não encontrado");
    }
}
```

### Error Response Examples

**404 Not Found:**
```json
{
  "timestamp": "2026-04-09T21:54:20Z",
  "status": 404,
  "error": "Not Found",
  "message": "Funcionário não encontrado",
  "path": "/funcionarios/999"
}
```

**400 Bad Request (Validation Error):**
```json
{
  "timestamp": "2026-04-09T21:54:20Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": {
    "nome": "must not be blank"
  }
}
```

**500 Internal Server Error:**
```json
{
  "timestamp": "2026-04-09T21:54:20Z",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred"
}
```

---

## 📚 Dependencies

### pom.xml

Located at: `09-04/pom.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>4.0.5</version>
    </parent>
    
    <groupId>com.aula</groupId>
    <artifactId>09-04</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <!-- Web & REST -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webmvc</artifactId>
        </dependency>
        
        <!-- Data Access -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <!-- Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- H2 Console -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-h2console</artifactId>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        
        <!-- Development Tools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        
        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webmvc-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

### Dependency Descriptions

| Dependency | Purpose | Version |
|-----------|---------|---------|
| spring-boot-starter-webmvc | Web & MVC support | Latest |
| spring-boot-starter-data-jpa | JPA & Hibernate | Latest |
| spring-boot-starter-validation | Input validation | Latest |
| h2 | In-memory database | Latest |
| spring-boot-h2console | H2 web console | Latest |
| lombok | Reduce boilerplate | Latest |
| spring-boot-devtools | Development tools | Latest |

---

## 🧪 Testing

### Running Tests

Execute tests using Maven:

```bash
# Run all tests
./mvnw test

# Run specific test class
./mvnw test -Dtest=FuncionarioControllerTest

# Run specific test method
./mvnw test -Dtest=FuncionarioControllerTest#testFindById

# Skip tests during build
./mvnw clean install -DskipTests
```

### Recommended Test Structure

```
src/test/java/com/aula/__04/
├── api/
│   └── controller/
│       ├── AssentoControllerTest.java
│       ├── FuncionarioControllerTest.java
│       └── ProjetoControllerTest.java
├── application/
│   ├── service/
│   │   ├── AssentoServiceTest.java
│   │   ├── FuncionarioServiceTest.java
│   │   └── ProjetoServiceTest.java
│   └── mapper/
│       ├── AssentoMapperTest.java
│       ├── FuncionarioMapperTest.java
│       └── ProjetoMapperTest.java
└── integration/
    └── FuncionarioIntegrationTest.java
```

---

## 📞 Support

### Repository Information

- **Repository Name**: staff-sync-api
- **Repository URL**: https://github.com/danielsismer/staff-sync-api
- **Owner**: danielsismer
- **Language**: Java
- **License**: Open Source
- **Created**: April 9, 2026

### Getting Help

1. **Check the API Endpoints section** for endpoint details
2. **Review Usage Examples** for common operations
3. **Visit H2 Console** at `http://localhost:8082/h2-console` for database inspection
4. **Check Application Logs** for detailed error information
5. **Open an Issue** on GitHub for bugs or feature requests

### Common Issues

**Issue: Port 8082 is already in use**
```bash
# Change port in application.properties
server.port=8083
```

**Issue: H2 Console not accessible**
```bash
# Ensure H2 is enabled in application.properties
spring.h2.console.enabled=true
```

**Issue: Database connection error**
```bash
# Verify JDBC URL
spring.datasource.url=jdbc:h2:mem:testdb
```

---

## 🚀 Getting Started - Quick Reference

```bash
# 1. Clone repository
git clone https://github.com/danielsismer/staff-sync-api.git

# 2. Navigate to project
cd staff-sync-api/09-04

# 3. Build project
./mvnw clean install

# 4. Run application
./mvnw spring-boot:run

# 5. Test the API
curl -X GET http://localhost:8082/funcionarios

# 6. Access H2 Console
open http://localhost:8082/h2-console
```

---

## 📝 Development Guidelines

### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Add comments for complex logic
- Implement proper error handling

### Best Practices

- ✅ Always validate user input
- ✅ Use transactions for multi-entity operations
- ✅ Implement proper logging
- ✅ Write unit tests for services
- ✅ Document public APIs
- ✅ Keep DTOs synchronized with API contracts
- ✅ Use lazy loading when appropriate
- ✅ Implement proper exception handling

---

## 🔄 Workflow Example

### Complete Workflow: Create Employee and Assign Resources

```bash
# Step 1: Create a Seat
SEAT_ID=$(curl -s -X POST http://localhost:8082/assentos \
  -H "Content-Type: application/json" \
  -d '{"codigo":"A-001"}' | jq '.id')

# Step 2: Create Projects
PROJECT_1=$(curl -s -X POST http://localhost:8082/projetos \
  -H "Content-Type: application/json" \
  -d '{"nome":"E-commerce"}' | jq '.id')

PROJECT_2=$(curl -s -X POST http://localhost:8082/projetos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Mobile App"}' | jq '.id')

# Step 3: Create Employee
EMP_ID=$(curl -s -X POST http://localhost:8082/funcionarios \
  -H "Content-Type: application/json" \
  -d '{"nome":"João Silva"}' | jq '.id')

# Step 4: Assign Seat to Employee
curl -X POST http://localhost:8082/funcionarios/assento/$EMP_ID/$SEAT_ID

# Step 5: Assign Projects to Employee
curl -X POST http://localhost:8082/funcionarios/projeto/$EMP_ID \
  -H "Content-Type: application/json" \
  -d "[$PROJECT_1, $PROJECT_2]"

# Step 6: View Employee Details
curl -X GET http://localhost:8082/funcionarios/$EMP_ID | jq
```

---

## 📖 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [Jakarta Validation](https://jakarta.ee/learn/validate/)
- [Lombok Features](https://projectlombok.org/features/all)
- [H2 Database](https://www.h2database.com/)
- [REST API Best Practices](https://restfulapi.net/)

---

## 🎉 Get Started Now!

Ready to manage your office resources? Follow the [Quick Start](#quick-start) guide and start building!

```bash
git clone https://github.com/danielsismer/staff-sync-api.git && \
cd staff-sync-api/09-04 && \
./mvnw spring-boot:run
```

---

<div align="center">

**Made with ❤️ by [danielsismer](https://github.com/danielsismer)**

⭐ If you find this useful, please consider giving it a star!

[GitHub](https://github.com/danielsismer/staff-sync-api) • [Issues](https://github.com/danielsismer/staff-sync-api/issues)

</div>
