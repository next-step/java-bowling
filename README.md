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
 - [ ] 답변들을 관리하는 Answers 1급 컬렉션 생성
 - [ ] 삭제 이력들을 관리하는 DeleteHistories 1급 컬렉션 생성
 - [ ] Answer / Question 삭제 대상 설정 로직 -> 각 객체로 역할 이전
