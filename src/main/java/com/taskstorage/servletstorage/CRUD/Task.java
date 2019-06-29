package com.taskstorage.servletstorage.CRUD;

import java.io.Serializable;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String description;
    private String content;

    public Task() {
    }

    public Task(String description, String content) {
        this.description = description;
        this.content = content;
    }

    public Task(Long id, String description, String content) {
        this.id = id;
        this.description = description;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
