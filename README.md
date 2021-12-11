# 볼링 게임 점수판
- InputView
  - 플레이어 이름을 입력받는다.
  - 이번 투구에 넘어뜨린 핀 개수를 입력 받는다.
- OutputView
  - 플레이어의 이름을 출력한다.
  - 프레임별로 현재 까지의 결과를 출력한다.
    - 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력
    
- Pin: 한 투구에 넘어뜨린 핀의 개수
  - 넘어뜨린 개수를 입력받아 Pin 생성
 
- Bowl
  - ProceedingBowl: 한번 더 투구를 할 수 있는 공
    - FirstBowl
    - NextBowl
  - EndedBowl: 더이상 투구할 수 없는 공
    - StrikeBowl
    - SpareBowl
    - MissBowl
    - GutterBowl

- Frame: 하나의 프레임을 의미, 이번 프레임에 친 공을 인스턴스 변수로 갖는다.
  - NormalFrame 1 ~ 9 프레임
  - FinalFrame 10 프레임

- Bowling
