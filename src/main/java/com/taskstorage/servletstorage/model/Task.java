package com.taskstorage.servletstorage.model;

import java.io.Serializable;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String description;
    private String content;

    private Long author;
    public Task() {
    }

    public Task(String description, String content, Long user) {
        this.description = description;
        this.content = content;
        this.author = user;
    }

    public Task(Long id, String description, String content, Long user) {
        this.id = id;
        this.description = description;
        this.content = content;
        this.author = user;
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

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }
}
