# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 기능 요구사항 - 질문 삭제하기 기능 리팩토링
### Question
- [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
    - [X] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
    - [X] 답변이 없는 경우 삭제가 가능하다. (Answer에게 삭제 가능한지 확인한다.)
    
### Answer
- [X] 답변 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
    - [X] 로그인 사용자와 답변한 사람이 같은 경우 삭제 가능하다.
    
### DeleteHistory
- [ ] 질문 삭제 이력에 대한 정보를 남긴다.
- [X] 답변 삭제 이력에 대한 정보를  남긴다.