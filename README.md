# 볼링 게임 점수판

## 진행 방법

* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## TODO

* Input
    * 점수를 입력받을 수 있다

* Domain
    * 일반 프레임, 10회차 프레임을 생성할 수 있다
        * 프레임 내 점수를 초과하면 익셉션이 발생한다
        * 일반 프레임 + 10회차 프레임을 묶는 객체를 만든다

* Output
    * 각 회차에 맞는 프레임 점수를 그려준다

## DONE

* Input
    * 플레이어 이름을 입력받을 수 있다

* Domain
    * 플레이어를 생성할 수 있다
        * 이름 3자 초과 시 익셉션이 발생한다