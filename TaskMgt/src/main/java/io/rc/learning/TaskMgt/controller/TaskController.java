package io.rc.learning.TaskMgt.controller;

import io.rc.learning.TaskMgt.model.Task;
import io.rc.learning.TaskMgt.model.TaskStatus;
import io.rc.learning.TaskMgt.DTO.TaskDto;
import io.rc.learning.TaskMgt.repository.TaskRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository){
        this.repository = repository;
    }

   @GetMapping("/tasks/{id}")
   public Task getTaskById(@PathVariable Long id){
      Optional<Task> task =  repository.findById(id);
     /* if(!task.isPresent())
          throw new RuntimeException("Invalid Id");*/
      return task.get() ;
   }

  //  @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    @GetMapping("/tasks")
    public List<TaskDto> getAllTasks(){
        /*List<TaskDto> taskDtoListList = Arrays.asList(
                new TaskDto("1","Task 1","Some Description" , "CREATED"),
                new TaskDto("2","Task 2","Another Description" , "CREATED")
        );*/
        List<TaskDto> taskDtoListList = new ArrayList<>();
        repository.findAll().forEach(task -> taskDtoListList.add(task.toDto()));
        return taskDtoListList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public String addTask(@RequestBody Task task){
        Task savedTask = repository.save(task);
        return "Task Created with ID " + task.getId();
    }

    @RequestMapping( method = RequestMethod.PUT, value = "/tasks/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto){
      Task existingTask = repository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Task not found"));
     if(!EnumUtils.isValidEnum(TaskStatus.class,taskDto.getStatus()))
          return new ResponseEntity<>("Available statuses are : CREATED,APPROVED,REJECTED,BLOCKED,DONE",HttpStatus.OK) ;

      existingTask.setTitle(taskDto.getTitle());
      existingTask.setDescription(taskDto.getDescription());
      existingTask.setStatus(TaskStatus.valueOf(taskDto.getStatus()));

      repository.save(existingTask);
      return new ResponseEntity<>("",HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{id}")
    public void deleteTask(@PathVariable Long id){
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Id"));
        repository.deleteById(id);

    }

    @GetMapping("/tasks/describe/{id}")
    public ResponseEntity<String> describeTask(@PathVariable Long id){
        Task existingTask = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " does not exist"));

        return new ResponseEntity<String>("Description of Task [" + existingTask.getId() + ":" + existingTask.toDto().getTitle() + "] is : " +
                                            existingTask.toDto().getDescription(), HttpStatus.OK);

    }

    @GetMapping("/tasks/describe")
    public List<String> describeAllTasks(){
       // List<Task> taskList = new ArrayList<>();
        List<String> conDescr = new ArrayList<>();
        repository.findAll().forEach(tasK -> {
            conDescr.add(
            "Description of Task [" + tasK.getId() + ":" + tasK.toDto().getTitle() + "] is : " +
                    tasK.toDto().getDescription());
        });

        return conDescr;
    }

}
