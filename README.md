# 볼링 게임 점수판
## 질문 삭제하기 요구사항
- [x] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- [x] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- [x] 답변이 없는 경우 삭제가 가능하다.
- [x] 질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능하다.
- [x] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- [x] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- [x] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
