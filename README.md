# Task Manager Java Application

A Task Manager web application built with **Spring Boot 3**, **Thymeleaf**, and **Maven**. It comes pre-loaded with 100 tasks, exposes a styled browser UI, and provides a REST API — all backed by in-memory storage.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 25 (Eclipse Temurin) |
| Framework | Spring Boot 3.2.4 |
| Web MVC | Spring Web (embedded Tomcat on port 8080) |
| Templating | Thymeleaf |
| Build Tool | Apache Maven 3.9.9 |
| Packaging | Executable JAR |

---

## Project Structure

```
TaskManagerJavaApplication/
├── .gitignore                                       # Excludes target/, IDE files
├── pom.xml                                          # Maven build descriptor
├── run.ps1                                          # One-click Windows run script
└── src/
    └── main/
        ├── java/com/example/taskmanager/
        │   ├── TaskManagerApplication.java          # Spring Boot entry point
        │   ├── Task.java                            # Domain model (POJO)
        │   └── TaskController.java                  # MVC + REST controller
        └── resources/
            └── templates/
                └── tasks.html                       # Thymeleaf UI view
```

---

## Code Architecture

### 1. Entry Point — `TaskManagerApplication.java`
Bootstraps the Spring Boot application using `@SpringBootApplication`, enabling auto-configuration, component scanning, and an embedded Tomcat server.

### 2. Domain Model — `Task.java`
A plain Java object (POJO) with two fields:
- `id` — auto-assigned `Long` identifier
- `title` — text description of the task

### 3. Controller — `TaskController.java`
A single `@Controller` class serving both the HTML UI and REST API.

- Uses `@PostConstruct` to pre-load **100 tasks** into memory at startup.
- Tasks are stored in an in-memory `ArrayList` — no database required.

| Method | URL | Type | Description |
|---|---|---|---|
| `GET` | `/tasks` | HTML (MVC) | Renders the full task list UI via Thymeleaf |
| `GET` | `/api/tasks` | REST JSON | Returns all tasks as a JSON array |
| `POST` | `/api/tasks` | REST JSON | Adds a new task, auto-assigns ID |

### 4. View — `tasks.html`
A Thymeleaf template with:
- Header showing total task count
- Live search bar (JavaScript filter)
- Responsive card grid — each card shows the task ID badge and title

---

## Live URLs

| Purpose | URL |
|---|---|
| Task list UI | http://localhost:8080/tasks |
| REST API — all tasks | http://localhost:8080/api/tasks |

---

## How to Run

### Prerequisites
- Java 17+ (tested with Eclipse Temurin 25)
- Apache Maven 3.6+

### Option 1 — One-click script (Windows)
```powershell
powershell -ExecutionPolicy Bypass -File run.ps1
```
The script automatically sets Java/Maven paths and frees port 8080 if occupied.

### Option 2 — Manual
```powershell
# Set paths (only needed if not permanently set)
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-25.0.2.10-hotspot"
$env:Path = "$env:Path;$env:JAVA_HOME\bin;$env:USERPROFILE\apache-maven\apache-maven-3.9.9\bin"

# Navigate to project and run
cd TaskManagerJavaApplication
mvn spring-boot:run
```

### Option 3 — Clone and run
```bash
git clone https://github.com/CanarysPlayground/TaskManagerJavaApplication.git
cd TaskManagerJavaApplication
mvn spring-boot:run
```

Then open: **http://localhost:8080/tasks**

---

## API Usage

### Get all tasks
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks"
```

### Add a new task
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/tasks" -Method POST -ContentType "application/json" -Body '{"title": "My new task"}'
```

**Response:**
```json
{
  "id": 101,
  "title": "My new task"
}
```

---

## Notes

- Tasks are stored **in memory only** — they reset when the app restarts.
- 100 tasks are pre-loaded on every startup via `@PostConstruct`.
- No database or authentication is configured; this is a demo project.
