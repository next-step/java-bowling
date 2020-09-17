# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 1단계 질문 삭제하기 기능 리팩토링
#### 기능 요구사항

- [X] deleteQuestion()에서 테스트 할 수 있는 부분과 없는 부분을 구분하여 로직을 객체에 위임
- [X] Question 객체에서 아래 로직 수행
    * 작성자 체크 (로그인 사용자와 질문한 사람이 같은 경우 삭제 가능)
    * 삭제
    * 연관된 답변 삭제

- [X] Answer 객체에서 아래 로직 수행
     * 작성자 체크
     * 삭제

- [X] DeleteHistory를 Wrap하는 DeleteHistories 생성하여 삭제 이력 기록
    * 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
    * 답변이 없는 경우 삭제 가능
    * 질문자와 답변글의 모든 답변자 같은 경우 삭제 가능
    * 질문자와 답변자가 다른 경우 답변을 삭제할 수 없음

## 2단계 볼링 점수판(그리기)
#### 기능 요구사항

- 프레임(frame)
- [X] 볼링 점수판 1~9 프레임을 나타내는 NormalFrame 객체 생성
- [X] 마지막 프레임을 나타내는 EndFrame 객체 생성
- [X] Frame 인터페이스 추출
- [X] 볼링 점수판을 나타내는 ScoreBoard 객체 생성

- 상태(state)
- [X] 상태(State)를 나타내는 인터페이스 정의
- [X] 스트라이크(Strike) 상태 객체 생성
- [X] 스페어(Spare) 상태 객체 생성
- [X] 미스(Miss) 상태 객체 생성
- [X] 거터(Gutter) 상태 객체 생성
- [X] 초기 준비(Ready) 상태 객체 생성
- [X] 투구 계속(Continue) 상태 객체 생성
- [X] 마지막 투구(EndFrameStates) 상태 객체 생성
- [X] 마지막 투구 갯수(EndFrameCount)를 나타내는 객체 생성

- 핀(pin)
- [X] 볼링핀(Pin) 객체 생성

- 플레이어(user)
- [X] 플레이어(User) 객체 생성

- 실행
- [X] BowlingGame 메인 생성
- [X] BowlingController 생성

- 화면(view)
- [X] 입력(InputView)화면 객체 생성
- [X] 출력(OutputView)화면 객체 생성
- [ ] 플레이어(User) 객체 생성
- [ ] FrameDTO, FrameAssembler 생성
- [ ] ScoreBoardDTO, ScoreBoardAssembler 생성
- [ ] UserDTO, UserAssembler 생성

