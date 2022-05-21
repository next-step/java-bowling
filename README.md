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
- [ ] DeleteHistories.saveAll 제거
  - [ ] DeleteHistoryService.saveAll 에서 바로 save
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
- [ ] scan Player name
  - [ ] name should be 3 chars
- [x] print initial score board
- [x] rename Score -> Player, add Score enum, Player.scores = List<Score>
- [x] Player.pitch => random hit
- [x] evaluate hit with ScoreType enum, Score.payload to show current score.
- [x] Player.plays => prevScore exists, Score.play once, else twice
- [ ] renew score, print score
- [ ] if strike, iterate to next frame
  - [ ] else, try same frame one more
