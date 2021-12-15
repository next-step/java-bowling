# 볼링 게임 점수판

## 1단계 - 질문 삭제하기 기능 리팩토링
### TODO
* [x] Question#answers List<Answer>를 일급 컬랙션으로 변경
  * [x] answers를 지우는 메소드
  * [x] return delete history
* [x] 삭제 메소드를 Question에 구현
  * [x] 답변들이 삭제 가능한지 확인
  * [x] return delete history
* [x] DeleteHistories as first class collection
  * [x] abstract class FirstClassCollection (Answers, DeleteHistories)
* [x] DeleteHistory factory method

### 질문 삭제하기 요구사항
* 질문 데이터를 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
* 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능
* 답변이 없는 경우 삭제 가능
* 질문자와 답변글의 모든 답변자같은 경우 삭제 가능
  * 질문자와 답변자가 다른 경우 답변 삭제 불가
* 질문을 삭제할 때 답변도 삭제
  * 답변의 삭제 또한 삭제 상태(deleted)를 변경
  * 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory에 남김

### 프로그래밍 요구사항
* qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
* 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.

## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)