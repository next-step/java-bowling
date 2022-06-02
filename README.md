# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## STEP 2 볼링 게임 점수판 구현
1. 사용자 입력 (이름이 blank가 아니고, 3글자 이내의 이름을 가지도록 구현)
2. 반복문으로 각 프레임 별 결과 입력 (숫자 입력)
3. BowlingGame list 객체를 만든다.
    (1) normal frame, final frame 을 가지도록 list 생성
4. Normal Frame
    (1) Frame 값으로 숫자를 입력한다.
    (2) Frame 입력된 값을 첫번째인지, 두번째인지 판단한다.
    (3) strike, spare 의 경우에 대해 처리한다.
5. Final Frame
    (1) bonus delivery를 가지며 첫번째, 두번째 숫자에 따라 보너스 시도가 있다.
    (2) 보너스 시도에서도 strike, spare 처리를한다.
6. 입력받은 list를 출력한다.