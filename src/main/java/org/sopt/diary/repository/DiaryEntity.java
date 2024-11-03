package org.sopt.diary.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;
import org.sopt.diary.constant.DiaryScope;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "diary_jpa_entity")
public class DiaryEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="diary_id")
    private Long id;

    @Column(name="diary_title", nullable=false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="createdAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime dateTime;

    @Column
    @Enumerated(EnumType.STRING)
    private DiaryScope scope;

    public DiaryEntity(){
    }

    public DiaryEntity(UserEntity user, String title, String content, DiaryScope scope){
        this.user = user;
        this.title= title;
        this.content=content;
        this.scope=scope;
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

    public DiaryScope getScope() { return this.scope; }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content=content;
    }

    public void setScope(DiaryScope scope) { this.scope = scope; }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
