package org.sopt.diary.service;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.diary.api.DiaryRequest;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public DiaryService (DiaryRepository diaryRepository){
        this.diaryRepository =  diaryRepository;
    }

    public void createDiary(@RequestBody DiaryRequest diaryRequest) {
        diaryRepository.save(new DiaryEntity(diaryRequest.title(), diaryRequest.content()));
    }

    public List<Diary> getList(){
        return diaryRepository.findAll().stream()
                .map(diaryEntity -> new Diary(diaryEntity.getId(), diaryEntity.getDateTime() ,diaryEntity.getTitle(), diaryEntity.getContent())).toList();
    }

    public void deleteDiary(long id){
        DiaryEntity diary=diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + "에 해당하는 다이어리를 찾을 수 없습니다."));
        diaryRepository.delete(diary);
    }
}
