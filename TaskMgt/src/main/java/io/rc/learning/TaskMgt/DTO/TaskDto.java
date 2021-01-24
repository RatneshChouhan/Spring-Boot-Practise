package io.rc.learning.TaskMgt.DTO;

public class TaskDto {
    private String id;
    private String title;
    private String description;
    private String status;

    public TaskDto(String id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public TaskDto() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
