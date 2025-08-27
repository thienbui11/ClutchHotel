# ClutchHotel – Full-Stack Hotel Management System

[![Project Status](https://img.shields.io/badge/status-in_progress-orange)]()

## Overview

ClutchHotel is a **full-stack hotel management application** developed following a tutorial, enhanced with custom features and structured for real-world readiness. It uses a **Spring Boot** backend and **React.js** frontend to deliver core functionalities like room booking, user authentication, Admin dashboard, and more.

## Features

- **User-facing functionalities**:
  - Room listing and search with filtering/sorting
  - Booking flow: check availability, book room, view booking history
  - User authentication: register, login, logout (JWT-based)
  
- **Admin Panel**:
  - Manage rooms, bookings, and users
  - View dashboard with analytics and stats

- **Project Enhancements**:
  - API documentation via **Swagger/OpenAPI**
  - Improved UI/UX with responsive design and loading states
  - Dockerized backend and frontend for local deployment
  - Authentication & role-based access control

## Tech Stack

| Layer               | Technologies                          |
|--------------------|---------------------------------------|
| Backend            | Spring Boot, Hibernate, JWT     |
| Frontend           | React.js                              |
| Database           | PostgreSQL                            |
| API Documentation  | Swagger/OpenAPI                       |
| DevOps             | Docker, Docker Compose, Git           |

## Getting Started

### Prerequisites

- Java 11+ (JDK)
- Node.js & npm
- Docker & Docker Compose (optional)
- Git

### Setup Steps

1. **Clone the repository**  
   ```bash
   git clone https://github.com/thienbui11/ClutchHotel.git
   cd ClutchHotel
2. **Backend**

- Navigate to `/backend`
- Configure database settings in `application.properties`
- Run the app:
  ```bash
  ./mvnw spring-boot:run
3. **Frontend**

- Navigate to /frontend
- Install dependencies and start React app:
npm install
npm start
- Visit:
  ```bash
  http://localhost:3000
## Demo
