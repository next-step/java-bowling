# QnaService
## 설계
### ExceptionFactory
- 해당타입에 맞는 ExceptionFactory 명시적으로 제공

### Validations
- check하는 함수들 전부 object내부에 설계
- isOwner
- countUnauthorized
- 위 두개의 함수에서 check하여 아닌경우에 exception 생성
- check 한 것이 존재한다면 exception Factory에서 생성

### MakeDeleteHistory
- 전부 삭제 history를 만들기에 interface로 확장성 

### CannotDeleteException
- default tx는 runtime exception을 잡기에 runtime exceptiond으로 변경

# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)