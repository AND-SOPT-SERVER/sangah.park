package org.sopt.diary.service;

import java.util.List;

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

    public List<Diary> getDiaryTen(){
        return diaryRepository.findAll().stream()
                .sorted((diary1, diary2)->diary2.getDateTime().compareTo(diary1.getDateTime()))
                .limit(10)
                .map(diaryEntity -> new Diary(diaryEntity.getTitle(), diaryEntity.getContent())).toList();
    }

    public DiaryDetail getList(long id) {
        DiaryEntity diary = diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + "에 해당하는 다이어리를 찾을 수 없습니다."));
        return new DiaryDetail(diary.getId(), diary.getDateTime(), diary.getTitle(), diary.getContent());
    }


    public void deleteDiary(long id){
        DiaryEntity diary=diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + "에 해당하는 다이어리를 찾을 수 없습니다."));
        diaryRepository.delete(diary);
    }

    public void patchDiary(long id, String content){
        DiaryEntity diary=diaryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + "에 해당하는 다이어리를 찾을 수 없습니다."));
                diary.setContent(content);
        diaryRepository.save(diary);
    }
}
