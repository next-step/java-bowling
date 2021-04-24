# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 2단계

### 도메인 분석

- 플레이어는 10 프레임 동안 플레이한다.
- 각 프레임마다 최대 두 번 투구한다.
- 프레임의 첫 투구에서 스트라이크를 했다면 다음에 던지지 않는다.
- 첫 투구에서 남은 핀이 있으면 한 번 더 던진다.
    - 남은 핀을 다 제거하면 스페어 상태가 된다.
    - 남은 핀이 있다면 미스 상태가 된다.
- 투구에서 핀을 하나도 쓰러트리지 못하면 거터 이다.
- 10 프레임에서는 초구 스트라이크 또는 2구 에서 스페어를 한 경우에만 세 번째 투구를 진행한다.
    - 10 프레임에서 최대 세 번의 스트라이크가 가능하다.
    
### 설계

- 볼링
    - 프레임
        - 한 개에서 세 개 까지의 투구를 가진다.
        - 프레임이 끝나면 각 투구의 결과를 종합한 상태를 보유한다.
    - 투구(pitch)
        - 각 투구는 쓰러뜨린 핀의 개수를 가지고 있다.

### 결과

- Player
    - PlayerName: 이름 제약조건 책임
    - Bowling: 볼링 게임 컨트롤
        - Frames: 프레임 목록을 제어
            - Frame: 한 프레임의 상태를 제어
                - Pins: 현재 프레임에 남아있는 핀을 추상화
                - Records: 각 투구 당 기록을 보관
                    - Record: 투구 1회의 기록을 추상화
        - FrameCreator: 프레임 생성 책임 부여

