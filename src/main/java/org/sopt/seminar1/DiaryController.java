package org.sopt.seminar1;

import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    final List<Diary> getList() {
        return diaryService.getDiaryList();
    }

    final void post(final String body) {
            if(body.length() > 30){
                System.out.println("일기는 30자 이내로 작성해 주세요.");
                throw new IllegalArgumentException();
            }
        diaryService.writeDiary(body);

    }

    final void delete(final String id) {
        diaryService.deleteDiary(Long.parseLong(id));

    }

    final void patch(final String id, final String body) {
        diaryService.patchDiary(Long.parseLong(id), body);
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}