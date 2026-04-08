
package com.example.taskmanager;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;

@Controller
public class TaskController {
 private List<Task> tasks=new ArrayList<>();

 @GetMapping("/api/tasks")
 @ResponseBody
 public List<Task> getTasks(){return tasks;}

 @PostMapping("/api/tasks")
 @ResponseBody
 public Task addTask(@RequestBody Task task){
  task.setId((long)(tasks.size()+1));
  tasks.add(task);
  return task;
 }

 @GetMapping("/")
 public String ui(Model model){
  model.addAttribute("tasks",tasks);
  return "tasks";
 }
}
