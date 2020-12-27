# 볼링 게임 점수판
### 기능 요구사항
* 최종 목표는 볼링 점수를 계산하는 프로그램을 구현한다. 1단계 목표는 점수 계산을 제외한 볼링 게임 점수판을 구현하는 것이다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
    * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

### 볼링의 협력 관계
* Controller가 Player 이름을 입력할 것을 요청한다.
* Controller가 Player 이름이 추가된 점수판을 출력하도록 요청한다.
* Controller가 Frames에게 게임을 진행할 것을 요청한다.
* Frames가 Frame에게 게임 진행을 요청한다.
    * 1~9프레임은 NormalFrame이 진행한다.
    * 10 플레임은 FinalFrame이 진행한다.
* Frame이 투구를 입력할 것을 요청한다.
* Frame이 결과를 출력할 것을 요청한다.

### 구현 목록
* [X] 투구 점수 입력 기능
* [ ] 투구에 따른 상태 반환 기능
* [ ] 투구 결과 출력 기능
* [X] Player 입력 기능
* [X] Frame 생성 기능
* [X] Frames 생성 기능

### 예외 처리
* [X] 투구 점수가 10을 초과할 경우 예외 처리
* [X] 투구 점수가 음수일 경우 예외 처리
* [X] Player 이름이 3글자가 아닌 경우 예외 처리
