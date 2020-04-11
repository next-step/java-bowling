# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 구현목록
* Player
    * 플레이어의 이름을 관리한다.
        * 플레이어 이름은 영문 세글자여야 한다.
* BowlingGame
    * 볼링 게임을 관리한다.
    * Player와 BowlingFrames를 관리한다.
* BowlingFrames
    * BowlingFrame 목록을 관리한다.
    * 최종 프레임 리스트는 10개여야 한다.
* BowlingFrame
    * 볼링 게임의 프레임을 관리한다.
    * FrameScore와 마지막 프레임 유무를 관리한다.
    * 남은 투구 기회 판단은 다음과 같다.
            * 1-9 프레임 경우, 첫번째 투구가 스트라이크면 프레임 종료
            * 1-9 프레임 경우, 두번째 투구가 끝나면 프레임 종료
            * 10 프레임 경우, 두번째 투구까지 스트라이크나 스페어가 아니면 프레임 종료 
            * 10 프레임 경우, 세번째 투구가 끝나면 프레임 종료s
* FrameScore
    * 볼링 프레임의 점수를 관리한다.
    * Score 리스트를 관리한다.
    * Score 의 합을 반환한다.
* FrameScoreResult
    * FrameScore를 가지고 아래 4가지 종류를 표현한다.
    * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태 (Ex. X)
    * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태 (Ex. 7|/)
    * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태 (Ex. 7|-)
    * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시 (Ex. -|-)
* Score
    * Score는 0 이상 10 이하의 정수 타입이다.
    * 0 ~ 10 사이의 정수를 입력 받아 투구 점수를 생성할 수 있다.
    * 0 ~ (10 - 이전 투구 점수) 사이의 정수를 입력 받아 다음 투구 점수를 생성할 수 있다.