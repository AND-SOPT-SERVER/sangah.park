package org.sopt.diary.api;

import org.apache.coyote.BadRequestException;
import org.sopt.diary.service.Diary;

import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    void post(@RequestBody DiaryRequest diaryRequest) {
        if (diaryRequest.content().length() > 30 ){
            throw new BadRequestException("최대 30자까지 작성 가능합니다.");
        }
        diaryService.createDiary(diaryRequest);
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryListResponse> get() {
        return ResponseEntity.ok(
                new DiaryListResponse(diaryService.getList().stream()
                        .map(diary -> new DiaryResponse(diary.getId(), diary.getDateTime(), diary.getTitle(), diary.getContent())).toList())
        );
    }

    @DeleteMapping("/diaries/{id}")
    ResponseEntity delete(@PathVariable final Long id){
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }
}
