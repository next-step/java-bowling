# 볼링 게임 점수판

## 볼링 점수판(그리기) 리뷰내용
* else를 쓰지 않는다. 얼리리턴이어도 if가 많으면 else와 같다.
* assertAll과 assertThat 여러개의 차이. assertAll은 윗 검증이 실패해도 다음 검증들을 진행한다.
* 몇 프레임인지 적용
* Player에서 Frames분리
* 매직넘버 제거
* 인터페이스의 책임 분리
* void 타입 메서드는 테스트가 어렵다. 반환값을 주어서 테스트해보기

## 볼링 점수판(그리기) 생각해보기
* Player: 1~3 영어
* Player가 프레임 여러 개를 가짐, Player에게 볼링게임에 대한 정보를 물어보기
* 프레임즈: 프레임을 여러개 묶음.
* 프레임: 라운드, 몇 개의 핀이 쓰러졌는지 등 현재 프레임의 정보를 가지는 무언가를 상태로 가짐.
* 라운드, 숫자 1~10
* 현재 프레임의 정보, 몇점? 몇개의 핀? 몇번 더 칠 수 있는지? 등을 가지는 무언가
    - 투구를 넣으면 상태가 변하고, 변한 정보와 관련된 무언가가 반환


## 질문 삭제하기 기능 리팩토링 리뷰 내용
* 다른 도메인과의 의존성을 줄이자
* 생성은 최소로 필요한 초기화의 느낌이고, 필요한 데이터는 이후에 넣어주자

## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)