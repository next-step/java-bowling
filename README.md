# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 1단계 - 질문 삭제하기 기능 리팩토링
* 요구사항
  * qna.service.QnaService의 deleteQuestion() 메소드 코드를 단위 테스트 가능한 코드로 분리해 단위 테스트 코드를 작성한다.


* 구현목록
  * Answers.java 일급객체 분리
    * answer 객체 delete 호출 
    * answer 객체들 삭제 히스토리 반환
  * Answer.java
    * 내부적으로 deleted 상태가 바뀌도록 수정
    * answer 삭제 히스토리 반환
  * Question.java
    * 내부적으로 deleted 상태가 바뀌도록 수정
    * answers.delete 호출
    * question 및 answers 삭제 히스토리 반환
  * QuestionBody.java
    * Question.java 의 title 과 contents를 객체로 포장
