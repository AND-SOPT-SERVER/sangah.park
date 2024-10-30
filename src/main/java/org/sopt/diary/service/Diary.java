package org.sopt.diary.service;
import java.time.LocalDateTime;

public class Diary {

    private final long id;
    private final LocalDateTime dateTime;
    private final String title;
    private final String content;

    public Diary(long id, LocalDateTime dateTime, String title, String content){
        this.id = id;
        this.dateTime= dateTime;
        this.title = title;
        this.content = content;
    }

    public long getId(){
        return id;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public String getTitle(){
        return  title;
    }

    public String getContent(){
        return  content;
    }
}

