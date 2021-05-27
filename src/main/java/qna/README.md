# 볼링 미션 1단계 - 질문 삭제하기 기능 리팩토링

## 질문 삭제하기 요구사항
+ 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
+ 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
+ 답변이 없는 경우 삭제가 가능하다.
+ 질문자와답변글의모든답변자같은경우삭제가가능하다.
+ 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
한다.
+ 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
+ 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 프로그래밍 요구사항
+ qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
+ 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.

## 추가된 도메인 설계

### Answers
+ Answer의 일급콜렉션으로 입력받은 리스트에 작성자가 동일한지 검증하는 객체
+ `checkOwner(User loginUser)` : Answers를 순회하면서 작성자가 모두 동일한지 검증한다.  
+ `of()` : `List<Answer>` 를 반환하는 메서드

### DeleteHistories 
+ DeleteHistory의 일급콜렉션으로 각각의 타입에 맞는 DeleteHistory를 생성하는 객체
+ `createAnswerDeleteHistory(Answer answer)` : Answer에 대한 DeleteHistory를 생성하는 메서드
+ `createQuestionDeleteHistory(Question question)` : Question에 대한 DeleteHistory를 생성하는 메서드 
+ `of()` : `List<DeleteHistory>` 를 반환하는 메서드 

### DeleteHistoryManager
+ Answer와 Question을 받아서 실질적인 삭제처리를 하는 객체
+ `deleteQuestion(boolean isDeleted, DeleteHistories deleteHistories)` : Question에 대한 삭제 처리를 하는 메서드 
+ `deleteAnswer(boolean isDeleted, DeleteHistories deleteHistories)` : Answer에 대한 삭제드 처리를 하는 메서드


