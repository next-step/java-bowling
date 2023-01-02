## 볼링 1단계 기능 요구사항
### Pin
* 볼링공을 굴렸을 때 쓰러뜨린 핀의 개수(amount)를 가짐
* 현재 amount가 전체 핀의 개수와 일치하는지 확인하는 isMax()
* 자신이 가진 amount와 인자로 주어진 핀의 amount를 비교해 핀 전체를 쓰려뜨렸는지 확인하는 isClear()

### Player

### Frame
* NormalFrame와 FinalFrame에서 구현되는 인터페이스
* bowl(Pin pin), isFinished(), nextFrame()을 갖는다

### NormalFrame
* 상태 값(Status)을 가짐
* bowl() 메소드로 게임을 진행 -> Status에게 위임
* isFinished()로 게임이 종료되었는지 확인 -> Status 클래스에게 판단을 위임
* nextFrame() 으로 다음 frame을 반환
    * 게임이 종료되었을 경우 새로운 프레임, 게임이 진행중이면 기존 프레임 반환

### FinalFrame
* 상태 값(Status)의 리스트(statuses)를 가짐
* 10번째 프레임에서는 Strike/Spare 시 총 3번의 bowl() 기회가 주어짐
  * bowlCount 변수를 가져 예외 로직 처리
* ifFinished()에서 bowlCount가 3이면, 무조건 true
  * bowlCount가 2면, Miss가 있을 경우에만 true
  * 나머지(bawlCount <= 1 || Miss가 없는 BowlCount == 2) 는 false
* nextFrame()은 Exception 발생 (마지막 프레임)

### Frames
* Frame의 일급컬렉션 (볼링 게임이 진행되는 동안 최대 10개의 Frame을 관리)
* bowl() 메소드로 게임을 진행 -> Frame에게 위임
* 전체 볼링 게임 종료 여부 gameFinished()

### 상태 클래스
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