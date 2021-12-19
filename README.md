# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 요구사항
* QnAService.deleteQuestion()에서 비즈니스 로직을 각각의 도메인으로 이동시킨다.
    * Question - 질문을 삭제할 권한이 있는지 체크한다.
    * Answers 일급 콜렉션 생성.
    * Answers - 답변을 삭제할 권한이 있는지 체크한다.
    * 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
    * FunctionalInterface을 추가하여 checked exception 처리.
    * Answers delete 분리.