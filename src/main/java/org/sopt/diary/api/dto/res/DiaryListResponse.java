package org.sopt.diary.api.dto.res;

import java.util.List;

public class DiaryListResponse {
    private List<DiaryResponse> diaryList;

    public DiaryListResponse(List<DiaryResponse> diaryList) {
        this.diaryList = diaryList;
    }

   public List<DiaryResponse> getDiary(List<DiaryResponse> diaryList) {
        return this.diaryList = diaryList;
    }

    public List<DiaryResponse> getDiaryList() {
        return this.diaryList;
    }
}