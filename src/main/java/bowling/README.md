# 볼링 점수판(그리기)

## 도메인 분리
1. Pins 볼링 핀
    - int downPin 을 가지는 핀 객체 (쓰러진 핀의 개 수)
    - 입력 값을 검증한다.
        + downPin은 0 ~ 10 사이의 수이다
        + 총 투구의 downPin 합은 10 이내이다 (마지막 투구 제외) 
    - 출력용 점수를 제공한다.

2. Status
    - 프레임 별 투구 상태 
    - Strike, Spare, Miss 어떻게 활용하지?
    
3. Frame
    - NormalFrame, FinalFrame 구현을 위한 Frame 인터페이스
    - Pins, int trying 을 가진다
    - 다음 Frame을 생성한다. 
    - n 번의 투구를 한다.
    
3.1 NormalFrame
    - 1 ~ 9 프레임
    - 2번의 MAX_TRY_COUNT를 가진다.
    - 두번째 투구일 경우 새 핀 생성
    
3.2 FinalFrame
    - 10 프레임
    - 보너스 투구 발생 시 최대 3번의 MAX_TRY_COUNT를 가진다.
    - 보너스 투구 발생 시 pins 값을 초기화한다
    - List<Status> 를 가진다 
    
4. Frames
    - List<Frame> Frames 를 가지는 1급 컬렉션
    - 10개의 프레임을 가진다.
    - 투구한다

5. BowlingGame
    - Player, Frames, currentIndex 를 가지는 게임 진행을 위한 객체 
    - 게임을 진행한다.
        
6. Player
    - String name을 가진다
    - 이름이 3글자인지 검증
