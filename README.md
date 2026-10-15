# ☕ DIO - Design Patterns with Java & Spring Boot
RESTful API developed as part of the **"Design Patterns with Java: From Classics (GoF) to Spring Framework"** lab by [Digital Innovation One (DIO)](https://dio.me).
This project demonstrates practical implementation of architectural and behavioral design patterns using Spring Boot, integrating with the external **ViaCEP** public API and managing customer memberships and discount strategies.
---
## 🏛️ Design Patterns Implemented
| Pattern | Type | How it is applied in this project |
| :--- | :--- | :--- |
| **Singleton** | Creational (GoF) | Managed natively by the Spring IoC container. Components annotated with `@Service`, `@RestController`, and `@Repository` are registered as singletons across the application lifecycle. |
| **Strategy** | Behavioral (GoF) | Encapsulates membership discount algorithms (`DiscountStrategy`). Strategies for `STANDARD`, `VIP`, and `PREMIUM` membership tiers can be selected dynamically at runtime via `DiscountContext` without `if/else` ladders. |
| **Facade** | Structural (GoF) | `CustomerServiceImpl` acts as a facade, hiding the orchestration complexity between multiple subsystems: `CustomerRepository`, `AddressRepository`, `ViaCepService` (OpenFeign HTTP client), and `DiscountContext`. |
---
## 🛠️ Tech Stack
- **Java 17 / 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Cloud OpenFeign** (Declarative REST client for ViaCEP)
- **H2 Database** (In-memory SQL database for easy local setup)
- **Maven** (Dependency management and build tool)
---
## 🚀 How to Run Locally
### Prerequisites
- JDK 17 or higher
- Maven 3.8+ (or use the included `./mvnw` wrapper)
- Git
### Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/devlucasnascimento81/dio-spring-design-patterns.git
   cd dio-spring-design-patterns

  

  

  

Run with Maven:


  
bash
./mvnw spring-boot:run

  

(Or run directly via your IDE using the main class DioSpringDesignPatternsApplication)


  

  

  

The API will be available at: http://localhost:8080


  

  

  

  
📡 API Endpoints

  
1. List all customers

  

  
Method: GET

  
URL: http://localhost:8080/customers

  

  
2. Find customer by ID

  

  
Method: GET

  
URL: http://localhost:8080/customers/{id}

  

  
3. Register a new customer

  

  
Method: POST

  
URL: http://localhost:8080/customers

  
Body (JSON):
  
json
{
  "name": "Maria Silva",
  "membershipType": "VIP",
  "address": {
    "cep": "01001000"
  }
}

  (The API will automatically query ViaCEP and populate the street, neighborhood, city, and state).

  

  
4. Update customer

  

  
Method: PUT

  
URL: http://localhost:8080/customers/{id}

  
Body (JSON):
  
json
{
  "name": "Maria Silva Santos",
  "membershipType": "PREMIUM",
  "address": {
    "cep": "01001000"
  }
}

  

  

  
5. Delete customer

  

  
Method: DELETE

  
URL: http://localhost:8080/customers/{id}

  

  
6. Calculate purchase with membership discount (Strategy Pattern)

  

  
Method: GET

  
URL: http://localhost:8080/customers/{id}/discount?amount=100.0

  
Response:
  
  

  
STANDARD: returns 100.0 (0% discount)

  
VIP: returns 90.0 (10% discount)

  
PREMIUM: returns 80.0 (20% discount)

  

  

  

  
📂 Project Structure

  
text
src/main/java/dio_spring_design_patterns/
├── controller/
│   └── CustomerRestController.java      # REST endpoints
├── model/
│   ├── Address.java                     # Address entity (ViaCEP mapping)
│   └── Customer.java                    # Customer entity
├── repository/
│   ├── AddressRepository.java           # Spring Data JPA repository
│   └── CustomerRepository.java          # Spring Data JPA repository
├── service/
│   ├── CustomerService.java             # Business interface
│   ├── CustomerServiceImpl.java         # Facade pattern implementation
│   ├── ViaCepService.java               # OpenFeign client for ViaCEP
│   └── strategy/
│       ├── DiscountStrategy.java        # Strategy interface
│       ├── StandardDiscountStrategy.java# Standard plan (no discount)
│       ├── VipDiscountStrategy.java     # VIP plan (10% discount)
│       ├── PremiumDiscountStrategy.java # Premium plan (20% discount)
│       └── DiscountContext.java         # Strategy dispatcher
└── DioSpringDesignPatternsApplication.java

  

  
👨‍💻 Author

  

Developed by Lucas Nascimento
 during the DIO Java bootcamp.


  
