# 🎓 SecureEdu-API: Enterprise Student Management Backend
🚀 **A Highly Secure, Cloud-Deployed RESTful Backend Architecture built with Spring Boot, Spring Security, and JWT for Enterprise Student Operations.**
---
<p align="center">
  <img src="https://img.shields.io/badge/Spring--Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Java--21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring--Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white" alt="Spring Security" />
  <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white" alt="JWT" />
  <img src="https://img.shields.io/badge/Railway-0B0D0E?style=for-the-badge&logo=railway&logoColor=white" alt="Railway" />
</p>
---
## 🌐 Production Cloud Environment
The backend services are completely live and running on the enterprise cloud tier:
* **Production Base URL:** `https://myfirstapibackend-production.up.railway.app`
> 🔒 **Security Protocol Warning:** Since this architecture enforces a strict Spring Security layout, direct unauthorized hits to internal resources will trigger a `403 Forbidden` status. Valid bearer authorization is mandatory.
---
## ⚡ Technical Architecture & Engineering Highlights
* **[span_1](start_span)🔐 Enterprise-Grade Security:** Engineered role-based state protection by mapping custom user filters (`JwtFilter`) and validation components (`JwtUtil`) to secure data layers seamlessly[span_1](end_span).
* **[span_2](start_span)🛡️ Database-Driven Authentication:** Integrated a customized user lifecycle using `UserDetailsService` and `BCryptPasswordEncoder` to safely authorize operations using secure hashed keys[span_2](end_span).
* **📦 Complete DevOps Pipeline:** Developed a structural migration system routing local repository pushes to functional production containers on Railway Cloud.
* **💾 Adaptive ORM Layer:** Configured **Spring Data JPA** with a dynamic structural synchronization workflow (`update`), currently mapped to an optimized embedded **H2 Memory Database** layout for rapid staging.
---
## 📁 Modular Project Layout
```text
myfirstapi/
├── src/main/java/com/example/myfirstapi/
│   ├── controller/      # Exposes Protected REST Endpoints (Student & Authentication)
│   ├── model/           # Enterprise Blueprints & Structural Mapping (Student ↔ Laptop)
│   ├── repository/      # High-level Data Intermediary Extensions (JPA JpaRepository)
│   └── security/        # Custom Identity Interceptors & Core Encryption Schemes
└── src/main/resources/
    └── application.properties # Dynamic Pipeline Variables & Database Profiles
```
## 🔌 API Endpoint Blueprint Specification
### 🔑 Authentication Gateways

| HTTP Request | Route Pathway | Payload Schema | Clearance Access |
| :--- | :--- | :--- | :--- |
| <kbd>POST</kbd> | /auth/register | Identity Profile (JSON) | **Public** |
| <kbd>POST</kbd> | /auth/login | Security Credentials (JSON) | **Public** | <br> ### 🎓 Secure Core Services
| HTTP Request | Route Pathway | Payload Schema | Clearance Access |
| :--- | :--- | :--- | :--- |
| <kbd>GET</kbd> | /get-all-students | None | **Secured (Valid Token)** |
| <kbd>POST</kbd> | /add-student | Student Profile (JSON) | **Secured (Valid Token)** |

## 🛠️ Step-by-Step Postman Validation Manual
### 📂 Step 1: Secure Token Generation
Send a standard <kbd>POST</kbd> instruction to the authorization endpoint with your structured credentials payload:
```json
{
  "username": "your_username",
  "password": "your_password"
}
```
*Extract and copy the continuous hashed string inside the token property from the success response.*
### 📂 Step 2: Accessing Protected Operational Routes
 1. Create your functional transaction tab inside Postman (e.g., GET pointing to /get-all-students).
 2. Head into the **Authorization** nested configuration panel underneath the request path.
 3. Switch the active mechanism type to **Bearer Token**.
 4. Inject the copied string from Step 1 directly into the input token field and click **Send**.
```
