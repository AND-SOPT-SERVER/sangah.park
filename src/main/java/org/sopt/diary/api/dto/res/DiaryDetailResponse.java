package org.sopt.diary.api.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.sopt.diary.constant.DiaryScope;

import java.time.LocalDateTime;

public class DiaryDetailResponse {
    private final long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private final LocalDateTime dateTime;
    private final String title;
    private final String content;
    private final DiaryScope scope;

    public DiaryDetailResponse(long id, LocalDateTime dateTime, String title, String content, DiaryScope scope) {
        this.id = id;
        this.dateTime = dateTime;
        this.title = title;
        this.content = content;
        this.scope=scope;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public DiaryScope getScope() {
        return scope;
    }
}
