package com.notetaking.noteservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;

    private String title;
    private String content;

    private String username;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Note() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Note(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

}
