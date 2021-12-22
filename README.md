# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# 1단계 - 질문 삭제하기 기능 리팩토링
## 질문 삭제하기 요구사항 분리
### 삭제 방법
- 질문 데이터를 완전히 삭제하는 것이 아닌 데이터의 상태를 삭테 상태(deleted - boolean type)로 변경한다.
- 질문 삭제 시 답변도 삭제해야 하며, 답변의 삭제 상태도 변경해야 한다.
### 삭제 가능 케이스  
- 로그인 사용자와 질문한 사람이 같은 경우 삭제가 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변 글의 모든 답변자가 같은 경우 삭제 가능하다.
### 삭제 불가 케이스
- 질문자와 답변자가 다르면 다르면 삭제 불가.
- 로그인 사용자와 질문자가 다르다.
### 히스토리  
- 질문과 답변의 삭제 이력을 DeleteHistory에 남긴다.

## 프로그래밍 요구사항
- qna.service.QnaService의 deleteQuestion()은 앞의 질문 삭제 기능을 구현한 코드이다.  
  이 메서드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있따.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드에 대해 단위 테스트를 구현한다.  
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
### 힌트1
- 객체의 상태 데이터를 꺼내지(get) 말고 메시지를 보낸다.
- 규칙 8 : 일급 콜렉션을 쓴다.
  - Question의 List를 일급 콜렉션으로 구현한다.
- 규칙 7 : 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.

### 힌트 2
- 테스트하기 쉬운 부분과 테스트하기 어려운 부분을 분리해 테스트 가능한 부분만 단위 테스트를 한다.