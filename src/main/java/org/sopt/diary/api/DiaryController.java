package org.sopt.diary.api;

import org.sopt.diary.dto.PatchedDiaryContent;

import org.sopt.diary.service.DiaryDetail;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
                new DiaryListResponse(diaryService.getDiaryTen().stream()
                        .map(diary -> new DiaryResponse(diary.getTitle(), diary.getContent())).toList())
        );
    }

    @GetMapping("/diaries/{id}")
    ResponseEntity<DiaryDetailResponse> getDetailList(@PathVariable final Long id) {
        DiaryDetail diaryDetail = (DiaryDetail) diaryService.getList(id);
        DiaryDetailResponse diaryResponse = new DiaryDetailResponse(diaryDetail.getId(), diaryDetail.getDateTime(), diaryDetail.getTitle(), diaryDetail.getContent());
        return ResponseEntity.ok(diaryResponse);
    }

    @DeleteMapping("/diaries/{id}")
    public ResponseEntity<Void>  delete(@PathVariable final Long id){
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/diaries/{id}")
    public ResponseEntity<Void> patch(@PathVariable final Long id, @RequestBody PatchedDiaryContent content) {
        if (content.getContent().length() > 30 ){
            throw new BadRequestException("최대 30자까지 작성 가능합니다.");
        }
        diaryService.patchDiary(id, content.getContent());
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }
}
