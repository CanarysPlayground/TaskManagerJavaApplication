# Requirements Document
## Task Manager Application

**Version:** 1.0.0
**Date:** April 9, 2026
**Status:** Current Implementation

---

## 1. Project Overview

The Task Manager Application is a web-based system that allows users to view and manage a list of tasks through a browser UI and a REST API. It is built using Java and Spring Boot and is intended as a foundational application that can be extended with additional features.

---

## 2. Stakeholders

| Role | Responsibility |
|---|---|
| Developer | Builds and maintains the application |
| End User | Views and interacts with the task list via browser |
| API Consumer | Integrates with the REST API to read or create tasks |

---

## 3. Functional Requirements

### 3.1 Task Display (UI)
| ID | Requirement | Status |
|---|---|---|
| FR-01 | The system shall display all tasks on a web page at `/tasks` | Implemented |
| FR-02 | Each task shall show a numeric ID and a title | Implemented |
| FR-03 | The task list shall be rendered using a card-based grid layout | Implemented |
| FR-04 | The UI shall show the total number of tasks in the header | Implemented |
| FR-05 | A search bar shall allow users to filter tasks by title in real time | Implemented |

### 3.2 Task Pre-loading
| ID | Requirement | Status |
|---|---|---|
| FR-06 | The system shall pre-load 100 tasks on application startup | Implemented |
| FR-07 | Each pre-loaded task shall have a unique auto-incremented ID starting from 1 | Implemented |

### 3.3 REST API
| ID | Requirement | Status |
|---|---|---|
| FR-08 | The system shall expose a `GET /api/tasks` endpoint returning all tasks as JSON | Implemented |
| FR-09 | The system shall expose a `POST /api/tasks` endpoint to add a new task | Implemented |
| FR-10 | A new task submitted via POST shall receive an auto-assigned ID | Implemented |
| FR-11 | The API shall accept and return data in `application/json` format | Implemented |

---

## 4. Non-Functional Requirements

| ID | Requirement | Details |
|---|---|---|
| NFR-01 | **Performance** | Application must start within 5 seconds on standard hardware |
| NFR-02 | **Availability** | Application must serve requests on port 8080 |
| NFR-03 | **Usability** | UI must be responsive and readable across desktop browsers |
| NFR-04 | **Maintainability** | Source code must follow standard Java package conventions |
| NFR-05 | **Portability** | Application must run on any OS with Java 17+ and Maven 3.6+ |
| NFR-06 | **Build** | Application must build successfully using `mvn clean package` |

---

## 5. Technical Requirements

| Component | Technology | Version |
|---|---|---|
| Language | Java | 17 or later (tested on JDK 25) |
| Framework | Spring Boot | 3.2.4 |
| Web Layer | Spring Web MVC (embedded Tomcat) | 10.1.x |
| Templating | Thymeleaf | 3.x |
| Build Tool | Apache Maven | 3.6+ |
| Packaging | Executable JAR | — |
| Port | HTTP | 8080 |

---

## 6. API Specification

### GET /api/tasks
- **Description:** Retrieve all tasks
- **Response:** `200 OK`
- **Response Body:**
```json
[
  { "id": 1, "title": "Set up project repository" },
  { "id": 2, "title": "Define project scope" }
]
```

### POST /api/tasks
- **Description:** Create a new task
- **Request Body:**
```json
{ "title": "My new task" }
```
- **Response:** `200 OK`
- **Response Body:**
```json
{ "id": 101, "title": "My new task" }
```

---

## 7. Data Model

### Task
| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Auto-assigned unique identifier |
| `title` | `String` | Text description of the task |

> **Note:** Data is stored in-memory only. All tasks are lost on application restart.

---

## 8. Constraints & Limitations

| ID | Constraint |
|---|---|
| C-01 | Tasks are stored in memory — no database persistence in current version |
| C-02 | No user authentication or authorization is implemented |
| C-03 | No task update (PUT) or delete (DELETE) endpoints exist |
| C-04 | No input validation on task title (empty titles accepted) |
| C-05 | Application runs on a single instance with no clustering support |

---

## 9. Future Enhancements (Out of Scope for v1.0)

| ID | Enhancement |
|---|---|
| FE-01 | Persist tasks in a relational database (e.g., H2, PostgreSQL) using Spring Data JPA |
| FE-02 | Add DELETE and PUT endpoints to update and remove tasks |
| FE-03 | Implement user authentication (login/logout) |
| FE-04 | Add task priorities, categories, and due dates |
| FE-05 | Add form in UI to create tasks without using API directly |
| FE-06 | Implement pagination for the task list |
| FE-07 | Add Swagger / OpenAPI documentation |
| FE-08 | Containerize the application with Docker |

---

## 10. How to Run

```powershell
# From the project root directory
powershell -ExecutionPolicy Bypass -File run.ps1
```

**Prerequisites:** Java 17+, Apache Maven 3.6+

**Access URLs:**
| URL | Description |
|---|---|
| `http://localhost:8080/tasks` | Browser UI — task list |
| `http://localhost:8080/api/tasks` | REST API — all tasks (JSON) |
