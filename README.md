# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 기능목록
- Pin
  - 핀이 쓰러진 갯수를 구한다.
  - 0 <= pin <= 10 의 범위를 가진다.
  - 범위 를 벗어나는 값이 오면 illegalException  
  - add(Pin pin) 쓰러진 핀의 개수를 더해서 반환한다.
  - isMaxCount() pin 쓰러진 pin의 개수가 10개인지 반환한다.
  - viewString() -> 현재 상태를 볼링 규칙에 맞게 반환한다.
- State
  - 프레임의 상태를 나타낸다
  - bowl()을 호출하면 다음 상태를 반환한다
  - isFinished() 종료상태인지 반환한다
  - viewString() -> 현재 상태를 볼링 규칙에 맞게 반환한다.
  - running
    - ready
    - firstBowl
  - finished
    - Strike
    - Miss
    - Spare
  - finalFrameState
    - 시도 횟수 3번
    - miss 면 시도횟수 0번으로 변경
    - isFinished() 는 시도횟수가 0번이면 발생
- Round
  - 1~10의 값만을 가진다.
  - isLastRound() 마지막 라운드일 경우 true 반환
  - nextRound() 다음 라운드를 반환한다.
- FrameFactory
  - Round에 맞게 Frame을 반환한다.
- Frame
  - bowl(PinCount pinCount) 공을 굴린 후 Frame 을 반환한다
    - finished 된 상태에서 bowl 이 호출되면 ileegalException
    - 만약 finished 상태가 되었다면 다음 Frame을 반환한다.
    - 아니라면 round는 같고 State는 다른 Frame을 반환한다.
  - isEqualsRound() round 가 같으면 true를 반환한다.
  - round() frame의 round를 반환한다.
- Frames
  - Frame을 포함하는 1급 객체
  - isEndGame() 호출 시 마지막 Frame의 isEndGame을 리턴한다.
  - bowl() 마지막 프레임에 bowl() 을 호출 한다.
  - bowl() 호출 후 다음 프레임이 반환되면 list에 add한다.
- User
  - 이름은 3글자 영어 단어만 가능
- GameResult
  - 진행된 게임의 result를 저장하는 객체
- BolwingGame
  - 볼링 게임을 관리하는 객체
  - bowl() 후 GameResult를 반환한다.
  

