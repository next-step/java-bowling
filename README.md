# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 구현대상 소스
### [UI]
* BowlingGameApplication : 볼링게임 실행을 위한 UI 어플리케이션
* InputView : 이름과 투구정보 입력을 위한 UI View
* OutputView : 투구 후, 현재의 게임진행 상황을 보여주는 출력기능 UI View
### [Presentation Layer]
* BowlingGameController : 볼링게임 실행 및 결과를 FrontEnd 에 제공하기 위한 API 진입소스
* BowlingGameRequest : 유저의 투구정보를 Backend 로 전달하기 위한 input dto
* BowlingGameResponse : 유저의 투구 후 게임 실행현황을 Frontend 로 전달하기 위한 output dto
* FrameInfo : 회차별 게임정보를 저장하고 있는 output dto. 회차번호와 투구결과를 문자열 형태로 갖는다.
### [Service Layer]
* BowlingGameService : 볼링게임 투구정보를 입력받아 도메인객체를 리턴해주는 도메인 서비스
### [Persistence Layer]
* BowlingGameRepository : 참가자별 볼링정보를 조회하고 저장하기 위한 리포지토리 클래스
### [Domain Object]
* BowlingGame : Frame 정보를 List 형태로 갖는 일급컬렉션 도메인 클래스
* Frame : 볼링 한 회차 정보를 표현하는 도메인 추상 클래스
* NormalFrame : 최종회차를 제외한 나머지 회차를 표현하는 도메인 클래스
* FinalFrame : 마지막 회차를 표현하기 위한 도메인 클래스
* Pitches : 투구처리의 집합을 표현하는 일급컬렉션 도메인 클래스 
* Pitch : 투구 한번을 표현하기 위한 도메인 클래스
### [Infra]
* BowlingGameDatabase : 볼링게임 진행내역을 저장하기 위한 데이터베이스