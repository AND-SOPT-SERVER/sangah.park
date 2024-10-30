package org.sopt.diary.service;

import java.util.List;

import org.sopt.diary.api.dto.req.DiaryPostRequest;
import org.sopt.diary.exception.FailureStatus;
import org.sopt.diary.exception.GlobalException;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class DiaryService {
    private static final int MAX_LIST_OF_DIARY = 10;

    private final DiaryRepository diaryRepository;

    public DiaryService (DiaryRepository diaryRepository){
        this.diaryRepository =  diaryRepository;
    }

    public void createDiary(@RequestBody DiaryPostRequest diaryPostRequest) {
        diaryRepository.save(new DiaryEntity(diaryPostRequest.title(), diaryPostRequest.content()));
    }

    public List<DiaryEntity> getDiaryTen(){
        List<DiaryEntity> diaries = diaryRepository.findAll();
        return diaries.stream()
                .sorted((diary1, diary2)->diary2.getDateTime().compareTo(diary1.getDateTime()))
                .limit(MAX_LIST_OF_DIARY)
                .toList();
    }

    public Diary getList(long id) {
        DiaryEntity diary = diaryRepository.findById(id)
                .orElseThrow(() -> new GlobalException(FailureStatus.DIARY_NOT_FOUND));
        return new Diary(diary.getId(), diary.getDateTime(), diary.getTitle(), diary.getContent());
    }


    public void deleteDiary(long id){
        DiaryEntity diary=diaryRepository.findById(id)
                .orElseThrow(() -> new GlobalException(FailureStatus.DIARY_NOT_FOUND));
        diaryRepository.delete(diary);
    }

    public void patchDiary(long id, String content){
        DiaryEntity diary=diaryRepository.findById(id)
                .orElseThrow(() -> new GlobalException(FailureStatus.DIARY_NOT_FOUND));
        diary.setContent(content);
        diaryRepository.save(diary);
    }
}
