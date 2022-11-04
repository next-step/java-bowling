# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

# 1단계 - 질문 삭제하기 기능 리팩토링
## 기능 목록
### 질문
- [ ] 질문을 삭제한다
    - [ ] 사용자와 질문자가 아닌 경우 UnAuthorizedException 예외를 throw한다.
    - [ ] 질문의 상태를 삭제 상태로 변경한다.
    - [ ] 모든 답변의 상태를 삭제로 변경한다.
    - [ ] 질문 삭제시 삭제이력을 남긴다.

### 답변
- [ ] 답변을 삭제한다.
    - [x] 사용자가 답변자가 아닌 경우 UnAuthorizedException 예외를 throw한다.
    - [x] 답변 삭제시 상태를 삭제 상태로 변경한다."
    - [x] 답변 삭제시 삭제이력을 남긴다.


## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)
