# 볼링 점수

## 요구 사항
* 사용자 1명의 볼링 게임 점수를 관리할 수 있는 프로그램을 구현한다.
* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다. 
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
  * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 스트라이크는 다음 2번의 투구까지 점수를 합산해야 한다. 스페어는 다음 1번의 투구까지 점수를 합산해야 한다.
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.
* 1명 이상의 사용자가 사용할 수 있는 볼링게임 점수판을 구현한다.

## 기능 정의

* 입력 기능
  - [x] 참가자 수를 입력받는다.
  - [x] 플레이어의 이름을 입력받는다.
  - [x] 쓰러트린 핀의 갯수를 입력받는다.
  
* 볼링 게임
  - [ ] n명의 사용자가 게임을 한다.
  - [X] 사용자를 정의해야한다.
  - [x] 각 프레임마다 결과를 반환한다.
    - [x] 총 프레임의 갯수는 10개이다.
    - [x] 한 프레임은 2번의 시도 횟수를 가진다.
       - 10번째 프레임은 스트라이크 or 스페어인 경우 한번의 투구 기회가 주어진다.
    - [x] 결과: 스트라이크, 스페어, 미스, 거터
    
* 점수 계산기
  - [x] 볼링 게임의 결과를 계산 할 수 있다.
    - [x] 스트라이크는 다음 2번의 투구 점수를 합산한다.
    - [x] 스페어는 다음 1번의 투구 점수를 합산한다.
  
* 출력 기능
  - [x] 프레임의 결과를 문자열로 출력한다. 