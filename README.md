# 볼링 게임 점수판

## 진행 방법

* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

* * *

# 질문 삭제하기 기능 리팩토링

## 질문 삭제하기 요구사항

* 질문 데이터를 완전히 삭제하는 것이 아니고, 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
* 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
* 답변이 없는 경우 삭제 가능하다.
* 질문자와 답변글의 모든 답변자같은 경우 삭제 가능하다.
* 질문자와 답변자가 다른 경우 답변 삭제할 수 없다.
* 질문을 삭제할 때 답변도 삭제한다.
    * 답변의 삭제 또한 삭제 상태(deleted)를 변경
    * 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory로 남긴다.

## 구현 내용

* [X] Entity: `Question`의 Field: `List<Answer> answers`를 일급 컬랙션으로 변경
    * [X] `Question`에 속한 `List<Answer> answers` 삭제 메서드 `delete` 추가
    * [X] `delete` 메서드 호출 이후, 삭제 이력 `List<DeleteHistory> deleteHistories` 반환
* [X] Entity: `Answer`의 도메인 테스트 `AnswerTest` 추가
* [X] Entity: `Question`의 삭제 메서드 `delete` 추가
    * [X] VO: `Answers`의 삭제 여부 확인 메서드 `deletable` 추가
    * [X] `List<DeleteHistory>` 반환 방식으로 변경
* [X] Entity: `DeleteHistory`의 리스트 형태 사용 로직에 일급 컬렉션 `DeleteHistories` 적용
* [X] `DeleteHistory` 생성 관련 로직을 정적 팩토리 메서드 위임

## 프로그래밍 요구사항

- qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.
