# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 기능 요구사항 - 질문 삭제하기 기능 리팩토링
### Question
- [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
    - [X] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
    - [X] 답변이 없는 경우 삭제가 가능하다. (Answer에게 삭제 가능한지 확인한다.)
    
### Answer
- [X] 답변 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
    - [X] 로그인 사용자와 답변한 사람이 같은 경우 삭제 가능하다.
    
### DeleteHistory
- [X] 질문 삭제 이력에 대한 정보를 남긴다.
- [X] 답변 삭제 이력에 대한 정보를  남긴다.

## 기능 요구사항 - 볼링 점수판
### Name : 이름
- [X] 이름은 세글자의 영어로 구성된다.
- [X] 이름은 빈 문자열이거나 null일 수 없다.

### Player
- [X] `Player` 는 `Name` 객체를 가진다.

### Frame : 프레임 공통 객체
- [ ] `FrameNumber` 와 `FrameScore` 를 인스턴스 변수로 가진다.

### NormalFrame : 1 ~ 9 프레임
- [ ] `Frame` 객체를 가진다.

### FinalFrame : 10 프레임
- [ ] `Frame` 과 `Score`(보너스 점수) 를 인스턴스 변수로 가진다.

### FrameNumber : 프레임 번호
- [X] 프레임 번호는 1 ~ 10까지의 숫자만 가능하다.
- [X] 프레임 번호 출력 시 1의 단위인 경우 앞에 '0'을 붙여서 출력한다.

### Score : 점수
- [X] 점수는 0 ~ 10 사이의 int 형 변수이다.
- [X] 점수는 0점인 경우 '-', 10점인 경우 'X' 를 출력한다.

### FrameScore : 프레임 당 점수
- [X] 프레임 당 점수 객체는 `Score` 객체 2를 가진다.
    - 1번 투구 점수
    - 2번 투구 점수
- [X] 두 Score 점수의 합은 10 점을 초과할 수 없다.

### InputView
- [X] 플레이어를 입력받아 그대로 리턴한다.
- [X] 각 프레임의 점수를 입력 받아 리턴한다.

### ResultView
- [ ] 점수판을 출력한다.