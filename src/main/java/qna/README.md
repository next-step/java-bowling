## 요구사항 
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와답변글의모든답변자같은경우삭제가가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- 질문자와답변자가다른경우답변을삭제할수없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 기능구현 목록
- Question : Answer  -> 1 : N
- Question : User -> 1 : N
- Answer : User -> N : 1
- DeleteHistory : Question -> 1 : 1 
- DeleteHistory : Answer -> 1 : 1

- [X] 일급 컬렉션 추가 Answers , DeleteHistories
- [ ] Question을 삭제하면 데이터 상태가 삭제상태인 true로 변한다.
- [ ] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다. -> Question로 이동
- [ ] 답변이 없는 경우 삭제가 가능하다. -> Question로 이동 
- [ ] 질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능하다 -> Answers로 이동 
- [ ] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다. -> Answers로 이동
- [ ] 질문자와 답변자가 다른 경우 답변을 삭제 할 수 없다. -> Answers으로 이동
- [ ] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다. -> DeleteHistories로 이동 
