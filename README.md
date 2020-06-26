# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


## 기능 구현 목록
1. 게임 참여자 Player
2. 참여자의 이름 Name
3. 볼링의 한 프레임 Frame - 인터페이스
4. 프레임의 번호 FrameNumber
5. 프레임 각 투구 정보 Pitch
6. 투구의 상태 State
8. Frame의 일급 컬렉션 Frames
9. Player와 해당하는 Frame을 Map으로 관리하는 ScoreBoard
10. Players로 게임을 진행하는 BowlingGame