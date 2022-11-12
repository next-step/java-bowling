# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## Goal
- ATDD기반으로, 레거시 앱에 테스트 코드 추가해 리팩토링하는 경험 쌓기
- 좀 더 상세하게 말하자면,
	- 서비스 레이어에 단위 테스트 추가후, 비지니스 로직을 도메인 객체로 이동하는 리팩토링 경험 쌓기.

### HOW?
- 핵심 비지니스 로직을 도메인 객체가 담당하도록 구현.
- 테스트 하기 쉬운 부분을 분리하여, 단위테스트 구현 및 지속적 리팩토링.
- 서비스 레이어에 단위테스트 추가후, 비지니스 로직을 도메인 객체로 이동하는 리팩토링.
- 인수테스트 추가후 리팩토링.

## 🚀 1단계 - 질문 삭제하기 기능 리팩토링
### 기능 요구사항
- 질문 삭제 OK 케이스 :
	- 답변이 없는 경우
	- 로그인 사용자와 질문한 사람이 같은 경우
	- 질문자와 답변글의 모든답변자 같은경우
- 질문 삭제 NG 케이스 :
	- 로그인 사용자와 질문한 사람이 다른 경우.
	- 질문자와 답변글의 모든 답변자 중, 한 사람이라도 다른 유저가 쓴 답변이 존재하는 경우
- 질문 삭제시 Behavior :
	- 질문 Soft-delete (데이터의 상태를 삭제 상태(deleted - boolean type)로 변경)
	- 답변 또한 soft delete
	- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory insert
### 프로그래밍 요구사항
- qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.
```java
public class QnAService {
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        deleteHistoryService.saveAll(deleteHistories);
    }
}
```
### 힌트
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
	- Question의 List를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
	- 인스턴스 변수의 수를 줄이기 위해 도전한다.
- 테스트하기 쉬운 부분과 테스트하기 어려운 부분을 분리해 테스트 가능한 부분만 단위테스트한다.

### TODO
- [ ] QnA서비스 중 테스트하기 어려운 부분 Question 로직으로 분리
- [ ] Question delete로직 구현
- [ ] Answer delete로직 구현
- [ ] 1급 컬렉션 작성
- [ ] QnA서비스 영향 없는지 확인
- [ ] Question Test 작성
- [ ] Answer Test 작성

## QnA

##### Q : Stream findAny vs findFirst

##### Fixed : How to Thorw exception if predicate test passed
- Option A) 람다에서 CheckedException은 던질수 없으므로,  던지고 싶은경우는 람다밖에서 던진다.
```java
final boolean isOtherUserAnswerIncluded = answers.stream()
	.anyMatch(answer -> !answer.isOwner(loginUser));
if (isOtherUserAnswerIncluded) {
	throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
}
```

- Option B) 런타임으로 전환해서 람다에서 던지기
```java
values.stream()
        .filter(s -> s.equals("two"))
        .findAny()
        .ifPresentThrow(() -> new RuntimeException("not found"));
```