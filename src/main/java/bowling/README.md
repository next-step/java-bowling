# 2단계 - 볼링 점수판(그리기)
## Step 2
### 요구사항
* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
  * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

### Model 
* Player
  * [x] Player 는 플레이어의 이름을 관리한다.
  
* BowlingGame
  * [x] BowlingGame 는 볼링 게임을 관리한다.
  * [x] Player 와 BowlingFrames 를 가지고 있다.
  
* BowlingFrame
  * [x] BowlingFrame 는 볼링 프레임을 관리한다.
  * [x] interface 로 bowl, isOver, sum, getFrameScore 의 메소드를 강제한다.
    
* BowlingFrames
  * [x] BowlingFrames 는 BowlingFrame 의 일급 컬렉션이다.
  
* CommonBowlingFrame
  * [x] CommonBowlingFrame 은 BowlingFrame 의 구현체이다.
  * [x] 1~9 프레임을 구현한다.
  * [x] FrameScore 와 Pins 를 관리한다.
  
* LastBowlingFrame
  * [x] LastBowlingFrame 는 BowlingFrame 의 구현체이다.
  * [x] 10 프레임임을 구현한다.
  * [x] FrameScore 와 Pins 를 관리한다.
  
* FrameScore
  * [x] FrameScore 는 Score 를 가지고 있다.

* Score
  * [x] Score 는 점수를 나타낸다.
  
* Pins
  * [x] Pins 볼링 프레임의 남은 핀 수를 구현한다.
  
* FrameScoreResult
  * 스트라이크(strike)
    * 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태 (Ex. X)
  * 스페어(spare)
    * 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태 (Ex. 7|/)
  * 미스(miss)
    * 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태 (Ex. 7|-)
  * 거터(gutter)
    * 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시 (Ex. -|-)
  
  
  
 