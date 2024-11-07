package org.sopt.diary.api.dto.req;

import org.sopt.diary.constant.DiaryScope;

public record DiaryPostRequest(String title, String content, DiaryScope scope) {
}
