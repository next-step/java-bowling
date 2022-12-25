                            ## 볼링 1단계 기능 요구사항
### Pins
* 볼링공을 굴렸을 때 쓰러뜨린 핀의 개수(amount)를 가짐
* 현재 amount가 전체 핀의 개수와 일치하는지 확인하는 isMax()
* 자신이 가진 amount와 인자로 주어진 핀의 amount를 비교해 핀 전체를 쓰려뜨렸는지 확인하는 isClear()

### Frame
* TODO::NormalFrame과 FinalFrame의 공통 부분 추출하여 interface로 만들기

### NormalFrame
* 상태 값(Status)을 가짐
* bowl() 메소드로 게임을 진행 -> Status에게 위임
* isFinished()로 게임이 종료되었는지 확인 -> Status 클래스에게 판단을 위임
* nextFrame() 으로 다음 frame을 반환
    * 게임이 종료되었을 경우 새로운 프레임, 게임이 진행중이면 기존 프레임 반환

### FinalFrame
* 상태 값(Status)의 리스트를 가짐
* FinalFrame에 맞게 bowl() 메소드 수정

### Frames
* Frame의 일급컬렉션 (볼링 게임이 진행되는 동안 최대 10개의 Frame을 관리)
* bowl() 메소드로 게임을 진행 -> Frame에게 위임
* 전체 볼링 게임 종료 여부 gameFinished()

### NormalFrame
* 1번에 10을 던지면 Finished
* 1번에 9이하를 던지면 Running(FirstPin), 2번 기회를 주고 Finished

### FinalFrame
* 1번에 10을 던지면 Running
    * 1번에 9이하를 던져도 Running
    * 2번째 기회에서 2번째까지 합산이 10이 되면 Running(SecondPin)
* 3번째 기회를 주고, Finished


### 상태 클래스
* 필요에 따라 pins를 가져 게임 진행 상태에 대한 정보를 기록
* isFinished() 메소드로 종료 여부를 판단
* bowl() 메소드로 게임을 진행
    * 현재 상태와 인자(Pins)에 따라 다른 상태로 변환됨

* Status(interface)
    * Running(abstract)
        * Ready -> Frame의 최초 상태
        * FirstPin
        * SecondPin -> FinalFrame에서만 사용
    * Finished(abstract)
        * Miss
        * Strike
        * Spare