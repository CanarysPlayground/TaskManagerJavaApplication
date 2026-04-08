# Task Manager Java Application

A simple Task Manager web application built with **Spring Boot 3**, **Thymeleaf**, and **Maven**. It exposes a browser-based UI as well as a REST API to create and view tasks stored in memory.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java (JDK 17+) |
| Framework | Spring Boot 3.2.4 |
| Web MVC | Spring Web (embedded Tomcat) |
| Templating | Thymeleaf |
| Build Tool | Apache Maven |
| Packaging | Executable JAR |

---

## Project Structure

```
TaskManagerJavaApplication/
├── pom.xml                                          # Maven build descriptor
└── src/
    └── main/
        ├── java/com/example/taskmanager/
        │   ├── TaskManagerApplication.java          # Spring Boot entry point
        │   ├── Task.java                            # Domain model (POJO)
        │   └── TaskController.java                  # MVC + REST controller
        └── resources/
            └── templates/
                └── tasks.html                       # Thymeleaf HTML view
```

---

## Code Architecture

### 1. Entry Point — `TaskManagerApplication.java`
Bootstraps the Spring Boot application using `@SpringBootApplication`, which enables auto-configuration, component scanning, and an embedded Tomcat server.

### 2. Domain Model — `Task.java`
A plain Java object (POJO) representing a task with two fields:
- `id` — auto-assigned `Long` identifier
- `title` — text description of the task

No persistence layer is used; tasks are stored in an in-memory `ArrayList` for the lifetime of the application.

### 3. Controller — `TaskController.java`
A single `@Controller` class that serves both the HTML UI and the REST API:

| Method | Path | Type | Description |
|---|---|---|---|
| `GET` | `/` | HTML (MVC) | Renders the task list via Thymeleaf |
| `GET` | `/api/tasks` | REST (`application/json`) | Returns all tasks as JSON |
| `POST` | `/api/tasks` | REST (`application/json`) | Accepts a JSON body, auto-assigns an ID, and saves the task |

### 4. View — `tasks.html`
A Thymeleaf template that iterates over the `tasks` model attribute and renders each task title as a list item.

---

## How to Run

### Prerequisites
- Java 17 or later
- Apache Maven 3.6+

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/CanarysPlayground/TaskManagerJavaApplication.git
   cd TaskManagerJavaApplication
   ```

2. **Build the project**
   ```bash
   mvn clean package
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   Or run the packaged JAR:
   ```bash
   java -jar target/taskmanager-0.0.1-SNAPSHOT.jar
   ```

4. **Open in browser**
   ```
   http://localhost:8080/
   ```

---

## API Usage

### Get all tasks
```bash
curl http://localhost:8080/api/tasks
```

### Add a new task
```bash
curl -X POST http://localhost:8080/api/tasks \
     -H "Content-Type: application/json" \
     -d '{"title": "Buy groceries"}'
```

**Response:**
```json
{
  "id": 1,
  "title": "Buy groceries"
}
```

---

## Notes

- Tasks are stored **in memory only** — they are lost when the application restarts.
- No database or authentication is configured; this is a demo project.
- To persist tasks across restarts, integrate Spring Data JPA with an embedded H2 or external database.
