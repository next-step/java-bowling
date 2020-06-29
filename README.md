# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# 1단계 - 질문 삭제하기 기능 리팩토링
### 질문 삭제하기 요구사항
* 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
* 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
* 답변이 없는 경우 삭제가 가능하다.
* 질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.
* 질문을 삭제할 때 답변 또한 삭제 해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
* 질문자와 답변자가 다른경우 답변을 삭제할 수 없다.
* 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

### 프로그래밍 요구사항
* qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
* 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.
<pre><code>
public class QnAService {
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        deleteHistoryService.saveAll(deleteHistories);
    }
}
</code></pre>
#### 힌트1
* 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
* 규칙 8: 일급 콜렉션을 쓴다.
    * Question의 List를 일급 콜렉션으로 구현해 본다.
* 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    * 인스턴스 변수의 수를 줄이기 위해 도전한다.
#### 힌트2
* 테스트하기 쉬운 부분과 테스트하기 어려운 부분을 분리해 테스트 가능한 부분만 단위테스트한다.

![힌트](https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/2020-04-08T11%3A45%3A40.213legacy_refactoring_3.png)

### Step1 리뷰 사항
* [x] [fix#1][Answers.java] forEach 만을 사용할 때는 stream()으로 변환하지 않아도 사용 가능
* [x] [fix#2][Answers.java] Answer에게 상태 값을 직접 설정 하기 보다는 Answer에게 삭제 요청을 보낼 것
* [x] [fix#3][Answers.java] anyMatch 활용
    <pre><code>return answers.stream()
                .anyMatch(answer -> !answer.isOwner(user));</code></pre> 
* [x] [fix#4][DeleteHistories.java] 질문과 답변이 삭제되고 반드시 DeleteHistory에 대한 정보를 남겨야 한다면 DeleteHistory는 질문과 답변에 대해서 종속적인 구조처럼 느껴짐, 따라서 Question과 Answer에게 삭제 요청 후 삭제된 정보를 바탕으로 DeleteHistory를 전달받도록 수정
    <pre><code>DeleteHistory questionDeleteHistory = question.delete();</code></pre>
* [x] [fix#5][QnAService.java] Question의 **내부에 있는 값을 꺼내 비교하기 보다는** Question 에게는 답변의 삭제 요청만을 하고, Question 내부의 Answers 에서 다른 사용자의 답변이 있다는 예외를 발생시키도록 수정
* [x] [fix#6][QnAService.java] Question의 **내부에 있는 값을 꺼내서 삭제요청하기 보다는** Question 에게 답변을 모두 삭제해 달라고 요청 
* [x] [fix#7][AnswersTest.java] Answer를 테스트 하는데 Question에 대한 의존성은 필요 없음
* [x] [fix#8][DeleteHistoriesTest.java] 한번 씩만 사용되는 값 이라면 테스트 내부에서 선언해서 사용 

# 2단계 - 볼링 점수판(그리기)
### 기능 요구사항
* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
    * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

### 구현 시작 방법
* 볼링 게임의 점수 계산 방식 아는 사람은 바로 구현을 시작한다.
* 점수 계산 방식을 모르는 사람은 구글에서 "볼링 점수 계산법"과 같은 키워드로 검색해 볼링 게임의 점수 계산 방식을 학습한 후 구현을 시작한다.
    * 볼링은 10개의 핀을 프레임당 2번의 기회가 주어지며, 총 10프레임 동안의 점수 합산으로 승부를 보는 아주 간단한 룰이며 즉, 핀을 많이 쓰러트리는 사람이 이기는 경기
    * 볼링은 한프레임당 최대 30점까지 받을 수 있으며 1프레임부터 10프레임까지 모두 스트라이크를 친 경우를 퍼펙트게임이라 하며 총 300점 이다.
    
<pre><code>프로그램 실행 결과
플레이어 이름은(3 english letters)?: PJS
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |      |      |      |      |      |      |      |      |      |      |

1프레임 투구 : 10
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |      |      |      |      |      |      |      |      |      |

2프레임 투구 : 8
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8   |      |      |      |      |      |      |      |      |

2프레임 투구 : 2
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |      |      |      |      |      |      |      |      |

3프레임 투구 :  7
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |  7   |      |      |      |      |      |      |      |

3프레임 투구 :  : 0
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |  7|- |      |      |      |      |      |      |      |

...
</code></pre>

### 프로그래밍 요구사항
* 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.
* 객체지향 생활 체조 원칙
    * 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
    * 규칙 2: else 예약어를 쓰지 않는다.
    * 규칙 3: 모든 원시값과 문자열을 포장한다.
    * 규칙 4: 한 줄에 점을 하나만 찍는다.
    * 규칙 5: 줄여쓰지 않는다(축약 금지).
    * 규칙 6: 모든 엔티티를 작게 유지한다.
    * 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    * 규칙 8: 일급 콜렉션을 쓴다.
    * 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.
### 힌트
* 객체 단위를 가장 작은 단위까지 극단적으로 분리하는 시도를 해본다.
* 1 ~ 9 프레임을 NormalFrame, 10 프레임을 FinalFrame과 같은 구조로 구현한 후 Frame을 추가해 중복을 제거해 본다.
* 다음 Frame을 현재 Frame 외부에서 생성하기 보다 현재 Frame에서 다음 Frame을 생성하는 방식으로 구현해 보고, 어느 구현이 더 좋은지 검토해 본다.

### Step2 리뷰 사항
* [ ] [fix#01][BowlingGame.java] 게임과 관련된 너무 많은 로직을 수행하고 있음 Controller의 역할을 하기보다는 Model의 역할을 하고 있는 것으로 보임  
View와 Model간의 중개 역할은 BowlingGameApplication에 맡기고 Model에만 집중하도록 수정
* [ ] [fix#02][BowlingGame.java] ScoreBoard는 서로 다른 메소드들 간에 필드가 공유되고 있지 않아서 class 변수로 선언할 필요가 없음
* [ ] [fix#03][BowlingGame.java] 핀이 몇 개가 쓰러졌는지는 사용자가 입력한 값으로 결정하는게 아닌, 매 프레임 마다 볼링 게임이 결정해야 할 일로 보임   
BowlingGame 이 결정할 일이 아님
* [ ] [fix#04][BowlingGame.java] score 역할을 하는 객체를 만들어 Score객체가 생성될 때 유효성 검사를 하도록 수정
* [ ] [fix#05][BowlingGame.java] spare = 0은 필드 변수로 선언하지 않는다면 0으로 따로 초기화 할 필요가 없어 보임
* [ ] [fix#06][BowlingGameView.java] View는 출력만 담당하고 게임과 관련된 계산은 게임과 관련된 정보를 가진 ScoreBoard 에게 요청하도록 수정
* [ ] [fix#07][BowlingGameView.java] scoreBoard가 넘겨주는 값이 Optional 한 값이라면, 사용자가 null 여부를 판단하기 보다는 scoreBoard가 Optional을 반환하도록 변경할 것
* [ ] [fix#08][BowlingGameView.java] View가 ScoreBoard의 내부 구조를 모두 알아야 만 결과를 출력할 수 있는 구조  
필요한 값이 Frame.getFrameResultSymbol()의 반환 값이라면 ScoreBoard가 Frame.getFrameResultSymbol()의 값을 반환 하도록 수정할 것
* [ ] [fix#09][Frame.java] Class가 의도대로 동작하는지 Test 추가 
* [ ] [fix#10][Frame.java] 자바에서는 관례적으로 static 변수, 인스턴스 순으로 변수를 선언하고 있음 자동차 경주 미션에 첨부되어 있던 https://myeonguni.tistory.com/1596 참고
* [ ] [fix#11][Frame.java] FrameStatus를 직접 결정하고 있음, 이럴경우 FrameStatus의 값이 추가 되거나, 삭제 될 때마다 Frame 클래스도 함께 수정이 발생하게 됨   
Frame이 필요한 값은 FrameStatus의 enum 값 보다는 symbol이 필요해 보임, FrameStatus에게 score와 spare를 전달하면서 symbol을 달라고 요청하도록 수정할 것
* [ ] [fix#12][ScoreBoard.java] Class가 의도대로 동작하는지 Test 추가
* [ ] [fix#13][BowlingRule.java] 의미없이 반복되는 값이 아닌, 게임의 진행과 밀접하게 관련된 값이 모여 있음  
게임과 밀접한 관련이 있는 값을 단순히 상수로 선언해 두게 된다면, 게임과 관련 없는 곳에서 해당 상수를 의미와는 다르게 사용할 우려가 있음  
만약 상수를 의도와는 다르게 다른 클래스에서 사용하게 된다면 상수를 수정할 때, 상수를 사용하고 있는 게임과 관련 없는 객체까지 함께 수정이 발생하게 되어 의도치 않은 오류를 발생할 수 있음  
이 숫자들을 가장 잘 알고 있어야 하는 객체에 상수를 선언하도록 수정할 것
* [x] [fix#14][Answer.java] Class가 의도대로 동작하는지 Test 추가 
* [x] [fix#15][Answer.java] delete() 메소드가 deleted의 상태값을 true로 변경하는 역할을 하고 있어 setDeleted()를 public 으로 선언할 필요 없음
* [ ] [fix#16][Question.java] DeleteHistories delete(User user) 테스트 추가
* [ ] [fix#17][Question.java] delete() 메소드가 deleted의 상태 값을 변경하는 역할을 하고 있어 setDeleted()를 public 으로 선언할 필요 없음
* [ ] [fix#18][Question.java] Answer들을 꺼내서 확인하기 보다는 Answer에게 User를 넘겨주고 Answer로 하여금 작성자가 일치하는지 판단하도록 수정
