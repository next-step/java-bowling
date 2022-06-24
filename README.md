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
- [ ] Const 의 변수들 필요한 위치로 분리
    - class 당 2개 변수 제한 때문에 옮기기 어렵다 -> static 변수는 괜찮은가?
- [x] 컬렉션을 리턴해줄때는 불변으로 리턴 (PlayFrames)
- [x] 주석 제거

## Step3

### Result

```
Please put player name (Max 3 latters)
gon
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |        |        |        |        |        |        |        |        |        |        |
| gon  |        |        |        |        |        |        |        |        |        |        |

1 Frame pitch: 6
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6     |        |        |        |        |        |        |        |        |        |
| gon  |        |        |        |        |        |        |        |        |        |        |

1 Frame pitch: 2
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |        |        |        |        |        |        |        |        |        |
| gon  |  8     |        |        |        |        |        |        |        |        |        |

2 Frame pitch: 5
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5     |        |        |        |        |        |        |        |        |
| gon  |  8     |        |        |        |        |        |        |        |        |        |

2 Frame pitch: 4
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |        |        |        |        |        |        |        |        |
| gon  |  8     |  17    |        |        |        |        |        |        |        |        |

3 Frame pitch: 4
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4     |        |        |        |        |        |        |        |
| gon  |  8     |  17    |        |        |        |        |        |        |        |        |

3 Frame pitch: 2
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |        |        |        |        |        |        |        |
| gon  |  8     |  17    |  23    |        |        |        |        |        |        |        |

4 Frame pitch: 7
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7     |        |        |        |        |        |        |
| gon  |  8     |  17    |  23    |        |        |        |        |        |        |        |

4 Frame pitch: 3
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |        |        |        |        |        |        |
| gon  |  8     |  17    |  23    |        |        |        |        |        |        |        |

5 Frame pitch: 9
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9     |        |        |        |        |        |
| gon  |  8     |  17    |  23    |  42    |        |        |        |        |        |        |

5 Frame pitch: 0
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |        |        |        |        |        |
| gon  |  8     |  17    |  23    |  42    |  51    |        |        |        |        |        |

6 Frame pitch: 5
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5     |        |        |        |        |
| gon  |  8     |  17    |  23    |  42    |  51    |        |        |        |        |        |

6 Frame pitch: 2
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |        |        |        |        |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |        |        |        |        |

7 Frame pitch: 1
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1     |        |        |        |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |        |        |        |        |

7 Frame pitch: 3
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |        |        |        |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |        |        |        |

8 Frame pitch: 1
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |  1     |        |        |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |        |        |        |

8 Frame pitch: 4
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |  1|4   |        |        |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |  67    |        |        |

9 Frame pitch: 8
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |  1|4   |  8     |        |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |  67    |        |        |

9 Frame pitch: 1
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |  1|4   |  8|1   |        |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |  67    |  76    |        |

10 Frame pitch: 10
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |  1|4   |  8|1   |  10    |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |  67    |  76    |        |

10 Frame pitch: 0
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |  1|4   |  8|1   |  X|/   |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |  67    |  76    |        |

10 Frame pitch: 2
| NAME |  01    |  02    |  03    |  04    |  05    |  06    |  07    |  08    |  09    |  10    |
| gon  |  6|2   |  5|4   |  4|2   |  7|/   |  9|-   |  5|2   |  1|3   |  1|4   |  8|1   |  X|-|2 |
| gon  |  8     |  17    |  23    |  42    |  51    |  58    |  62    |  67    |  76    |  88    ||
```

### Todo

#### first implement at `bowling`

- [x] print 로직을 Output 으로 이동
- [x] 누계 점수 기능 구현
    - [x] Subtotal class 생성
        - 인자로 이전 subtotal을 받아 합산
        - 생성 시 strike, miss, spare 경우에 따라 waiting 설정
    - [x] Scores -> Frames 로 이름 변경하고 subtotals 추가
    - [x] plays 내에서 subtotal 생성하고 evaluateBonus 로직 추가
    - [x] 10 프레임 이후에는 handleLast 에서 strike, spare 인 경우 한번 더 투구: playBonus
        - 이외의 경우에는 subtotal.setLast 로 기다리지 않아도 됌을 명시하고 최종 점수 출력
- [x] domain test 추가
    - Player
    - Frames
    - Scores
    - Subtotal
- [ ] Refactoring 가능 class 확인

#### second implement at `bowling_Step3`

