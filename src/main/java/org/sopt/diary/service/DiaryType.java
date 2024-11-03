package org.sopt.diary.service;

/*import org.sopt.diary.constant.Scope;*/
import org.sopt.diary.constant.DiaryScope;

import java.time.LocalDateTime;

public class DiaryType {

    private final long id;
    private final LocalDateTime dateTime;
    private final String title;
    private final String content;
 /*   private final Scope scope;
*/
    public DiaryType(long id, LocalDateTime dateTime, String title, String content,/*, Scope scope*/DiaryScope scope){
        this.id = id;
        this.dateTime= dateTime;
        this.title = title;
        this.content = content;
/*        this.scope = scope;*/
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

