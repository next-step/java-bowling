##질문 삭제하기 기능 리팩토링

### 리팩토링 시나리오
1. QnAService에서 테스트 가능한 코드 분리
2. domain에서 단위테스트 작성 - 기능별 추가 분리(메서드, 클래스) 

### 힌트
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
    + Question의 List를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    + 인스턴스 변수의 수를 줄이기 위해 도전한다.


Question 
title, contents, writer, List<Answer> answers, deleted

Answer
writer, Question, contents, deleted

DeleteHistory
id, ContentType, contentId, deletedBy, createDate 
