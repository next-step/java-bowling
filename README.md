# 볼링 게임 점수판

## 요구 사항

### 기능 요구사항

* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
    * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

### 구현 시작 방법

* 볼링 게임의 점수 계산 방식 아는 사람은 바로 구현을 시작한다.
* 점수 계산 방식을 모르는 사람은 구글에서 "볼링 점수 계산법"과 같은 키워드로 검색해 볼링 게임의 점수 계산 방식을 학습한 후 구현을 시작한다.

### 프로그램 실행 결과

![bowling_result](https://user-images.githubusercontent.com/15815583/147379038-f9c2c7a5-f7a9-466a-ae62-7a757fe3c9a9.png)

## 프로그래밍 요구사항

* **객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.**

### 객체지향 생활 체조 원칙

* 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
* 규칙 2: else 예약어를 쓰지 않는다.
* 규칙 3: 모든 원시값과 문자열을 포장한다.
* 규칙 4: 한 줄에 점을 하나만 찍는다.
* 규칙 5: 줄여쓰지 않는다(축약 금지).
* 규칙 6: 모든 엔티티를 작게 유지한다.
* 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
* 규칙 8: 일급 콜렉션을 쓴다.
* 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.

## 힌트

* 객체 단위를 가장 작은 단위까지 극단적으로 분리하는 시도를 해본다.
* 1 ~ 9 프레임을 NormalFrame, 10 프레임을 FinalFrame과 같은 구조로 구현한 후 Frame을 추가해 중복을 제거해 본다.
* 다음 Frame을 현재 Frame 외부에서 생성하기 보다 현재 Frame에서 다음 Frame을 생성하는 방식으로 구현해 보고, 어느 구현이 더 좋은지 검토해 본다.

## 볼링 계산기 참고 링크

https://www.bowlinggenius.com/

## 기능 목록

* `Player`
    * `Player` 생성
        * '영문 대소문', '3글자' 이름 조건 허용
* `Pins`
    * `Pins` 생성
        * 핀의 갯수는 0개에서 10개까지 허용
* `ThrowingState`
    * `RunningState` 한 프레임이 진행중인 볼링 투구 상태
    * `EndedState` 한 프레임이 끝난 볼링 투구 상태

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
* [X] Entity: `Answer`, `DeleteHistory`, `Question`, `User` 에 Field: `id` 기반 equals, hashCode 최적화
* [X] Entity: `Answer`, `DeleteHistory`, `Question`, `User`의 toString 재정의
* [X] Entity: `Answer` 삭제 로직 메서드로 위임
* [X] Entity: `User`, `Question`의 사용하지 않는 getter, seter 제거
* [X] Entity: `Question` 질문 삭제 테스트
    * [X] 질문 삭제 - 질문에 답변(들)이 있는데, 질문자와 답변자가 같은 경우
    * [X] 질문 삭제 예외 - 질문에 답변(들)이 있는데, 질문자와 답변자가 다른 경우

## 프로그래밍 요구사항

- qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.
