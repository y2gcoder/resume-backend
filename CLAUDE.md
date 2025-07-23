# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a **resume builder backend service** ("Resu:me") built with Kotlin/Spring Boot, following hexagonal architecture and domain model patterns. The service allows users to register as members and manage their resumes.

## Architecture

- **Hexagonal Architecture**: Clean separation between domain, application, and adapter layers
- **Domain Model Pattern**: Rich domain objects with business logic
- **Layered Structure**: Domain → Application → Adapter
- **Package Structure**:
  - `domain/`: Core business entities and value objects
  - `application/provided/`: Use case interfaces exposed to external actors
  - `application/required/`: Port interfaces for external dependencies
  - `adapter/`: Implementation of external integrations (security, persistence, integration)

## Build & Development Commands

### Building and Running
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run with Docker Compose (includes database)
docker-compose up
```

### Testing
```bash
# Run all tests
./gradlew test

# Run a specific test class
./gradlew test --tests "MemberTest"

# Run tests with continuous mode
./gradlew test --continuous
```

## Key Domain Concepts

### Member Management
- **Member States**: PENDING → ACTIVE → DEACTIVATED
- Members register with email/nickname/password, start in PENDING status
- Must be activated to ACTIVE status to use full features
- Email is used as natural ID (unique identifier)

### Core Entities
- **Member**: User account with email, nickname, password, status
- **Resume**: User's resume with basic profile info and sections
- **Email/PhoneNumber**: Value objects with validation

### Domain Rules
- Password encoding handled by `PasswordEncoder` domain service
- Member state transitions are strictly enforced
- Email uniqueness enforced via `DuplicateEmailException`

## Code Conventions

- **Kotlin**: Primary language, leveraging null safety and data classes
- **Entity Access**: Use domain methods, avoid direct field setter access from outside
- **Constructor Pattern**: Private constructors with factory methods (e.g., `Member.register()`)
- **Testing**: Uses JUnit 5, Kotest, and Mockito with agent configuration
- **Validation**: Jakarta validation annotations on domain objects

## Database Configuration

- **Development**: H2 in-memory database
- **Production**: PostgreSQL via Docker Compose
- **JPA**: Hibernate with `ddl-auto: update`
- **Natural IDs**: Email fields marked with `@NaturalId`

## Testing Framework

- **JUnit 5** with **Kotest** integration
- **Spring Boot Test** with constructor injection enabled (`spring.test.constructor.autowire.mode=all`)
- **Mockito** with agent for mocking capabilities
- Test structure mirrors main package structure

## Documentation References

The project includes comprehensive Korean documentation:
- `domain-model.md`: Complete domain model specification
- `glossary.md`: Business terminology dictionary  
- `개발가이드.md`: Development guidelines and architecture principles

These documents contain detailed business rules and should be consulted when implementing new features.