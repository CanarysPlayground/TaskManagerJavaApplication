
package com.example.taskmanager;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.annotation.PostConstruct;
import java.util.*;

@Controller
public class TaskController {
 private List<Task> tasks = new ArrayList<>();

 @PostConstruct
 public void initTasks() {
  String[] taskNames = {
   "Set up project repository", "Define project scope", "Create wireframes",
   "Design database schema", "Configure CI/CD pipeline", "Write unit tests",
   "Implement user authentication", "Build REST API endpoints", "Integrate front-end with API",
   "Set up logging framework", "Configure email notifications", "Implement search feature",
   "Add pagination to task list", "Create admin dashboard", "Write API documentation",
   "Set up role-based access control", "Implement file upload", "Add task priority levels",
   "Create task categories", "Implement due date reminders", "Add task comments feature",
   "Build activity feed", "Implement task assignments", "Add task status tracking",
   "Create user profile page", "Implement password reset", "Add two-factor authentication",
   "Build notification center", "Implement dark mode toggle", "Add keyboard shortcuts",
   "Create onboarding tutorial", "Implement drag-and-drop task ordering", "Add task labels",
   "Build reporting dashboard", "Implement data export to CSV", "Add import from CSV",
   "Create recurring tasks feature", "Implement task dependencies", "Add time tracking",
   "Build Gantt chart view", "Implement calendar view", "Add Kanban board view",
   "Create team collaboration features", "Implement real-time updates", "Add WebSocket support",
   "Build mobile-responsive layout", "Implement offline mode", "Add PWA support",
   "Create API rate limiting", "Implement request caching", "Add database indexing",
   "Optimize query performance", "Implement database migrations", "Set up Redis caching",
   "Configure load balancer", "Implement horizontal scaling", "Add health check endpoint",
   "Set up monitoring alerts", "Create backup strategy", "Implement disaster recovery plan",
   "Conduct security audit", "Fix SQL injection vulnerabilities", "Implement CSRF protection",
   "Add XSS prevention headers", "Set up HTTPS certificate", "Implement secrets management",
   "Write integration tests", "Set up end-to-end testing", "Perform load testing",
   "Fix reported bug #101", "Fix reported bug #102", "Fix reported bug #103",
   "Fix reported bug #104", "Fix reported bug #105", "Review pull requests",
   "Refactor authentication module", "Improve error handling", "Update dependencies",
   "Upgrade Spring Boot version", "Migrate to Java 21 features", "Remove deprecated APIs",
   "Improve code documentation", "Add OpenAPI/Swagger specs", "Create developer guide",
   "Write deployment runbook", "Update README file", "Create changelog",
   "Plan Q3 sprint backlog", "Conduct retrospective meeting", "Update project roadmap",
   "Prepare release notes", "Tag release v1.0.0", "Deploy to staging environment",
   "Run smoke tests on staging", "Get stakeholder sign-off", "Deploy to production",
   "Monitor production deployment"
  };
  for (int i = 0; i < taskNames.length; i++) {
   tasks.add(new Task((long) (i + 1), taskNames[i]));
  }
 }

 @GetMapping("/api/tasks")
 @ResponseBody
 public List<Task> getTasks() { return tasks; }

 @PostMapping("/api/tasks")
 @ResponseBody
 public Task addTask(@RequestBody Task task) {
  task.setId((long) (tasks.size() + 1));
  tasks.add(task);
  return task;
 }

 @GetMapping("/tasks")
 public String ui(Model model) {
  model.addAttribute("tasks", tasks);
  return "tasks";
 }
}
