## 볼링 점수판(그리기)

### 기능 요구 사항
1. 플레이어 이름을 입력받는다
    - 이름은 3글자로 제한한다

2. 이름이 표시된 볼링판을 출력한다
    - 볼링판은 01 ~ 10의 프레임을 가진다

3. n프레임 투구를 입력받는다 (1~10까지 반복)
    - 프레임 표시법에 따라 표출한다. 
        + 스트라이크 X
        + 스페어 9|/
        + 미스 8|1
        + 거터 -, 9|-
    - 한 프레임당 두 번의 투구를 입력을 받는다
    - 첫번째 투구가 스트라이크인 경우 다음 프레임으로 넘어간다
    
4. 점수가 추가 된 볼링 판을 출력한다.


### 도메인 설계
1. ScoreBoard
    - Player, Score, String basicLine
    
2. Frame
    - int index, int firstScore, int SecondScore
    - 각 프레임의 점수를 가진다.
    
3. Frames
    - List<Frame> frame = new LinkedList<>();
    - 1 ~ 10 프레임을 가진다 

4. Player
    - String name
    
5. Score (enum)
    - 점수를 가진다. strike, spare, miss, gutter

6. BowlingGame 볼링 메인
    1. 플레이어 이름을 입력받는다.
    2. 볼링판 출력
    3. currentFrame이 10이 될 때 까지 투구 입력(점수 업데이트), 점수판 출력 반복 
