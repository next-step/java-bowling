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
    * FrameScore와 Pins를 관리한다.
    * 쓰러트린 볼링 핀수를 입력받아 FrameScore과 Pins를 갱신한다.
    * SubTotalScore을 계산하여 반환한다.
* CommonBowlingFrame
    * 1-9 프레임을 표현하는 BowlingFrame 구현체
    * 남은 투구 기회 판단은 다음과 같다.
        * 스트라이크면 프레임 종료
        * 두번째 투구가 끝나면 프레임 종료
* LastBowlingFrame
    * 마지막 프레임을 표현하는 BowlingFrame 구현체
    * 남은 투구 기회 판단은 다음과 같다.
        * 두번째 투구까지 스트라이크나 스페어가 아니면 프레임 종료 
        * 세번째 투구가 끝나면 프레임 종료
* Pins
    * 볼링 프레임의 남은 볼링 핀 수를 관리한다.
    * 초기값은 10이며, 최소값은 0이다.
    * 쓰러트린 핀 수를 입력받으면 남은 볼링 핀수를 갱신하고 Score 값을 반환한다.
        * 쓰러트린 핀 수가 남은 볼링 핀수보다 많으면 에러를 발생한다.
    * 남은 볼링 핀 수를 리셋할 수 있다. (다시 10개로)
* FrameScore
    * 볼링 프레임의 점수를 관리한다.
    * Score 리스트를 관리한다.
    * Score 의 합을 반환한다.
    * SubTotalScore 객체를 반환한다.
    * SubTotalScore를 입력받아서 SubTotalScore 객체를 반환한다.
* Score
    * Score는 0 이상 10 이하의 정수 타입이다.
    * 0 ~ 10 사이의 정수를 입력 받아 투구 점수를 생성한다.
* SubTotalScore
    * 각 프레임의 획득 Score를 관리하는 객체.
    * 획득 스코어 Score와 이전 프레임에서 합산할 수 있는 객체 NextAddingUpScores를 관리한다.
* NextAddingUpScores
    * Strike나 spare시 합산할 score를 저장하는 객체.
    * 최대 2개의 Score를 가질 수 있다.
* FrameScoreResult
    * FrameScore를 가지고 아래 4가지 종류를 표현한다.
    * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태 (Ex. X)
    * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태 (Ex. 7|/)
    * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태 (Ex. 7|-)
    * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시 (Ex. -|-)