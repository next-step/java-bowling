# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 기능 요구사항
### 그리기
* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
  * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

## TODO
### 점수계산
* score type enum
  * strike(x) : 10점
  * spare(/) : 10점
  * miss(숫자) : 1~9점
  * gutter(-) : 0점
  * 정적 팩토리 메서드
    * 이전 점수 + 현재점수가 10이면 spare
    * 나머지는 점수대로 할당
* score
  * score type, score
* frame 추상 클래스, 일급 콜렉션
  * score1, score2, score3
  * normalFrame
    * 2회까지 투구 가능
    * strike 인 경우 1회 투구
  * finalFrame
    * strike 또는 spare 인 경우 3회까지 투구 가능
    * 그 외에는 2회 투구
* frames 일급콜렉션
  * frame list
* player
  * name : 참가자명
  * frames
* 입출력 view 및 게임실행
