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
     


