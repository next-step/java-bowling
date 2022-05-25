# 1단계 - 질문 삭제하기
## 질문 삭제하기 요구사항
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변 글의 모든 답변자같은 경우 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
  한다.
- 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 프로그래밍 요구사항
- qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.

<prd><code>

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
</code></pre>

##힌트1
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
    - Question의 List를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    - 인스턴스 변수의 수를 줄이기 위해 도전한다.

##힌트2
- 테스트하기 쉬운 부분과 테스트하기 어려운 부분을 분리해 테스트 가능한 부분만 단위테스트한다.

## 구현 사항
### 테스트
- Answer 테스트코드 추가
- Question 테스트코드 추가
- User 테스트코드 추가

### 리팩토링
- List<T> 일급 객체로 분리
    - Question.List<Answer>
    - QnAService.List<DeleteHistory>

- QnAService.deleteQuestion 수정