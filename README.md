# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

--------
## 1단계 - 질문 삭제하기 기능 리팩토링

### 0. 요구사항
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변 글의 모든 답변자가 같은 경우 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
한다.
- 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

### 1. 프로그래밍 요구사항
- qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.

```java
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
```
- 힌트1
    - 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
    - 규칙 8: 일급 콜렉션을 쓴다.
        - Question의 List를 일급 콜렉션으로 구현해 본다.
    - 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
        - 인스턴스 변수의 수를 줄이기 위해 도전한다.
- 힌트2 
    - 테스트하기 쉬운 부분과 테스트하기 어려운 부분을 분리해 테스트 가능한 부분만 단위테스트한다.
    
-------
## 2단계 - 볼링 점수판(그리기)

### 0. 요구사항
1. 기능 요구사항
- 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
- 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
    - 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태 
    - 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    - 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태 
    - 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
- 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

2. 구현 시작 방법
- 볼링 게임의 점수 계산 방식 아는 사람은 바로 구현을 시작한다.
- 점수 계산 방식을 모르는 사람은 구글에서 "볼링 점수 계산법"과 같은 키워드로 검색해 볼링 게임의 점수 계산 방식을 학습한 후 구현을 시작한다.

3. 프로그램 실행 결과
```
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
```

### 1. 프로그래밍 요구사항
- 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.
- 객체지향 생활 체조 원칙
    - 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
    - 규칙 2: else 예약어를 쓰지 않는다.
    - 규칙 3: 모든 원시값과 문자열을 포장한다.
    - 규칙 4: 한 줄에 점을 하나만 찍는다.
    - 규칙 5: 줄여쓰지 않는다(축약 금지).
    - 규칙 6: 모든 엔티티를 작게 유지한다.
    - 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    - 규칙 8: 일급 콜렉션을 쓴다.
    - 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.
- 힌트
    - 객체 단위를 가장 작은 단위까지 극단적으로 분리하는 시도를 해본다.
    - 1 ~ 9 프레임을 NormalFrame, 10 프레임을 FinalFrame과 같은 구조로 구현한 후 Frame을 추가해 중복을 제거해 본다.
    - 다음 Frame을 현재 Frame 외부에서 생성하기 보다 현재 Frame에서 다음 Frame을 생성하는 방식으로 구현해 보고, 어느 구현이 더 좋은지 검토해 본다.

### 2. 기능 분석 (Domain 설계)
1. Player
    1. 속성
        - `String name`
        - `Game game`
    2. 메서드
        - `void pitch(int pins)`
2. Game
   1. 속성
        - `Frames frames`
        - `Pitchings pitchings`
   2. 메서드
        - `void pitch(int pins)`
3. Frames
    1. 속성
        - `List<Frame> frames`
        - `FinalFrame finalFrame`
    2. 메서드
        - `void pitch(int pins)`
        - `void validateOverTenFrames()`
4. FinalFrame
    1. 속성
        - `Frame finalFrame`
        - `Pitching bonusPitching1`
        - `Pitching bonusPitching2`
    2. 메서드
        - `void pitch(Ptiching pitching)`
        - `void bonusPitching(Pitching pitching)`
        - `boolean done()`
5. Frame    
    1. 속성
        - `Pitching firstPitching`
        - `Pitching secondPitching`
    2. 메서드
        - `void validateSumIsOverTen()`
        - `void secondPitching(Ptiching pitching)`
        - `int score()`
        - `boolean strike()`
        - `boolean spare()`
        - `boolean done()`
6. Pitching
    1. 속성
        - `Pins pins`
        - `Pitching nextPitching`
    2. 메서드
        - `Integer score(int levelOfRecursion)`
7. Pins
    1. 속성
        - `int pins`
    2. 메서드
        - `void validateOverTen()`

-------
## 3단계 - 볼링 점수판(점수 계산)

### 0. 요구사항
1. 기능 요구사항(추가)
- 사용자 1명의 볼링 게임 점수를 관리할 수 있는 프로그램을 구현한다.
- 스트라이크는 다음 2번의 투구까지 점수를 합산해야 한다. 스페어는 다음 1번의 투구까지 점수를 합산해야 한다.

2. 프로그램 실행 결과
```
플레이어 이름은(3 english letters)?: PJS
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |      |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

1프레임 투구 : 10
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

2프레임 투구  : 8
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8   |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

2프레임 투구 : 2
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |      |      |      |      |      |      |      |      |
|      |  20  |      |      |      |      |      |      |      |      |      |

3프레임 투구 : 8
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |   8  |      |      |      |      |      |      |      |
|      |  20  |  38  |      |      |      |      |      |      |      |      |

3프레임 투구 : 1
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |  8|1 |      |      |      |      |      |      |      |
|      |  20  |  38  |  47  |      |      |      |      |      |      |      |
...
```

### 1. 프로그래밍 요구사항
1. 객체지향 5원칙을 지키면서 프로그래밍한다.
- 객체지향 5원칙(SOLID)
    1. SRP (단일책임의 원칙: Single Responsibility Principle)
        - 작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축: axis of change)을 수행하는 데 집중되어 있어야 한다
    2. OCP (개방폐쇄의 원칙: Open Close Principle)
        - 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다.
    3. LSP (리스코브 치환의 원칙: The Liskov Substitution Principle)
        - 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다. 즉, 서브 타입은 언제나 기반 타입과 호환될 수 있어야 한다.
    4. ISP (인터페이스 분리의 원칙: Interface Segregation Principle)
        - 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다.
    5. DIP (의존성역전의 원칙: Dependency Inversion Principle)
        - 구조적 디자인에서 발생하던 하위 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의 역전 원칙이다.

2. 힌트
- 점수를 구하는 역할을 각 Frame이 담당할 수 있도록 구현해 본다. Frame이 자신의 점수를 구하려면 다음 Frame에 접근할 수 있어야 한다. Frame이 LinkedList와 같은 자료 구조 기반으로 구현해 본다.
- Frame 외부에서 점수를 계산해 set하는 것이 아니라 Frame 자체가 점수를 계산할 수 있도록 한다.
    ```
    Frame frame = new Frame(frameNo);
    frame.bowl(10);
    frame.bowl(8);
    frame.bowl(2);
    int score =정 frame.getScore();
    // score는 20을 반환해야 한다.
    ```
- 자바의 다형성을 적용해 로직 구현에서 발생하는 수 많은 if/else를 제거한다.
- 구현 중 해결책을 찾기 힘든 경우 볼링 점수판 리팩토링 힌트 문서를 참고해 구현해 본다.
- 단, 정말 해결책을 찾으려다 포기하고 싶은 상황이 됐을 때 참고할 것을 추천한다.

### 2. 기능 분석 (Domain 설계)
- 각 Frame 점수의 경우 score 를 통해 이전 step 에서 구현 
- 누적점수가 필요하므로 Frame 내 totalScore 추가 구현 필요
1. Frame(추가 사항)
    1. 속성
        - `Frame beforeFrame`
    2. 메서드
        - `int totalScore()`
2. Status(enum) - frame 의 상태를 알려주는 enum
    1. enum
        - STRIKE
        - SPARE
        - MISS
        - NULL
    2. 속성
        - Score interface 구현체
    3. 메서드
        - `Status of(Pitching firstPitching, Pitching secondPitching`
3. Score interface 및 구현체들
    1. 구현체
        - StrikeScore
        - SpareScore
        - MissScore
        - NullScore
    2. 메서드
        - `int score()`