- [x] 최하위도메인인 Scores는 scores(점수 리스트 최대 3개), remainingPitch(남은 투구 횟수) 를 가진다.
    - [x] 최대핀개수10개를 활용해 remainingPins구함
    - [x] pitchRandom() 수행 시 남은 핀의 개수를 최대 범위로 하여 랜덤 투구 수행
    - [x] lastScore() 통해 scores 중 마지막 투구한 점수 도출
    - [x] isStrike() 스트라이크 여부 확인
    - [x] done() 잔여 투구가 없는지 여부 확인
    - [x] evaluateLastBonus() FrameLast에서 Strike이거나 Spare인경우 remainingPitch를 하나 더 준다, 최종 3번째 투구인 경우 remainingPitch는 0으로
      고정
- [x] Subtotal 은 State와 value를 가진다.
    - [x] accumulateBonus(int bonus) 는 State의 wait 단계를 한 단계 낮추고 보너스를 합산한다. bonus가 10인경우(Strige) 는 바로 DONE으로 설정
- [x] State 는 INIT, DONE, WAIT_TWICE, WAIT_ONCE 의 상태를 가진다
    - [x] 각 상태는 remainingWait를 가지며 보너스를 기다려야할 횟수를 의미한다.
    - [x] decreaseWait(): WAIT 단계를 한 단계 낮춰준다.
    - [x] waiting(): 기다려야 할 보너스가 남았는지 여부 리턴
- [x] FrameGeneral은 1 ~ 9 라운드를 담당하며, Scores, Subtotal을 멤버로 가진다.
    - [x] pitchManual()은 수동으로, pitchRandom()은 자동으로 투구를 하고 pitch 를 호출한다
    - [x] pitch(Scores scores, Frames frames) 는 이전 프레임이 보너스를 대기중일 경우 subtotal에 점수를 합산하고 현재 프레임의 subtotal에도 합산한다.
        - [x] evaluateState() 프레임의 투구를 모두 마친 경우에는 score의 상태를 평가하여 WAIT_TWICE, WAIT_ONCE, DONE 중에 하나를 가진다
- [x] FrameLast 는 10라운드를 담당한다
    - [x] pitch의 경우 FrameGeneral과 다르게 scores.evaluateLastBonus() 를 수행하여 남은 투구횟수를 추가할 지 결정한다.
- [x] Frames 는 frame에 대한 일급 컬렉션이다
    - [x] create() 는 0~8 은 FrameGeneral, 9는 FrameLast 를 생성해준다
    - index(): 현재 프레임의 index를 리턴
    - first(): 첫 Frame 리턴
    - next(): 다음 Frame을 리턴
- [x] Frame 은 Frames 일급 컬렉션이 FrameGeneral과 FrameLast 를 모두 가지게 해주는 인터페이스
- [x] Player 는 String name을 변수로 가지며 name은 3글자를 초과할 수 없다.

### Requested changes

- [x] equals, hashCode 는 클래스의 제일 아래에 구현
- [x] 인터페이스와 구현 클래스 사이에 추상 클래스 추가하여 중복 제거
- [x] pitch 에서 투구할 때는 투구 정보만 관리, score, subtotal 계산은 별도 분리
    - [x] rename pitchManual to playManual
    - [x] rename pitchRandom to pitchRandom
    - [x] split to updateScore
    - [x] split to updateSubtotal
- [ ] Frame immutable 을 지향하기 위채 최대한 노력
    - Frame 를 리턴해서 다시 Frames 의 특정 index 에 할당하는 방식으로 구현을 시도했으나 next Frame 에 대한 subtotal 값을 처리하는 부분이 멤버 변수에 값을 할당하는 형태로
      되어있어 좀 더 대규모의 리팩토링이 필요할 것같습니다 ㅠ
- [ ] pitchManual 과 random 으로 분리한 이유?
    - 프레임 단위의 투구에 대한 테스트를 위해 manual 과 random 으로 분리하였습니다.
    - score 도메인에서 이미 random 과 manual 에 대한 테스트 가 되어 있으므로 불필요한 메서드 일까요?

### Requested changes - phase2

- [x] FrameMutual.scores, subtotal private 으로 변경
    - rename updateScore to evaluateScore
- [x] play 하나로 구현하고 테스트도 가능한 구조 찾기
    - 밖에서 randomPins 를 구한 후 파라미터로 입력
- [x] play 시에는 play만 하여 scores를 구하고 이후에 createSubtotals 를 통해 중간누적점수를 구함
- [x] 테스트를 작은 단위로 분리

### Requested changes - additional

#### BowlingApp.java

- [x] frame.finished 로 종료 여부 판단


#### FrameLast.java

- [x] FrameMutual 이 Frame interface 를 구현하고 있으므로 FrameMutual 만 상속

#### FrameMutual.java

- [x] 추상 클래스임을 표현 하기 위해 AbstractFrame 으로 변경
- [x] 주 생성자는 생성자의 마지막에 구현
- [ ] 인스턴스 변수를 private 접근 제어자로 변경

#### Frame.java

- [ ] interface 분리하여 메서드 수 줄이기

## Step 4
