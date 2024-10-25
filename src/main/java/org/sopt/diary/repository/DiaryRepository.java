package org.sopt.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {
    //findAll, save 등의 행위가 정의되어 있음
    //실제 구현체들은 jpa가 알아서 (springboot가 해주는 작업)
}
