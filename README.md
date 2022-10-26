

## 볼링 게임 점수판

---

1. [ ] 사용자로부터 플레이어 이름을 입력받을 수 있다. 
   1. [ ] 플레이어 이름 길이는 제한 길이(3글자)를 초과해서는 안된다. 
2. [ ] 각 라운드별로 몇 개의 핀을 쓰러트렸는지 입력받을 수 있다.
   1. [ ] 마지막 라운드인 경우를 제외하고는 2번의 투구를 입력받는다. 스트라이크인 경우 (10) 한번만 입력받는다. 
   2. [ ] 마지막 라운드인 경우는 스트라이크와 무관하게 3번의 투구를 입력받는다. 
   3. [ ] 투구 값은 0 보다 작거나 , 10보다 클수는 없다.  
3. [ ] 각 라운드별로 게임 점수판을 출력할 수 있다. 이때 아래와 같이 표기한다.
   1. [ ] 첫번쨰 투구와 두번째 투구간 구분은 "|"" ,  스트라이크는 "X" , 스페어는 "/" , 거트는 "-" 

--- 
## 질문 삭제하기 리팩토링 미션 - 요구사항 정리

---
1. [x] 질문 데이터는 완전 삭제가 아닌 삭제 상태만 변경한다. (soft delete)
- 아래와 같은 조건을 충족 시 삭제가 가능하다.
2. [x] 로그인 사용자와 질문한 사용자가 같아야 한다.
3. [x] 답변이 없는 경우
4. [x] 답변이 있으나 , 답변을 단 사람이 질문자와 동일한 경우 

- 삭제 시에는 아래와 같은 동작을 수행해야 한다.

5. [x] 질문 삭제 시 , 답변 또한 삭제해야 한다. 답변 삭제도 마찬가지로 삭제 상태만 변경한다. 
6. [x] 삭제 이력은 삭제된 상태에서만 조회가 가능하다. 
