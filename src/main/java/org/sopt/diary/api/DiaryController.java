package org.sopt.diary.api;

import org.sopt.diary.api.dto.req.DiaryPatchRequest;

import org.sopt.diary.service.Diary;
import org.sopt.diary.api.dto.req.DiaryPostRequest;
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
    private static final int MAX_LENGTH_OF_DIARY = 30;
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/diaries")
    void post(@RequestBody DiaryPostRequest diaryPostRequest) {
        if (diaryPostRequest.content().length() > MAX_LENGTH_OF_DIARY ){
            throw new GlobalException(FailureStatus.INVALID_PARAMETER);
        }
        diaryService.createDiary(diaryPostRequest);
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
        Diary diary = (Diary) diaryService.getList(id);
        DiaryDetailResponse diaryResponse = new DiaryDetailResponse(diary.getId(), diary.getDateTime(), diary.getTitle(), diary.getContent());
        return ResponseEntity.ok(diaryResponse);
    }

    @DeleteMapping("/diaries/{id}")
    public ResponseEntity<Void>  delete(@PathVariable final Long id){
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/diaries/{id}")
    public ResponseEntity<Void> patch(@PathVariable final Long id, @RequestBody DiaryPatchRequest diaryPatchRequest) {
        if (diaryPatchRequest.content().length() > MAX_LENGTH_OF_DIARY ){
            throw new GlobalException(FailureStatus.INVALID_PARAMETER);
       }
        diaryService.patchDiary(id, diaryPatchRequest.content());
        return ResponseEntity.ok().build();
    }
}
