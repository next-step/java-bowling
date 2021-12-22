# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 요구사항 정리
* Name : 사용자 이름
    * 이름은 영어다.
    * 이름은 3글자다.
* Pins : 핀들
    * 최소 0에서 최대 10 개의 핀을 가질 수 있다.
    * 투구해서 쓰러뜨린 핀이 0개면 거터
    * 스페어 체크할 때 두번에 쓰러뜨린 핀의 총합은 10을 넘을 수 없다.
* Frame 
    * NormalFrame
        * 1 ~ 9 프레임
        * 총 2번의 기회만 있다.
    * FinalFrame
        * 10 프레임
        * Strike나 Spare면 상태를 RESET한다.
* State
    * Running
        * 한 프레임의 게임이 끝나지 않은 상태
        * Ready
            * 아직 해당 프레임의 게임을 시작 준비하는 상태
            * bowl 함수를 호출했을 때 Strike면 Strike 객체 아니라면 FirstBowl 객체를 반환
        * FirstBowl
            * 한번 굴린 상태
            * bowl 함수를 호출했을 때 스페어면 Spare 아니라면 Miss객체 반환
    * Finished
        * 한 프레임 게임이 끝난 상태
        * Strike
            * 스트라이크
        * Spare
            * 스페어
        * Miss
            * 스페어 또는 스트라이크가 아닌 상태
* GameResult
    * 각 프레임의 결과를 getDesc()를 사용해서 저장한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)
