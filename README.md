# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## step1
- 질문 삭제하기 요구사항
    - 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
    - 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
    - 답변이 없는 경우 삭제가 가능하다.
    - 질문자와 답변 글의 모든 답변자 같은 경우 삭제가 가능하다.
    - 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
한다.
    - 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
    - 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
- 프로그래밍 요구사항
    - qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
    - 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드를 분리해 단위 테스트 가능한 코드 에 대해 단위 테스트를 구현한다.

## step2
### 요구사항
- 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
- 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
    - 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    - 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    - 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    - 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
- 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.
- 프로그램 실행 결과
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
  
### 객체
    
- Player : 플레이어
    - [x] 이름이 3글자 알파벳이 아닐시 IllegalArgumentException이 발생한다.
    
- Score : 투구의 점수
    - [x] 투구의 점수가 0~10이 아닐시 IllegalArgumentException이 발생한다.
    - [x] 다른 점수와의 합을 계산한다.
    - [x] 다른 점수와의 크기를 비교한다.
    
- Scores : 한 프레임의 두 개의 투구의 점수
    - [x] 점수를 입력받아 결과를 확인한다.
    - [x] 첫번째와 두번째 투구의 점수의 합이 10을 넘으면 IllegalArgumentException이 발생한다.
    - [x] 두번째 투구의 점수를 입력하지 않은채 결과를 확인하면 IllegalStateException이 발생한다.
    - [x] 첫번째 투구의 점수가 10이면 두번째 투구의 점수를 입력하지 않아도 결과를 확인할 수 있다.
    - [x] 두 개의 점수가 모두 입력되었는지 확인한다.
    
- Result : 한 프레임의 결과
    - [x] STRIKE, SPARE, MISS, GUTTER
    - [x] 투구 점수를 받아 매칭되는 결과를 찾는다.
    
- Frame : 프레임
    - [x] 점수를 기록한다. 
    - [x] 이미 점수가 다 기록된 프레임에 또 점수를 기록하려고 하면 IllegalStateException이 발생한다.
    - [x] 점수를 더 기록할 수 있는지 확인한다.
    - NormalFrame : 1번째부터 9번째 프레임
        - [x] 다음 프레임을 생성한다.
    - FinalFrame : 10번째 프레임
        - [x] 다음 프레임을 생성하려고 하면 UnsupportedOperationException이 발생한다.
        - [ ] 스트라이크이거나 스페어면 한 번의 투구 점수를 더 받는다.
        
- Frames : 프레임 목록
    - [x] 10개의 프레임을 생성한다.
    - [x] 새로운 점수를 기록한다.
    - [x] 점수를 더 기록할 수 있는지 확인한다.
        
- BowlingGame
    - [x] 게임을 시작하면 새로운 프레임 목록을 생성한다.
    - [x] 새로운 점수를 기록한다.
    
- BowlingGameController
    - [x] 게임을 진행한다.
    
- InputView
    - [x] 플레이어의 이름을 사용자로부터 입력받는다.
    - [x] 투구 점수를 사용자로부터 입력받는다.
    
- OutputView
    - [x] 맨 처음에 플레이어의 이름을 출력한다.
    - [x] 매 프레임의 결과를 출력한다.
    - [x] 스트라이크면 "X", 스페어이면 "9|/", 미스이면 "8|1", 거터이면 "-" 형식으로 표시한다.

- ViewResult
    - [x] 프레임 결과에 알맞는 출력용 문자열을 찾는다.
