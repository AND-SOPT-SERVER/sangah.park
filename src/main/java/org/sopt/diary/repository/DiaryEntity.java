package org.sopt.diary.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
    @CreationTimestamp
    public LocalDateTime dateTime;

    @Column(name="title")
    public String title;

    @Column(name="content")
    public String content;

    public DiaryEntity(){
    }

    public DiaryEntity(String title, String content){
        this.title= title;
        this.content=content;
    }

    public long getId() {
        return this.id;
    }

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

    public String getTitle(){
        return this.title;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content) {
        this.content=content;
    }
}
