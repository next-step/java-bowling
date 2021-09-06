# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

***


# 1단계 - 질문 삭제하기 기능 리팩토링

## 요구사항
* 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
* 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
* 답변이 없는 경우 삭제가 가능하다.
* 질문자와답변글의모든답변자같은경우삭제가가능하다.
* 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경 한다.
* 질문자와답변자가다른경우답변을삭제할수없다.
* 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 기능 정의

* 답변
  - [x] SoftDelete (setter 제거)
  - [x] 일급 컬렉션
    - [x] 삭제 가능 여부
    - [x] 모든 답변 삭제
* 질문
  - [x] 삭제 가능 여부
    - [x] 질문자와 사용자 동일한 경우 삭제 가능
    - [x] 답변이 없거나, 모든 답변의 작성자가 질문자와 동일한 경우 가능
    - [x] 1개 이상의 답변자가 질문자와 다른 경우 삭제 불가
  - [x] SoftDelete
  - [x] 삭제 후 삭제이력 객체 리턴
* 삭제 이력
  - [x] 일급 컬렉션 사용

## 피드백
- [x] 삭제이력 추가 시 동일 레퍼런스 해제
- [x] 중복 로직 제거
- [x] 답변 삭제 가능 여부 검증 위치 수정

***

# 2단계 - 볼링 점수판(그리기)

## 요구사항
* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
  * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

## 기능 정의

* 플레이어
  -[x] 이름 제한
    - [x] 빈문자 허용안함
    - [x] 3글자만 허용
    - [x] 영문만 허용
* 일반 프레임
  * 투구
    - [x] 기본 2회 투구
    - [x] 투구당 최소 0점 최대 10점 (점수 = 쓰러트린 핀 수)
      - [x] 2회 투구의 합산 최소 0점 최대 10점
      - [x] 점수는 음수가 될 수 없음
    - [x] 초구 10점 = `스트라이크`
    - [x] 스트라이크시 두번째 투구 없음
    - [x] 2회 투구의 합산이 10점 = `스페어`
    - [x] 모든 투구 후 다음 프레임 진행
    - [x] 9번 프레임 이후 마지막 프레임 진행
* 마지막 프레임
  * [x] 첫번째 투구 시 스트라이크하면 세번째 투구 가능
    - [x] 두 번재 투구가 0점이어도 마지막(세번째) 투구 가능
  * [x] 두번째 투구 시 스페어를 하면 한 번의 투구를 추가
* 화면
  * [ ] 스트라이크 표시 : `X`
  * [ ] 스페어 표시 : `초구점수|/`
  * [ ] 미스 표시 : `초구점수|-`