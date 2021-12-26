# 볼링 게임 점수판

## 2단계

### TODO
* [ ] domain 설계
  * [x] 투구 결과
  * [ ] 두번의 투구 결과를 갖고 있는 프레임
    * [x] 몇번째 프레임
    * ~~[x] 투구 결과를 합산한 점수~~ : 2단계에서 필요한 스펙이 아님
    * [ ] 마지막 10프레임은 3번 투구
    * [x] 나머지는 일반 프레임
  * [ ] 10개의 프레임을 갖고 있는 점수판
    * [ ] 최대 3글자의 이름
* [ ] controller 설계
    * [ ] 현재 프레임을 받아올 수 있어야함
    * [ ] 10 프레임 종류 후 프로그램 종료
* [ ] view 설계
  * [ ] 이름 입력
  * [ ] 투구 결과 입력

## 기능 요구 사항
 * 최종 목표는 볼링 점수를 계산하는 프로그램을 구현
   * 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현
 * 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력
 * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
 * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
 * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
 * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
 * 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

## 프로그래밍 요구사항
 * 객체지향 생활 체조 원칙
   * 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
   * 규칙 2: else 예약어를 쓰지 않는다.
   * 규칙 3: 모든 원시값과 문자열을 포장한다.
   * 규칙 4: 한 줄에 점을 하나만 찍는다.
   * 규칙 5: 줄여쓰지 않는다(축약 금지).
   * 규칙 6: 모든 엔티티를 작게 유지한다.
   * 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
   * 규칙 8: 일급 콜렉션을 쓴다.
   * 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.
   
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