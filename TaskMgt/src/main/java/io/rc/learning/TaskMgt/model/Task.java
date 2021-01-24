package io.rc.learning.TaskMgt.model;

import io.rc.learning.TaskMgt.DTO.TaskDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private TaskStatus status = TaskStatus.CREATED;

    public Task() {
    }

    public Task(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskDto toDto(){
        return new TaskDto(String.valueOf(id), title, description, status.name());
    }

    public Long getId() {
        return id;
    }
}
