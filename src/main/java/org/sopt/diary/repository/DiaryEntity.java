package org.sopt.diary.repository;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class DiaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    @CreationTimestamp
    public LocalDateTime dateTime;

    @Column
    public String title;

    @Column(length=30)
    public String content;

    public DiaryEntity(){
    }

    public DiaryEntity(String title, String content){
        this.title= title;
        this.content=content;
    }

    public long getId(){
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
