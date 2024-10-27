package org.sopt.diary.api;


public class DiaryResponse {

    private final String title;
    private String content;

    public DiaryResponse(String title, String content){
        this.title=title;
        this.content=content;
    }

    public String getTitle(){
        return title;
    }

    public String getContent() { return content; }

}


