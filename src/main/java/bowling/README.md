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
    
3.2 FinalFrame  (수정 예정)
    - 10 프레임
    - 최대 3번의 MAX_TRY_COUNT를 가진다.
    - Pins 는 투구 합이 10 이상 검증 하는데 보너스 프레임 등은 어떡할지?
    - 스트라이크나 스페어 발생시 pins 새로 생성
    - List<Status> 를 가진다 . Strike, Spare, Strike, etc,, Miss  결과에 따라 사이즈가 다름
    - getResult를 제공한다 List<Status> 를 순회하며 result제
    
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


* 생성 시점 체크 
핀이 새로 생성되는 시점 : 한 프레임이 끝날 때 ( 투구 기회가 끝났을 때 )
프레임이 새로 생성되는 시점 : 한 프레임이 끝 날 때  (투구 기회가 끝났을 때)
==> 생성 되는 사이클이 같다.
