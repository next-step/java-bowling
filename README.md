

## 볼링 게임 점수판

--- 
## 질문 삭제하기 리팩토링 미션 - 요구사항 정리

---
1. [ ] 질문 데이터는 완전 삭제가 아닌 삭제 상태만 변경한다. (soft delete)
- 아래와 같은 조건을 충족 시 삭제가 가능하다.
2. [ ] 로그인 사용자와 질문한 사용자가 같아야 한다.
3. [ ] 답변이 없는 경우
4. [ ] 답변이 있으나 , 답변을 단 사람이 질문자와 동일한 경우 

- 삭제 시에는 아래와 같은 동작을 수행해야 한다.

5. [ ] 질문 삭제 시 , 답변 또한 삭제해야 한다. 답변 삭제도 마찬가지로 삭제 상태만 변경한다. 
6. [ ] 삭제 이력은 DeleteHistory를 활용해 남긴다. 

