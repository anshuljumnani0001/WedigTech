````md
# URL Shortener

A simple URL Shortener application built using:

- Frontend: React + Vite
- Backend: Spring Boot
- Java + Maven

---

# Project Structure

```bash
urlshortener/
│
├── client/               # React Frontend
│
└── urlshortener/         # Spring Boot Backend
````

---

# Frontend Setup

## Step 1: Go to Client Folder

```bash
cd client
```

## Step 2: Install Dependencies

```bash
npm install
```

## Step 3: Run Frontend

```bash
npm run dev
```

## Frontend URL

```bash
http://localhost:5173
```

---

# Backend Setup

## Step 1: Go to Backend Folder

```bash
cd urlshortener
```

## Step 2: Run Spring Boot Application

### Windows

```bash
.\mvnw.cmd spring-boot:run
```

### Mac/Linux

```bash
./mvnw spring-boot:run
```

## Backend URL

```bash
http://localhost:8080
```

---

# Technologies Used

## Frontend

* React
* Vite
* JavaScript

## Backend

* Spring Boot
* Maven
* Java

---

# Requirements

## Frontend

Install:

* Node.js
* npm

Check installation:

```bash
node -v
npm -v
```

---

## Backend

Install:

* Java 17 or above

Check installation:

```bash
java -version
```

---

# API Connection

Frontend communicates with backend using:

```bash
http://localhost:8080
```

Example:

```javascript
fetch("http://localhost:8080/api/url")
```

---

# Common Errors

## Port Already in Use

### Frontend Port Change

```bash
npm run dev -- --port 3000
```

### Backend Port Change

Open:

```bash
src/main/resources/application.properties
```

Add:

```properties
server.port=8081
```

---

# Stop Server

Press:

```bash
CTRL + C
```

---

# Screenshots

## Frontend Running

```bash
VITE v8.x ready
Local: http://localhost:5173/
```

## Backend Running

```bash
Started UrlshortenerApplication
Tomcat started on port(s): 8080
```

---

# Author

Anshul

```
```
