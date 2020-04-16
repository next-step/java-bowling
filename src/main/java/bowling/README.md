# 클래스 정리

### Pins
* 남은 핀의 개수(10개로 시작)
* 핀은 1~10 개 사이 여야 한다

### Score
* 점수를 가진다
* 점수는 0점 부터 시작한다
 
### Bowl
1. 핀을 쓰러뜨린다

### result
1. ready, strike, spare, miss, gutter

### Tern
1. first - second - third 

### Frame
1. 인터페이스
    ### NormalFrame
    1. Tern을 가진다
    1. 다음 Frame를 가진다.
    1. frame 번호를 가진다
    1. 점수를 계산한다
    1. 볼을 굴린다.
    1. 다음 프레임을 생성한다
    
    ### FinalFrame
    1. NormalFrame를 가진다

