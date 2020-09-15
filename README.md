# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## STEP01 
 - 단위 테스트가 가능한 코드 -> validation 로직.
 - 단위 테스트가 어려운 코드 -> DB와의 connection이 존재하는 로직.
### TODO
 - [x] 질문 삭제 권한 체크 -> Question의 역할로 이전
 - [x] 답변 관련 삭제 권한 체크 -> Answer의 역할로 이전
 - [x] 답변들을 관리하는 Answers 1급 컬렉션 생성
 - [x] 삭제 이력들을 관리하는 DeleteHistories 1급 컬렉션 생성
 - [x] Answer / Question 삭제 대상 설정 로직 -> 각 객체로 역할 이전
 - *step01 pr : https://github.com/next-step/java-bowling/pull/263
 
## STEP02
### Programing needs
 - 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
 - 규칙 2: else 예약어를 쓰지 않는다.
 - 규칙 3: 모든 원시값과 문자열을 포장한다.
 - 규칙 4: 한 줄에 점을 하나만 찍는다.
 - 규칙 5: 줄여쓰지 않는다(축약 금지).
 - 규칙 6: 모든 엔티티를 작게 유지한다.
 - 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
 - 규칙 8: 일급 콜렉션을 쓴다. 
 - 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.
 
 ### TODO
  - [x] 플레이어 이름을 입력받는다 (영문3자 제한)
  - [x] 초기 점수판을 출력한다.
  - [x] 프레임 투구 결과 넘긴 핀의 개수를 입력받는다. 
  - [x] 기본적으로 프레임당 2번 입력 받고, 첫 투구가 스트라이크인 경우 1번 입력 받는다. 
  단, 10프레임은 스트라이크인 경우에도 2번.
  - [x] 10프레임(final frame)의 경우 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.
  - [x] 입력받은 프레임 결과가 스트라이크(10) 이면 "X", 스페어(두번 던진 총합이 10) 이면 "0~9|/" 
  미스(두번 던진 총합이 9이하) 이면 "0~9 | 0~9"와 같이 출력한다. 거터(0)은 "-" 
