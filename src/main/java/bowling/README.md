# 볼링 2단계 기능 요구사항
## Pin
* 볼링공을 굴렸을 때 쓰러뜨린 핀의 개수(amount)를 가짐
* 현재 amount가 전체 핀의 개수와 일치하는지 확인하는 isMax()
* 자신이 가진 amount와 인자로 주어진 핀의 amount를 비교해 핀 전체를 쓰려뜨렸는지 확인하는 isClear()

## Player

## Frame
* NormalFrame와 FinalFrame에서 구현되는 인터페이스
* bowl(Pin pin), isFinished(), nextFrame()을 갖는다

## NormalFrame
* 상태 값(Status)을 가짐
* bowl() 메소드로 게임을 진행 -> Status에게 위임
* isFinished()로 게임이 종료되었는지 확인 -> Status 클래스에게 판단을 위임
* nextFrame() 으로 다음 frame을 반환
    * 게임이 종료되었을 경우 새로운 프레임, 게임이 진행중이면 기존 프레임 반환

## FinalFrame
* 상태 값(Status)의 리스트(statuses)를 가짐
* 10번째 프레임에서는 Strike/Spare 시 총 3번의 bowl() 기회가 주어짐
  * bowlCount 변수를 가져 예외 로직 처리
* ifFinished()에서 bowlCount가 3이면, 무조건 true
  * bowlCount가 2면, Miss가 있을 경우에만 true
  * 나머지(bawlCount <= 1 || Miss가 없는 BowlCount == 2) 는 false
* nextFrame()은 Exception 발생 (마지막 프레임)

## Frames
* Frame의 일급컬렉션 (볼링 게임이 진행되는 동안 최대 10개의 Frame을 관리)
* bowl() 메소드로 게임을 진행 -> Frame에게 위임
* 전체 볼링 게임 종료 여부 gameFinished()

## 상태 클래스
* 필요에 따라 pins를 가져 게임 진행 상태에 대한 정보를 기록
* isFinished() 메소드로 종료 여부를 판단
* bowl() 메소드로 게임을 진행
    * 현재 상태와 인자(Pins)에 따라 다른 상태로 변환됨

* Status(interface)
    * Running(abstract)
        * Ready -> Frame의 최초 상태
        * FirstPin -> 한 번 bowl()을 했으나 아직 한 Frame이 종료되지 않은 상태
    * Finished(abstract)
        * Miss
        * Strike
        * Spare

---
# 볼링 3단계 요구사항
## 리팩토링 요구사항
* NormalFrame, FinalFrame의 공통 로직을 AbstractFrame 으로 추출
  * Normal, Final에서 Abstract를 호출할 때 값을 다르게 주어 공통 로직을 활용
## 기능 요구사항
* 점수 계산 로직 구현하기
  * **스트라이크**면 이후 2번의 투구 점수를 합산해야 하며,
  * **스페어**면 이후 1번의 투구 점수를 합산해야 한다.

### Frame
* 점수 계산은 Frame에서 수행
    * 점수 계산을 하기 위해선 **이후 Frame**에 접근 가능해야 한다
        * `Frame next` -> private로 두어도 같은 클래스 내이므로 계속해서 다음 프레임에 접근 가능
  * Score에서 점수 가능 여부를 판단하고, 다음 Frame까지를 고려해 점수를 계산해야 한다면 다음 Frame에게 점수 계산을 위임한다.
      * 현재 프레임의 점수가 계산가능하면, 바로 계산해서 반환
        * 현재 프레임의 점수 계산 `calculateScore()`
      * 점수가 계산 가능하지 않다면, 위임
        * 이전 프레임으로부터 위임받은 **이전 프레임의** 점수 계산 `calculateLastFrameScore()` -> 먼저 `state.calculateScore(Score lastScore)` 호출
          * -> next Frame의 상태가 `Finished`면, 1. 점수를 반환하거나 2. 계속해서 next Frame으로 요청 전달
          * -> 상태가 `Finished`가 아니면서 현재 Frame의 `calculateScore(Score lastScore)` 결과가 계산 불가능하다면 `Optional.empty()` 반환 (Finished가 아니어도 우선 현재 상태를 Score에 반영해봐야함에 주의)

### Score
* `현재까지의 점수 - currentScore`, `점수 계산 가능까지 남은 bowl() 횟수 - leftBowlCount`를 저장
* `bowl(int pinCount)` -> pinCount만큼 currentScore을 증가시키고, leftBowlCount를 감소시킴
* `canCalculateScore()` -> 현 상태에서 점수 계산 가능 여부를 반환
* `score()` -> 점수가 계산 가능할 경우, 현재 점수를 반환

### State <-> Score
* Score 생성 시, bowl() 시 State에 저장된 `fallenPin` 값이 필요
  * 현재는 외부에서 접근 불가 (toString()으로 출력에서만 사용)
* State에 `score()` 과 같은 메소드를 만들어 State의 상태를 활용해 Score를 생성할 수 있도록 구현해보기
* 또, Score의 상태를 업데이트해 새로운 Score를 반환하는 메소드도 제공하기 - `calculateScore(Score lastScore)`
  * 예를 들어, Miss, Spare는 Score의 bowl()을 2회 호출해야 하고
  * Strike, FirstPin은 Score의 bowl()을 1회 호출해야 함

