package org.sopt.diary.api;

import jakarta.servlet.http.HttpServletRequest;
import org.sopt.diary.api.dto.req.DiaryPatchRequest;

import org.sopt.diary.service.DiaryType;
import org.sopt.diary.api.dto.req.DiaryPostRequest;
import org.sopt.diary.api.dto.res.DiaryDetailResponse;
import org.sopt.diary.api.dto.res.DiaryListResponse;
import org.sopt.diary.api.dto.res.DiaryResponse;
import org.sopt.diary.exception.FailureStatus;
import org.sopt.diary.exception.GlobalException;
import org.sopt.diary.service.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/diaries")
public class DiaryController {
    private static final int MAX_LENGTH_OF_DIARY = 30;
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    ResponseEntity<Object> post(@RequestHeader("UserId") final Long userId, @RequestBody DiaryPostRequest diaryPostRequest) {
        if (diaryPostRequest.content().length() > MAX_LENGTH_OF_DIARY ){
            throw new GlobalException(FailureStatus.INVALID_PARAMETER);
        }
        diaryService.createDiary(userId, diaryPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    ResponseEntity<DiaryListResponse> get(@RequestHeader("UserId") final Long userId) {
        return ResponseEntity.ok(
                new DiaryListResponse(diaryService.getDiaryTen(userId).stream()
                        .map(diary -> new DiaryResponse(diary.getTitle(), diary.getContent())).toList())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<DiaryDetailResponse> getDetailList(@RequestHeader("UserId") final Long userId, @PathVariable(name="id") final Long id) {
        DiaryType diaryType = (DiaryType) diaryService.getList(userId, id);
        DiaryDetailResponse diaryResponse = new DiaryDetailResponse(diaryType.getId(), diaryType.getDateTime(), diaryType.getTitle(), diaryType.getContent(), diaryType.getScope());
        return ResponseEntity.ok(diaryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  delete(@RequestHeader("UserId") final Long userId, @PathVariable(name="id") final Long id){
        diaryService.deleteDiary(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@RequestHeader("UserId") Long userId, @PathVariable(name="id") final Long id, @RequestBody DiaryPatchRequest diaryPatchRequest) {
        if (diaryPatchRequest.content().length() > MAX_LENGTH_OF_DIARY ){
            throw new GlobalException(FailureStatus.INVALID_PARAMETER);
       }
        diaryService.patchDiary(userId, id, diaryPatchRequest.content());
        return ResponseEntity.ok().build();
    }
}
