# 볼링 게임 점수판

## Step1

### Environment

- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와답변글의모든답변자같은경우삭제가가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
- 한다.
- 질문자와답변자가다른경우답변을삭제할수없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

### refactoring Todo list

- [x] modify question.getAnswers to question.checkPrivilegeOnAnswer
- [x] modify question.getWriter to question.createDeleteHistory
- [x] create first class collection with Answers
- [x] create first class collection: DeleteHistories
- [x] wrap with DeleteHistories
- [x] wrap with Answers
- [x] create first class collection: Questions
- [x] add AnswersTest
- [x] add DeleteHistoriesTest

### Requested changes

- [x] DeleteHistories.saveAll 제거
    - [x] DeleteHistoryService.saveAll 에서 바로 save
- [x] Answer 의 별도 생성자 제거, 다른방법 테스트?
- [x] Question.createDeleteHistory 불필요 파라미터 question, new 제거
- [x] QnAService.deleteQuestion 을 Question 클래스로 이동

### Requested changes phase2

- [x] Answers.deleteHistory 에서 DeleteHistories 받지 말고 로컬 변수로 구현
- [x] Questions.java 제거
- [x] Question.createDeleteHistory => DeleteHistory 로 이동
- [x] DeleteHistoryService.saveAll 에서 getter 로 바로 저장 하도록 수정

## Step2 - bowling scoreboard drawing

### Requirements

- 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
- 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
- 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
- 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
- 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
- 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
- 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

### Todos

- [x] scan Player name
    - [x] name should be 3 chars
- [x] print initial score board
- [x] rename Score -> Player, add Score enum, Player.scores = List<Score>
- [x] Player.pitch => random hit
- [x] evaluate hit with ScoreType enum, Score.payload to show current score.
- [x] Player.plays => prevScore exists, Score.play once, else twice
- [x] renew score, print score
- [x] if second of strike, iterate to next frame
    - [x] else, try same frame one more
- [x] test: add ScoreTypeTest
- [x] fix: play should hit only remainingPin
- [x] Should save, print Second score correctly.
    - [x] save only prevScore
    - [x] add gutter testcase
    - [x] save score with Hit(first, second)
- [x] add testcase for Score, Hit

### Result

```
put your nick name(3 characters): 
GON
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |      |      |      |      |      |      |      |      |      |      |  
Frame 1
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8   |      |      |      |      |      |      |      |      |      |  
Frame 1
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |      |      |      |      |      |      |      |      |      |  
Frame 2
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9   |      |      |      |      |      |      |      |      |  
Frame 2
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |      |      |      |      |      |      |      |      |  
Frame 3
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3   |      |      |      |      |      |      |      |  
Frame 3
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |      |      |      |      |      |      |      |  
Frame 4
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1   |      |      |      |      |      |      |  
Frame 4
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |      |      |      |      |      |      |  
Frame 5
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1   |      |      |      |      |      |  
Frame 5
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |      |      |      |      |      |  
Frame 6
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |      |      |      |      |  
Frame 7
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2   |      |      |      |  
Frame 7
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2|5 |      |      |      |  
Frame 8
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2|5 |  2   |      |      |  
Frame 8
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2|5 |  2|3 |      |      |  
Frame 9
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2|5 |  2|3 |  1   |      |  
Frame 9
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2|5 |  2|3 |  1|/ |      |  
Frame 10
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2|5 |  2|3 |  1|/ |  7   |  
Frame 10
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
| GON  |  8|1 |  9|/ |  3|/ |  1|3 |  1|5 |  X   |  2|5 |  2|3 |  1|/ |  7|2 |  
```

### Requested changes

- [x] Optional 사용 최소화
    - Optional은 argument로 사용해서는 안된다
    - [x] ScoreType.NULL, Const.NULL = -1 을 만들어 구현
- [x] 매직넘버 const 로 변경
    - Hit
    - PlayerName
- [x] Player.pitch 내 Random 인스턴스 static으로 변경
- [x] PlayerName indent 수정
- [x] 인스턴스화 하지 않는 Input등의 유틸은 생성자를 private으로 써서 불필요한 인스턴스 생성을 막는다
- [x] payload -> 세부 이름으로 변경
- [x] DeleteHistories 에 Optional 제거
    - overload 으로 두 개의 add를 구현
- [x] 각 테스트별 @DisplayName 추가
- [x] 점수판 출력에 대한 중복 부분 const화
    - [x] 점수판 헤더
    - [x] print 를 Output 으로 보내기
    - [x] '|' 와 같은 foramt
    - [x] Player가 Output에 의존하지 않도록 출력
- [x] 입, 출력은 Input, Output으로 분리
- [x] 플레이어가 스코어를 관리하던 역할을 분리 => Scores 일급 콜렉션으로 분리
    - 플레이를 할때마다 Score 인스턴스 스스로 점수를 받아서 더한다
    - 1 ~ 9 프레임을 NormalFrame, 10 프레임을 FinalFrame과 같은 구조로 구현한 후 Frame을 추가해 중복을 제거해 본다.
    - 다음 Frame을 현재 Frame 외부에서 생성하기 보다 현재 Frame에서 다음 Frame을 생성하는 방식으로 구현해 보고, 어느 구현이 더 좋은지 검토해 본다.
- [x] Score 에 대한 format 을 ScoreType enum 내에 포장
- [x] 누락 테스트 추가
    - Scores
    - PlayFrames
    - PlayFrame

### Requested changes phase 2
- [x] create factory method of Scores for Player

## Step3
