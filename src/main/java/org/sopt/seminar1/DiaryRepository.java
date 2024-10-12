package org.sopt.seminar1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DiaryRepository {
    private final Map<Long, String> storage = new ConcurrentHashMap<>();
    private final Map<Long, String> deletedStorage = new ConcurrentHashMap<>();
    private final AtomicLong numbering = new AtomicLong();
    private final AtomicLong patchCount = new AtomicLong();
    private final LocalDate initialDate = LocalDate.now();
    private LocalDate firstPatchDate = initialDate;

    void save(final Diary diary){
        final long id = numbering.addAndGet(1);

        storage.put(id, diary.getBody());
    }

    List<Diary> findAll(){
        final List<Diary> diaryList = new ArrayList<>();

        for(long index = 1; index <= numbering.longValue() ; index++){
            final String body = storage.get(index);
            if (body != null){
                diaryList.add(new Diary(index, body));
            }
        }
        return diaryList;
    }

    void delete(final Long id){
        final String body = storage.get(id);
        deletedStorage.put(id, body);
        storage.remove(id);
    }


  void patch(final Long id,final String body){
      final LocalDate currentPatchDate =  LocalDate.now();
      if (!firstPatchDate.isEqual(currentPatchDate)) {
          patchCount.set(0);
          firstPatchDate = LocalDate.now() ;
      }
          if (patchCount.get() < 2){
            if (patchCount.get() == 0){
                  firstPatchDate=LocalDate.now();
              }
              storage.put(id, body);
              patchCount.addAndGet(1);
              System.out.println("일일 일기 수정 잔여 횟수는 "+ (2-patchCount.get())+"회입니다!");
          } else{
              System.out.println("일기 수정은 하루에 2번까지 가능합니다");
          }
      }

    void restore(final Long id){
        final String body = deletedStorage.get(id);
        storage.put(id, body);
    }
}