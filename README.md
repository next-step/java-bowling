# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 질문 삭제하기 기능목록
**질문 데이터, 답변의 상태를 삭제 상태(deleted - boolean type)로 변경한다.**

- [x] 질문 삭제
  - [x] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
    - [x] 답변이 없는 경우 삭제가 가능하다.
  - [x] 질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능하다.
    - [x] 질문을 삭제할 때 답변 또한 삭제한다.


- [x] 답변 삭제
  - [x] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
  
- [x] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 볼링 점수판(그리기)

### 플레이어
- [x] 플레이어 이름은 3 english letters

### 핀
- [x] 유효한 핀의 개수는 0~10개

### 상태
- [x] 끝난 상태 반환
- 진행
  - [x] 준비: 첫번째볼 or 스트라이크
  - [x] 첫번째 볼: 미스 or 스페어
- 끝
  - [x] 미스
  - [x] 스페어
  - [x] 스트라이크

### 프레임
- frames
  - [x] 프레임이 끝나면 다음 프레임 생성
- 일반
  - [x] 상태와 프레임번호를 가짐
  - [x] 게임 진행시 상태 변경
  - [x] 프레임이 끝났는지 반환
  - [x] 다음 프레임 생성
- 마지막
  - [x] 프레임이 끝났는지 반환
  - [x] 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구

### 입력
  - 플레이어 이름은(3 english letters)?: PJS
  - 1프레임 투구 : 10

### 출력
```
  3프레임 투구 :  7
  | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
  |  PJS |  X   |  8|/ |  7   |      |      |      |      |      |      |      |
```
- 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
  - 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  - 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  - 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  - 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
  
