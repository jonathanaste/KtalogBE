KtalogBE: E-commerce Catalog Backend

KtalogBE is a modern, production-grade Ktor backend server written in Kotlin that implements a complete e-commerce catalog management system with JWT-based authentication, role-based access control, and database persistence. Despite its "KMP" naming, this is currently a JVM-only backend API serving as a catalog/shopping cart platform.

Core Architecture and Strong Points

1. Modern Tech Stack
Kotlin 2.0.0 - Latest language version with improved performance and IDE support
Ktor 2.3.13 - Lightweight, asynchronous web framework built on Kotlin coroutines
PostgreSQL + Exposed ORM 0.50.0 - Type-safe SQL DSL with JSONB support for flexible data structures
Flyway 10.15.2 - Database version control and automated migrations
Koin 3.5.6 - Lightweight, pragmatic dependency injection
2. Enterprise Security
JWT Authentication - Stateless token-based auth with HS256 signing
Role-Based Access Control (RBAC) - USER and ADMIN roles with protected endpoints
BCrypt Password Hashing - Secure credential storage (jbcrypt 0.4)
CORS Support - Cross-origin request handling built-in
Authorization Header Parsing - Standard Bearer token validation
3. Production-Ready Infrastructure
Content Negotiation - Automatic JSON serialization/deserialization via kotlinx.serialization
HikariCP Connection Pooling (5.1.0) - High-performance database connection management
Structured Error Handling - HTTP status pages and exception mapping
Swagger/OpenAPI - API documentation and discovery
Docker Support - Fat JAR packaging and Docker image generation
Health Checks - Application readiness verification
4. Performance & Scalability
Asynchronous/Non-blocking I/O - Ktor's coroutine-based architecture for handling concurrent requests
Connection Pooling - Efficient database resource management
Stateless Design - JWT eliminates session management overhead, enabling horizontal scaling

Main Features & Functionality

API Endpoints (from Catalog.http test file):
Authentication
POST /auth/register - User registration (creates CLIENT role by default)
POST /auth/login - JWT token generation
Shopping Cart
GET /cart - Protected endpoint (requires Bearer token)
Admin Management
POST /admin/categories - Create product categories (ADMIN only)
POST /admin/products - Create/manage products with SKU, pricing, stock tracking (ADMIN only)
Product Management
Full product lifecycle: SKU management, pricing (retail + cost), image URLs, stock levels
Supplier association and consignment tracking
Category hierarchy
Key Technologies & Libraries
Technology
Version
Purpose
Kotlin
2.0.0
Core language
Ktor Server
2.3.13
Web framework & routing
Exposed
0.50.0
Type-safe ORM with JSONB
PostgreSQL
42.7.3
Relational database
Flyway
10.15.2
Database migrations
Koin
3.5.6
Dependency injection
JWT Auth
2.3.12
Token-based authentication
Logback
1.5.13
Structured logging
HikariCP
5.1.0
Connection pooling


Development Metrics
Language: 100% Kotlin
Build System: Gradle Kotlin DSL
Repository Size: 227 KB (minimal, focused codebase)
Created: June 8, 2025
Last Updated: August 1, 2025
Public Repository: Yes
Deployment Capabilities
Gradle Tasks Available:
buildFatJar - Standalone JAR with all dependencies
buildImage - Docker container image
runDocker - Run containerized version locally
test - Full test suite execution
Runtime: Netty engine on port 8080
Configuration: YAML-based (ktor-server-config-yaml)
Use Cases
E-commerce catalog backend
Product inventory management
Shopping cart service
Multi-tenant SaaS catalog system
REST API for mobile/web frontends
This is a well-engineered, focused backend solution with modern best practices for authentication, database management, and scalability—suitable for production deployment.

