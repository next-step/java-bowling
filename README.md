# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

<br>

### step1: 질문 삭제하기 기능 리팩토링

- [x] 삭제 상태로 변경
- [x] 일급컬렉션: Answers
  - `@Embedded` / `@Embeddable` 사용  
    [참고 blog](https://wbluke.tistory.com/23)
- [x] service -> domain 으로 책임 이동
  -  Question
    - [x] 로그인 유저와 작성자 validation 
    - [x] answer 없는 경우 
    - [x] answer 있는 경우 -> answer 작성자 체크, 삭제상태로 변경.
     

-------

### step2: 볼링 점수판(그리기)
- [x] 피드백 반영
  - 메서드 동사형으로

- [x] Name 
    - 이름 입력 3글자, 영어로만.
    
- [x] pin
    - 볼링공을 한번 던졌을 때. 0~10 입력 가능. 그 외 예외처리.
    - `score` : 내가 쓰러뜨린 핀의 개수 
    
- [x] frame
    - 프레임. 한 경기는 10회의 프레임으로 이루어져있다. (ball 을 최대 2번 던졌을 떄의 객체)
        - final frame (마지막 프레임) : 보너스 게임 판단 -> 실행
    - 1 ball 후, 상태 체크 -> 진행 여부 판단 -> 1 프레임 종결 후 상태(`strike`, `spare`, `miss`) 판단.
    - `index` : 현재 회차
    - `Score` : pin 객체 집합. => 이 값으로 상태값 판단, 결과값 전달.
- [x] bowlingScore
    - enum. ResultView에 보여줄 값으로 치환. (예- 10 -> X)
- [x] bowlingState
    - state 패턴을 위함. 각 상태에 맞는 `printResult()`로 현재 상태값 프린트.
    
- [x] frames
    - 프레임10개의 묶음.
    
- [x] bowling
    - frames 와 playerName 묶음
    
