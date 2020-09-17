# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step01 : To Do List
-[X] 단위 테스트 가능한 코드 테스트
    -[X] Question 삭제 가능한지 확인하기
        -[X] Question 작성자가 로그인한 유저와 동일 인물 인지 확인하기
        -[X] Answers 중에서 로그인한 유저가 아닌 다른 사람이 작성한 답변이 있는지 확인하기
    
-[X] 도메인 로직으로 이동
    -[X] Question 삭제 기능
    -[X] Answer 삭제 기능

-[X] 일급 콜렉션을 사용한다.
    -[X] List<Answer>을 랩핑한 Answers 구현하기
    -[X] List<DeleteHistory>을 랩핑한 DeleteHistories 구현하기
    
## Step02 
-[X] User 구현
-[ ] Frame 구현
    -[ ] 1~9번 Frame : NormalFrame으로 구현
    -[ ] 10번 Frame : FinalFrame으로 구현
-[ ] 하나의 투구 구현 (Delivery)
-[X] Pins 구현

-[ ] 투구마다 넘어뜨린 핀 갯수 입력받기
-[ ] 투구 입력 받고 점수판 현황 출력

