package org.sopt.diary.api;

import org.sopt.diary.api.dto.res.PatchedDiaryContent;

import org.sopt.diary.api.dto.res.DiaryDetail;
import org.sopt.diary.api.dto.req.DiaryRequest;
import org.sopt.diary.api.dto.res.DiaryDetailResponse;
import org.sopt.diary.api.dto.res.DiaryListResponse;
import org.sopt.diary.api.dto.res.DiaryResponse;
import org.sopt.diary.exception.FailureStatus;
import org.sopt.diary.exception.GlobalException;
import org.sopt.diary.service.DiaryService;
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
            throw new GlobalException(FailureStatus.INVALID_PARAMETER);
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
            throw new GlobalException(FailureStatus.INVALID_PARAMETER);
       }
        diaryService.patchDiary(id, content.getContent());
        return ResponseEntity.ok().build();
    }

/*    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BadRequestException extends RuntimeException {
        public BadRequestException(String message) {
            super(message);
        }
    }*/
}
