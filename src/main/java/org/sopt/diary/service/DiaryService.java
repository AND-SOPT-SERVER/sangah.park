package org.sopt.diary.service;

import java.util.List;

import org.sopt.diary.api.dto.req.DiaryPostRequest;
import org.sopt.diary.exception.FailureStatus;
import org.sopt.diary.exception.GlobalException;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {
    private static final int MAX_LIST_OF_DIARY = 10;

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    public DiaryService (DiaryRepository diaryRepository, UserRepository userRepository){
        this.diaryRepository =  diaryRepository;
        this.userRepository = userRepository;
    }

    public void createDiary(Long userId, DiaryPostRequest diaryPostRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(FailureStatus.USER_NOT_FOUND));
        DiaryEntity diary = new DiaryEntity();
        diary.setUser(user);
        diary.setTitle(diaryPostRequest.title());
        diary.setContent(diaryPostRequest.content());
        diary.setScope(diaryPostRequest.scope());

        diaryRepository.save(diary);
    }

    public List<DiaryEntity> getDiaryTen(Long userId){
        List<DiaryEntity> diaries = diaryRepository.findByUserId(userId);
        return diaries.stream()
                .sorted((diaryEntity1, diaryEntity2)-> diaryEntity2.getDateTime().compareTo(diaryEntity1.getDateTime()))
                .limit(MAX_LIST_OF_DIARY)
                .toList();
    }

    public DiaryType getList(Long userId, Long id) {
        DiaryEntity diaryEntity = findDiary(userId, id);
        return new DiaryType(diaryEntity.getId(), diaryEntity.getDateTime(), diaryEntity.getTitle(), diaryEntity.getContent(), diaryEntity.getScope());
    }

    public void deleteDiary(Long userId, Long id){
        DiaryEntity diaryEntity = findDiary(userId, id);
        diaryRepository.delete(diaryEntity);
    }

    public void patchDiary(Long userId, Long id, String content){
        DiaryEntity diaryEntity = findDiary(userId, id);
        diaryEntity.setContent(content);
        diaryRepository.save(diaryEntity);
    }

    private DiaryEntity findDiary(Long userId, Long id) {
        List<DiaryEntity> diaries = diaryRepository.findByUserId(userId);
        return diaries.stream()
                .filter(diary -> diary.getId() == id)
                .findFirst()
                .orElseThrow(() -> new GlobalException(FailureStatus.DIARY_NOT_FOUND));
    }
}
